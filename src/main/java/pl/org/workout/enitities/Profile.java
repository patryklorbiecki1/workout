package pl.org.workout.enitities;

import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class Profile {
    @Id
    String id;
    @Null
    String firstname;
    @Null
    String lastname;
    @Null
    Double weight;
    @Null
    Double height;
    @Null
    String trainingGoal;
    @Null
    List<Training> trainings;

}
