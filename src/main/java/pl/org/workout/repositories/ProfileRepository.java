package pl.org.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.org.workout.enitities.Profile;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
