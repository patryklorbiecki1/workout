package pl.org.workout.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.org.workout.enitities.Excercise;
@Repository
public interface ExcerciseRepository extends MongoRepository<Excercise,String> {
}
