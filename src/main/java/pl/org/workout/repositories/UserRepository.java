package pl.org.workout.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.org.workout.enitities.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
}
