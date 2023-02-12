package pl.org.workout.services;

import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Request.AddUserRequest;
import pl.org.workout.dtos.Request.LoginRequest;
import pl.org.workout.dtos.Response.JwtResponse;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.UserResponse;
import pl.org.workout.enitities.User;
import pl.org.workout.repositories.UserRepository;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();
    UserResponse get(String userId) throws Exception;
    JwtResponse signIn(LoginRequest loginRequest);
    MessageResponse addUser(AddUserRequest addUserRequest);
    void remove(String userId);
}
