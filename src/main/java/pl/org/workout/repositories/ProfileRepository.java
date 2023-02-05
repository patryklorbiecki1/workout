package pl.org.workout.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.org.workout.enitities.Profile;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<Profile,String> {
}
