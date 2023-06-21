package pl.org.workout.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.org.workout.enitities.Training;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TrainingRepository extends MongoRepository<Training, String> {
    Optional<Training> findByDate(LocalDate date);
}
