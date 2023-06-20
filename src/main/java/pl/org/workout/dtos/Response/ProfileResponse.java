package pl.org.workout.dtos.Response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.org.workout.enitities.Training;

import java.util.List;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public record ProfileResponse(String id, String firstname, String lastname, Double weight, Double height,
                              String trainingGoal, List<Training> trainings) {

}
