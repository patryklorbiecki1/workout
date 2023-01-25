package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Request.AddTrainingRequest;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.TrainingResponse;
import pl.org.workout.exceptions.EntityNotFoundException;
import pl.org.workout.repositories.TrainingRepository;

import java.util.List;
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class TrainingServiceImpl implements TrainingService{

    TrainingRepository trainingRepository;
    @Override
    public List<TrainingResponse> getAll() {
        return null;
    }

    @Override
    public TrainingResponse get(Long trainingId) throws EntityNotFoundException {
        return null;
    }

    @Override
    public MessageResponse add(AddTrainingRequest addTrainingRequest) {
        return null;
    }

    @Override
    public TrainingResponse update() {
        return null;
    }

    @Override
    public TrainingResponse remove(Long trainingId) {
        return null;
    }
}
