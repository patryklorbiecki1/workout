package pl.org.workout.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.org.workout.enitities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findUserByUsername(String username);
    void deleteUserById(String id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findUserByEmail(String email);

}
