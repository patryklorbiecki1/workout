package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
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
public class TrainingServiceImpl implements TrainingService {
    TrainingRepository trainingRepository;
    ExcerciseRepository excerciseRepository;
    UserRepository userRepository;
    TrainingMapper trainingMapper;

    public TrainingServiceImpl(TrainingRepository trainingRepository, ExcerciseRepository excerciseRepository,
                               UserRepository userRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.excerciseRepository = excerciseRepository;
        this.userRepository = userRepository;
        this.trainingMapper = trainingMapper;
    }

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
        User user = userRepository.findById(String.valueOf(new ObjectId(addTrainingRequest.userId()))).orElseThrow();
        List<Excercise> excercises = addTrainingRequest.excercisesId().stream()
                .map(excerciseId -> excerciseRepository.findById(String.valueOf(new ObjectId(excerciseId))).orElseThrow())
                .collect(Collectors.toList());
        Training training = Training.builder()
                .date(addTrainingRequest.date())
                .duration(addTrainingRequest.duration())
                .excercises(excercises)
                .user(user)
                .build();
        trainingRepository.save(training);
        return trainingMapper.toTrainingResponse(training);
    }

    @Override
    public Optional<TrainingResponse> update(TrainingUpdateRequest request) {
        trainingRepository.findByDate(request.date())
                .map(training -> {
                    Optional.ofNullable(request.excercises()).ifPresent(training::setExcercises);
                    Optional.ofNullable(request.date()).ifPresent(training::setDate);
                    Optional.ofNullable(request.duration()).ifPresent(training::setDuration);
                    return trainingRepository.save(training);
                });
        return trainingRepository.findByDate(request.date()).stream()
                .map(trainingMapper::toTrainingResponse).findFirst();
    }

    @Override
    public void remove(String trainingId) {
        trainingRepository.deleteById(trainingId);
    }
}
