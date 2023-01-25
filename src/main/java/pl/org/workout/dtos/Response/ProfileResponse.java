package pl.org.workout.dtos.Response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.org.workout.enitities.Profile;
import pl.org.workout.enitities.Training;
import java.util.List;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProfileResponse {
    String firstname;
    String lastname;
    Double weight;
    Double height;
    String trainingGoal;
    List<Training> trainings;
    public static ProfileResponse from(Profile profile){
        return new ProfileResponse(
                profile.getFirstname(),
                profile.getLastname(),
                profile.getWeight(),
                profile.getHeight(),
                profile.getTrainingGoal(),
                profile.getTrainings()
        );
    }
}
