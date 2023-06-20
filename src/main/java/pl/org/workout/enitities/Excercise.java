package pl.org.workout.enitities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@AllArgsConstructor
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Excercise {
    @Id
    String id;
    @NonNull
    String name;
    @NonNull
    Integer reps;
    @NonNull
    Double weight;
    @NonNull
    Integer sets;
}
