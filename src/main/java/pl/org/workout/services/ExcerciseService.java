package pl.org.workout.services;

import pl.org.workout.dtos.Request.AddExcerciseRequest;
import pl.org.workout.dtos.Response.TrainingResponse;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.exceptions.EntityNotFoundException;

import java.util.List;

public interface ExcerciseService {
    List<TrainingResponse> getAll();
    TrainingResponse get(Long excerciseId) throws EntityNotFoundException;
    MessageResponse add(AddExcerciseRequest addExcerciseRequest);
    TrainingResponse update();
    TrainingResponse remove(Long excerciseId);
}
