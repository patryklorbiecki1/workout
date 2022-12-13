package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Request.AddUserRequest;
import pl.org.workout.dtos.Request.LoginRequest;
import pl.org.workout.dtos.Response.JwtResponse;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.UserResponse;
import pl.org.workout.enitities.User;
import pl.org.workout.exceptions.EntityNotFoundException;
import pl.org.workout.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(UserResponse::from).toList();
    }

    @Override
    public UserResponse get(Long userId) throws EntityNotFoundException {
        return UserResponse.from(userRepository.findById(userId).
                orElseThrow(() -> new EntityNotFoundException(User.class)));
    }

    @Override
    public JwtResponse signIn(LoginRequest loginRequest) {
        return null;
    }
    @Override
    public MessageResponse addUser(AddUserRequest addUserRequest) {
        return null;
    }

    @Override
    public UserResponse update() {
        return null;
    }

    @Override
    public UserResponse remove(Long userId) {
        return null;
    }
}
