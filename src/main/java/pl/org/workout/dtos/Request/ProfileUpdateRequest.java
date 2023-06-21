package pl.org.workout.dtos.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import pl.org.workout.enitities.Training;

import java.util.List;


public record ProfileUpdateRequest(@NotNull String email, @Null String firstname, @Null String lastname,
                                   @Null Double weight, @Null Double height, @Null String trainingGoal,
                                   @Null List<Training> trainings) {
}
