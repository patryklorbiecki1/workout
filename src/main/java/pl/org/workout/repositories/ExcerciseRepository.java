package pl.org.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.org.workout.enitities.Excercise;
@Repository
public interface ExcerciseRepository extends JpaRepository<Excercise,Long> {
}
