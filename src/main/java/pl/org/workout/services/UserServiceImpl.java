package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Request.AddUserRequest;
import pl.org.workout.dtos.Request.LoginRequest;
import pl.org.workout.dtos.Response.JwtResponse;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.UserResponse;
import pl.org.workout.enitities.Profile;
import pl.org.workout.enitities.User;
import pl.org.workout.exceptions.EntityNotFoundException;
import pl.org.workout.repositories.ProfileRepository;
import pl.org.workout.repositories.UserRepository;
import pl.org.workout.security.JwtTokenUtil;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    ProfileRepository profileRepository;
    JwtTokenUtil jwtTokenUtil;
    AuthenticationManager authenticationManager;
    PasswordEncoder encoder;

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(UserResponse::from).toList();
    }

    @Override
    public UserResponse get(String username) throws EntityNotFoundException {
        return UserResponse.from(userRepository.findUserByUsername(username));
    }

    @Override
    public MessageResponse signIn(LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authenticationResult;
        try {
            authenticationResult = authenticationManager.authenticate(authToken);
        } catch (AuthenticationException e) {
            return MessageResponse.builder().message("Wrong username or password").build();
        }

        SecurityContextHolder.getContext().setAuthentication(authenticationResult);
        UserDetails userDetails = (UserDetails) authenticationResult.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        String jwtToken = jwtTokenUtil.generateJwtToken(authenticationResult);
        return MessageResponse.builder().message(new JwtResponse(jwtToken,userDetails.getUsername(),roles).toString()).build();
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
                .password(encoder.encode(addUserRequest.getPassword()))
                .createDate(Instant.now())
                .profile(profile)
                .roles(Set.of(User.Roles.ROLE_USER))
                .build();

        userRepository.save(user);
        profileRepository.save(user.getProfile());
        return MessageResponse.builder()
                .message("User: " + addUserRequest.getUsername() + " created successfully")
                .build();
    }

    @Override
    public void remove(String userId) {
        userRepository.deleteUserById(userId);
    }
}
