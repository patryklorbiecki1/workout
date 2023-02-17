package pl.org.workout.enitities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class User{
    @Id
    String id;
    @Indexed(unique = true)
    @NonNull
    String email;
    @NonNull
    String password;
    @Indexed(unique = true)
    @NonNull
    String username;
    @NonNull
    Instant createDate = Instant.now();
    Profile profile;
    public enum Roles{
        USER,ADMIN,MOD
    }
    @NonNull
    Set<Roles> roles = new HashSet<>();
}
