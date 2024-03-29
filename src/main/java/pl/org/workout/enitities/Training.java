package pl.org.workout.enitities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@Document
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class Training {
    @Id
    String id;
    @NonNull
    LocalDate date;
    @NonNull
    Duration duration;
    @DBRef
    User user;
    @DBRef
    List<Excercise> excercises;
}
