package com.example.springkuzmin.service.user;


import com.example.springkuzmin.dto.user.UpdateDto;
import com.example.springkuzmin.dto.user.UserInfoDto;
import com.example.springkuzmin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserMeService{
    private final UserCrudService userCrudService;
    @Autowired
    public UserMeService(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }
    public UserInfoDto update(UpdateDto user) {
        User userEntity = fromUpdateDto(user);
        userEntity = userCrudService.update(userEntity, userEntity.getId());
        return toUserInfo(userEntity);
    }
    public void remove(){
        User user = getAuthenticatedUser();
        userCrudService.remove(user);
        SecurityContextHolder.getContext().setAuthentication(null);
    }
    public UserInfoDto get(){
        User user =  getAuthenticatedUser();
        return toUserInfo(user);
    }
    private User getAuthenticatedUser(){
        return userCrudService.getByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()
        );
    }
    private UserInfoDto toUserInfo(User user){
        return new UserInfoDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
    private User fromUpdateDto(UpdateDto updateDto){
        User userEntity = new User();
        userEntity.setEmail(updateDto.getEmail());
        userEntity.setLastName(updateDto.getLastName());
        userEntity.setFirstName(updateDto.getFirstName());
        userEntity.setPassword(updateDto.getPassword());
        userEntity.setId(getAuthenticatedUser().getId());
        userEntity.setRole(getAuthenticatedUser().getRole());
        userEntity.setEnabled(getAuthenticatedUser().isEnabled());
        return userEntity;
    }
}

