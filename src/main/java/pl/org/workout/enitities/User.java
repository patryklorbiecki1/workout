package pl.org.workout.enitities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@javax.persistence.Entity
@Table(name = "user")
public class User{
    @Id
    Long id;
    @NonNull
    String email;
    @NonNull
    String password;
    @NonNull
    String username;
}
