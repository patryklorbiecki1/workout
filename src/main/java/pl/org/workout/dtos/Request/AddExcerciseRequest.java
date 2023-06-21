package pl.org.workout.dtos.Request;

public record AddExcerciseRequest(String name, Integer reps, Double weight, Integer sets) {
}
