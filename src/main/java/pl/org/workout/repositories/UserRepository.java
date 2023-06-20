package pl.org.workout.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.org.workout.enitities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findUserByUsername(String username);
    void deleteUserById(String id);
    Optional<User> findUserByEmail(String email);

}
