package pl.org.workout.services;

import pl.org.workout.dtos.Request.AddExcerciseRequest;
import pl.org.workout.dtos.Request.ExcerciseUpdateRequest;
import pl.org.workout.dtos.Response.ExcerciseResponse;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.exceptions.EntityNotFoundException;

import java.util.List;

public interface ExcerciseService {
    List<ExcerciseResponse> getAll();
    ExcerciseResponse getExcerciseById(String excerciseId) throws EntityNotFoundException;
    MessageResponse add(AddExcerciseRequest addExcerciseRequest);
    ExcerciseResponse update(ExcerciseUpdateRequest request);
    void remove(String excerciseId);
}
