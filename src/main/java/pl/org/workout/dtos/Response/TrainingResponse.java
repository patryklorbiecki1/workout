package pl.org.workout.dtos.Response;

import pl.org.workout.enitities.Excercise;


import java.time.Duration;
import java.time.LocalDate;
import java.util.List;


public record TrainingResponse(LocalDate date, Duration duration, List<Excercise> excercises) { }
