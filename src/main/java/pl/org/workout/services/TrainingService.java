package pl.org.workout.services;

import pl.org.workout.dtos.Request.AddTrainingRequest;
import pl.org.workout.dtos.Request.TrainingUpdateRequest;
import pl.org.workout.dtos.Response.TrainingResponse;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.exceptions.EntityNotFoundException;

import java.util.List;

public interface TrainingService {
    List<TrainingResponse> getAll();
    TrainingResponse get(String trainingId) throws EntityNotFoundException;
    MessageResponse addTraining(AddTrainingRequest addTrainingRequest);
    TrainingResponse update(TrainingUpdateRequest request) throws EntityNotFoundException;
    void remove(String trainingId);
}
