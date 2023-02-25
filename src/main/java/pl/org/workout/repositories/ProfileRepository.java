package pl.org.workout.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.org.workout.dtos.Response.ProfileResponse;
import pl.org.workout.enitities.Profile;

@Repository
public interface ProfileRepository extends MongoRepository<Profile,String> {
}
