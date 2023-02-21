package pl.org.workout.dtos.Request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import pl.org.workout.enitities.Excercise;

import javax.validation.constraints.Null;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TrainingUpdateRequest {
    @Null
    LocalDate date;
    @Null
    Duration duration;
    @Null
    List<Excercise> excercises;
}
