package pl.org.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.org.workout.enitities.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training,Long> {
}
