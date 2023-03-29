package com.example.springkuzmin.service.user;


import com.example.springkuzmin.dto.user.AuthDTO;
import com.example.springkuzmin.dto.user.RegDTO;
import com.example.springkuzmin.model.Token;
import com.example.springkuzmin.model.User;
import com.example.springkuzmin.repository.RoleRepos;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

@Service
public class UserRegService {
    private final UserCrudService userCrudService;
    private final RoleRepos roleRepository;
    private final UserAuthService userAuthService;
    public UserRegService(UserCrudService userCrudService,
                          RoleRepos roleRepository,
                          UserAuthService userAuthService) {
        this.userCrudService = userCrudService;
        this.roleRepository = roleRepository;
        this.userAuthService = userAuthService;
    }
    public Token registration(RegDTO regDto) throws Exception {
        if (userCrudService.existsByUsername(regDto.getEmail())) {
            throw new EntityExistsException("User with email: " + regDto.getEmail() + " already exists");
        }
        User user = fromRegDto(regDto);
        user.setRole(roleRepository.findById("ROLE_USER").get());
        userCrudService.create(user);
        Token token = userAuthService.authorization(
                new AuthDTO(user.getEmail(), regDto.getPassword())
        );
        return token;
    }
    private User fromRegDto(RegDTO regDto){
        User user = new User();
        user.setEnabled(true);
        user.setEmail(regDto.getEmail());
        user.setFirstName(regDto.getFirstName());
        user.setLastName(regDto.getLastName());
        user.setPassword(regDto.getPassword());
        return user;
    }
}
