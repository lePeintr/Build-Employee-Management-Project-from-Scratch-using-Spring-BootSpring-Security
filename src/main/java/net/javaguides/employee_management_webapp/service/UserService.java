package net.javaguides.employee_management_webapp.service;

import net.javaguides.employee_management_webapp.dto.UserRegistrationDto;
import net.javaguides.employee_management_webapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
