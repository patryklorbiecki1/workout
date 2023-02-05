package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Request.AddTrainingRequest;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.TrainingResponse;
import pl.org.workout.enitities.Training;
import pl.org.workout.exceptions.EntityNotFoundException;
import pl.org.workout.repositories.TrainingRepository;

import java.util.List;
@Service
//@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrainingServiceImpl implements TrainingService{
    private final TrainingRepository trainingRepository;
    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository){
        this.trainingRepository = trainingRepository;
    }
    @Override
    public List<TrainingResponse> getAll() {
        return trainingRepository.findAll().stream().map(TrainingResponse::from).toList();
    }

    @Override
    public TrainingResponse get(String id) throws EntityNotFoundException {
        return TrainingResponse.from(trainingRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(Training.class)));
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
    public void remove(String trainingId) {
        trainingRepository.deleteById(trainingId);
    }
}
