package pl.org.workout.services;

import pl.org.workout.dtos.Request.AddUserRequest;
import pl.org.workout.dtos.Request.LoginRequest;
import pl.org.workout.dtos.Response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponse> getAll();

    Optional<UserResponse> get(String userId);

    String signIn(LoginRequest loginRequest);

    UserResponse addUser(AddUserRequest addUserRequest);

    void remove(String userId);
}
