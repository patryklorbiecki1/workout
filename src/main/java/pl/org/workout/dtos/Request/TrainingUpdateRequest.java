package pl.org.workout.dtos.Request;

import jakarta.validation.constraints.Null;
import pl.org.workout.enitities.Excercise;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;


public record TrainingUpdateRequest(@Null LocalDate date, @Null Duration duration, @Null List<Excercise> excercises) {
}
