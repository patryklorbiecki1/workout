package pl.org.workout.services;

import pl.org.workout.dtos.Request.AddTrainingRequest;
import pl.org.workout.dtos.Request.TrainingUpdateRequest;
import pl.org.workout.dtos.Response.TrainingResponse;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.enitities.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingService {
    List<TrainingResponse> getAll();
    TrainingResponse get(String trainingId);
    TrainingResponse addTraining(AddTrainingRequest addTrainingRequest);
    Optional<TrainingResponse> update(TrainingUpdateRequest request);
    void remove(String trainingId);
}
