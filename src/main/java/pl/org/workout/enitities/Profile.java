package pl.org.workout.enitities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String firstname;
    String lastname;
    Double weight;
    Double height;
    String trainingGoal;
    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL)
    List<Training> trainings;
    @OneToOne(mappedBy = "profile")
    User user;
}
