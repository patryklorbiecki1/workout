package pl.org.workout.services;

import pl.org.workout.dtos.Request.AddUserRequest;
import pl.org.workout.dtos.Request.LoginRequest;
import pl.org.workout.dtos.Request.UserRequest;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.ProfileResponse;
import pl.org.workout.dtos.Response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();
    UserResponse get(String userId) throws Exception;
    MessageResponse signIn(LoginRequest loginRequest);
    MessageResponse addUser(AddUserRequest addUserRequest);
    ProfileResponse getProfileByUser(UserRequest userRequest) throws Exception;
    void remove(String userId);
}
