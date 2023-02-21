package pl.org.workout.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddExcerciseRequest {
    String name;
    Integer reps;
    Double weight;
    Integer sets;
}
