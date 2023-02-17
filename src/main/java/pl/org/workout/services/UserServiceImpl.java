package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Request.AddUserRequest;
import pl.org.workout.dtos.Request.LoginRequest;
import pl.org.workout.dtos.Response.JwtResponse;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.UserResponse;
import pl.org.workout.enitities.Profile;
import pl.org.workout.enitities.User;
import pl.org.workout.exceptions.EntityNotFoundException;
import pl.org.workout.repositories.UserRepository;
import pl.org.workout.security.JwtTokenUtil;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    JwtTokenUtil jwtTokenUtil;
    AuthenticationManager authenticationManager;

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(UserResponse::from).toList();
    }

    @Override
    public UserResponse get(String username) throws EntityNotFoundException {
        return UserResponse.from(userRepository.findUserByUsername(username).
                orElseThrow(() -> new EntityNotFoundException(User.class)));
    }

    @Override
    public JwtResponse signIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenUtil.generateJwtToken(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            return new JwtResponse(jwt,
                    userDetails.getUsername(),
                    roles);
    }
    @Override
    public MessageResponse addUser(AddUserRequest addUserRequest) {
        if(Boolean.TRUE.equals(userRepository.existsByUsername(addUserRequest.getUsername())))
        {
            return MessageResponse.builder()
                    .message("[ERROR] Username: " + addUserRequest.getUsername() + " is already taken")
                    .build();
        }
        if(Boolean.TRUE.equals(userRepository.existsByEmail(addUserRequest.getEmail())))
        {
            return MessageResponse.builder()
                    .message("[ERROR] Email: " + addUserRequest.getEmail() + " is already taken")
                    .build();
        }
        Profile profile = Profile.builder()
                .firstname("")
                .lastname("")
                .height(0.0)
                .weight(0.0)
                .trainingGoal("")
                .trainings(new ArrayList<>())
                .build();

        User user = User.builder()
                .email(addUserRequest.getEmail())
                .username(addUserRequest.getUsername())
                .password(addUserRequest.getPassword())
                .createDate(Instant.now())
                .profile(profile)
                .build();

        userRepository.save(user);

        return MessageResponse.builder()
                .message("User: " + addUserRequest.getUsername() + " created successfully").
                build();
    }
    @Override
    public void remove(String userId) {
        userRepository.deleteUserById(userId);
    }
}
