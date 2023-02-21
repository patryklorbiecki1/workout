package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Request.AddExcerciseRequest;
import pl.org.workout.dtos.Request.ExcerciseUpdateRequest;
import pl.org.workout.dtos.Response.ExcerciseResponse;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.enitities.Excercise;
import pl.org.workout.exceptions.EntityNotFoundException;
import pl.org.workout.repositories.ExcerciseRepository;

import java.util.List;
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class ExcerciseServiceImpl implements ExcerciseService{

    ExcerciseRepository excerciseRepository;
    @Override
    public List<ExcerciseResponse> getAll() {
        return excerciseRepository.findAll().stream().map(ExcerciseResponse::from).toList();
    }

    @Override
    public ExcerciseResponse getExcerciseById(String excerciseId) throws EntityNotFoundException {
        return ExcerciseResponse.from(excerciseRepository.findById(excerciseId)
                .orElseThrow(()-> new EntityNotFoundException("Excercise by id:" + excerciseId + " not found")));
    }

    @Override
    public MessageResponse add(AddExcerciseRequest request) {
        Excercise excercise = Excercise.builder()
                .name(request.getName())
                .reps(request.getReps())
                .sets(request.getSets())
                .weight(request.getWeight())
                .build();
        excerciseRepository.save(excercise);
        return MessageResponse.builder()
                .message("Excercise: " + request.getName() + " added successfully")
                .build();
    }

    @Override
    public ExcerciseResponse update(ExcerciseUpdateRequest request) {
        return null;
    }

    @Override
    public void remove(String excerciseId) {
        excerciseRepository.deleteById(excerciseId);
    }
}
