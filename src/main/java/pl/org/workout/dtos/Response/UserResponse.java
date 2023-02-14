package pl.org.workout.dtos.Response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.org.workout.enitities.User;

@Getter
@AllArgsConstructor
@Builder
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String email;
    String password;
    public static UserResponse from(User user){
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }
}
