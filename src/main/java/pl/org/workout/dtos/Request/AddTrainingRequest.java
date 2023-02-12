package pl.org.workout.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.org.workout.enitities.Excercise;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTrainingRequest {
    LocalDate date;
    Duration duration;
    List<Excercise> excercises;
}
