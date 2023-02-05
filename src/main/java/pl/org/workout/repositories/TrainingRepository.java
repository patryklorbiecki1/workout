package pl.org.workout.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.org.workout.enitities.Training;

@Repository
public interface TrainingRepository extends MongoRepository<Training,String> {
}
