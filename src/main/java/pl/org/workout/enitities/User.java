package pl.org.workout.enitities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Document
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class User {
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
    Instant createDate;
    @DBRef
    Profile profile;

    public enum Roles {
        ROLE_USER, ROLE_ADMIN, ROLE_MOD
    }

    @NonNull
    Set<Roles> roles = new HashSet<>();
}
