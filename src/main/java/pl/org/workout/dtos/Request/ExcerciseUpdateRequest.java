package pl.org.workout.dtos.Request;

public record ExcerciseUpdateRequest(String id, String name, Integer reps, Double weight, Integer sets) {
}
