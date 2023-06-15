package pl.org.workout.dtos.Response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.org.workout.enitities.Excercise;
import pl.org.workout.enitities.Training;


import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public record TrainingResponse(LocalDate date, Duration duration, List<Excercise> excercises) { }
