package com.example.springkuzmin.service;

import com.example.springkuzmin.dto.role.RoleRequest;
import com.example.springkuzmin.dto.role.RoleResponse;
import com.example.springkuzmin.dto.user.UserRequest;
import com.example.springkuzmin.dto.user.UserResponse;
import com.example.springkuzmin.model.Role;
import com.example.springkuzmin.model.User;
import com.example.springkuzmin.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::buildUserResponse)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(UUID userId) {
        return userRepository.findById(userId)
                .map(this::buildUserResponse)
                .orElseThrow(() -> new EntityNotFoundException("User" + userId + "is not found"));
    }

    @NotNull
    private UserResponse buildUserResponse(@NotNull User user) {
        return new UserResponse()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setRole(new RoleResponse()
                        .setName(user.getRole().getName()));
    }

    @NotNull
    @Override
    @Transactional
    public UserResponse update(UUID userId, UserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User" + userId + "is not found"));
        userUpdate(user, request);
        return buildUserResponse(userRepository.save(user));
    }

    @NotNull
    private void userUpdate(@NotNull User user, @NotNull UserRequest request) {
        ofNullable(request.getId()).map(user::setId);
        ofNullable(request.getFirstName()).map(user::setFirstName);
        ofNullable(request.getLastName()).map(user::setLastName);

        RoleRequest roleRequest = request.getRole();
        if(roleRequest !=null) {
         ofNullable(roleRequest.getName()).map(user.getRole()::setName);
        }
    }


    @NotNull
    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
        User user = buildUserRequset(request);
        return buildUserResponse(userRepository.save(user));
    }

    @NotNull
    @Override
    @Transactional
    public void delete(UUID userId) {
        userRepository.deleteById(userId);
    }

    @NotNull
    private User buildUserRequset(@NotNull UserRequest request) {
        return new User()
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setRole(new Role()
                        .setName(request.getRole().getName()));
    }


}
