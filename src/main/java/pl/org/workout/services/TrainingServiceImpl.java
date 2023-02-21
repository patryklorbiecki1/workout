package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Request.AddTrainingRequest;
import pl.org.workout.dtos.Request.TrainingUpdateRequest;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.TrainingResponse;
import pl.org.workout.enitities.Excercise;
import pl.org.workout.enitities.Training;
import pl.org.workout.enitities.User;
import pl.org.workout.exceptions.EntityNotFoundException;
import pl.org.workout.repositories.ExcerciseRepository;
import pl.org.workout.repositories.TrainingRepository;
import pl.org.workout.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrainingServiceImpl implements TrainingService{
    TrainingRepository trainingRepository;
    ExcerciseRepository excerciseRepository;
    UserRepository userRepository;
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
    public MessageResponse addTraining(AddTrainingRequest addTrainingRequest) {
        User user = userRepository.findById(String.valueOf(new ObjectId(addTrainingRequest.getUserId()))).orElseThrow();
        List<Excercise> excercises = addTrainingRequest.getExcercisesId().stream()
                .map(excerciseId -> excerciseRepository.findById(String.valueOf(new ObjectId(excerciseId))).orElseThrow())
                .collect(Collectors.toList());
        Training training = Training.builder()
                .date(addTrainingRequest.getDate())
                .duration(addTrainingRequest.getDuration())
                .excercises(excercises)
                .user(user)
                .build();
        trainingRepository.save(training);
        return MessageResponse.builder()
                .message("Training added successfully")
                .build();
    }

    @Override
    public TrainingResponse update(TrainingUpdateRequest request) throws EntityNotFoundException {
        Training training = trainingRepository.findByDate(request.getDate())
                .orElseThrow(()->new EntityNotFoundException(Training.class));
        if(request.getExcercises()!=null){
            training.setExcercises(request.getExcercises());
        }
        if(request.getDuration()!=null){
            training.setDuration(request.getDuration());
        }
        if(request.getDate()!=null){
            training.setDate(request.getDate());
        }
        return TrainingResponse.from(trainingRepository.save(training));
    }

    @Override
    public void remove(String trainingId) {
        trainingRepository.deleteById(trainingId);
    }
}
