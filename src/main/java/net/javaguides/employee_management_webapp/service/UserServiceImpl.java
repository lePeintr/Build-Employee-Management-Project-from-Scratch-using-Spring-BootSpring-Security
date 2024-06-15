package net.javaguides.employee_management_webapp.service;

import net.javaguides.employee_management_webapp.dto.UserRegistrationDto;
import net.javaguides.employee_management_webapp.model.Role;
import net.javaguides.employee_management_webapp.model.User;
import net.javaguides.employee_management_webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl( UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
