package pl.org.workout.dtos.Response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.org.workout.enitities.Excercise;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ExcerciseResponse {
    String id;
    String name;
    Integer reps;
    Double weight;
    Integer sets;
    public static ExcerciseResponse from(Excercise excercise){
        return new ExcerciseResponse(
                excercise.getId(),
                excercise.getName(),
                excercise.getReps(),
                excercise.getWeight(),
                excercise.getSets());
    }
}
