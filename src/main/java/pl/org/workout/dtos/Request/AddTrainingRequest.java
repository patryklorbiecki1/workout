package pl.org.workout.dtos.Request;


import java.time.Duration;
import java.time.LocalDate;
import java.util.List;


public record AddTrainingRequest(LocalDate date, Duration duration, List<String> excercisesId, String userId) {
}

