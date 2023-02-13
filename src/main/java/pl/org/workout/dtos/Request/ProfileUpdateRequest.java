package pl.org.workout.dtos.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.org.workout.enitities.Training;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import java.util.List;

@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProfileUpdateRequest {
    @NotNull
    String email;
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
