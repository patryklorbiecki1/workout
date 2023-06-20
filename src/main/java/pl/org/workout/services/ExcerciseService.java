package pl.org.workout.services;

import pl.org.workout.dtos.Request.AddExcerciseRequest;
import pl.org.workout.dtos.Request.ExcerciseUpdateRequest;
import pl.org.workout.dtos.Response.ExcerciseResponse;

import java.util.List;
import java.util.Optional;

public interface ExcerciseService {
    Optional<List<ExcerciseResponse>> getAll();
    Optional<ExcerciseResponse> getExcerciseById(String excerciseId);
    ExcerciseResponse add(AddExcerciseRequest addExcerciseRequest);
    ExcerciseResponse update(ExcerciseUpdateRequest request);
    void remove(String excerciseId);
}
