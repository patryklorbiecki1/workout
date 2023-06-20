package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Request.AddTrainingRequest;
import pl.org.workout.dtos.Request.TrainingUpdateRequest;
import pl.org.workout.dtos.Response.TrainingResponse;
import pl.org.workout.enitities.Excercise;
import pl.org.workout.enitities.Training;
import pl.org.workout.enitities.User;
import pl.org.workout.mapper.TrainingMapper;
import pl.org.workout.repositories.ExcerciseRepository;
import pl.org.workout.repositories.TrainingRepository;
import pl.org.workout.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrainingServiceImpl implements TrainingService {
    TrainingRepository trainingRepository;
    ExcerciseRepository excerciseRepository;
    UserRepository userRepository;
    TrainingMapper trainingMapper;

    @Override
    public List<TrainingResponse> getAll() {
        return trainingMapper.toTrainingResponseList(trainingRepository.findAll());
    }

    @Override
    public Optional<TrainingResponse> get(String id) {
        return trainingRepository.findById(id).stream()
                .map(trainingMapper::toTrainingResponse)
                .findFirst();
    }

    @Override
    public TrainingResponse addTraining(AddTrainingRequest addTrainingRequest) {
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
        return trainingMapper.toTrainingResponse(training);
    }

    @Override
    public Optional<TrainingResponse> update(TrainingUpdateRequest request) {
        trainingRepository.findByDate(request.getDate())
                .map(training -> {
                    Optional.ofNullable(request.getExcercises()).ifPresent(training::setExcercises);
                    Optional.ofNullable(request.getDate()).ifPresent(training::setDate);
                    Optional.ofNullable(request.getDuration()).ifPresent(training::setDuration);
                    return trainingRepository.save(training);
                });
        return trainingRepository.findByDate(request.getDate()).stream()
                .map(trainingMapper::toTrainingResponse).findFirst();
    }

    @Override
    public void remove(String trainingId) {
        trainingRepository.deleteById(trainingId);
    }
}
