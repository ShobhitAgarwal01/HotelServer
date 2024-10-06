package com.hotelproject.hotelserver.services.auth;

import com.hotelproject.hotelserver.dto.SignupRequest;
import com.hotelproject.hotelserver.dto.UserDto;
import com.hotelproject.hotelserver.entity.User;
import com.hotelproject.hotelserver.enums.UserRole;
import com.hotelproject.hotelserver.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    Logger logger;

    private final UserRepository userRepository;

    @PostConstruct
    public void createAnAdminAccount(){
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount.isEmpty()){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("Admin");
            user.setPassword(new BCryptPasswordEncoder().encode("Admin123"));
            user.setUserRole(UserRole.ADMIN);

            userRepository.save(user);
            System.out.println("Admin Account Created SuccessFully");
        }else {
            System.out.println("Admin Account There");
        }
    }

    public UserDto createUser(SignupRequest signupRequest){
        if(userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()){
            throw new EntityExistsException("User is Already Present with Email: " + signupRequest.getEmail());
        }
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);

        return createdUser.getUserDto();
    }
}
