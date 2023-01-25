package pl.org.workout.dtos.Response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.org.workout.enitities.Excercise;
import pl.org.workout.enitities.Training;


import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TrainingResponse {
    Long id;
    LocalDate date;
    Duration duration;
    List<Excercise> excercises;
    public static TrainingResponse from(Training training){
        return new TrainingResponse(
                training.getId(),
                training.getDate(),
                training.getDuration(),
                training.getExcercises()
        );
    }
}
