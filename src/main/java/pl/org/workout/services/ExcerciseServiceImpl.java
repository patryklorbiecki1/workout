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
import pl.org.workout.mapper.ExcerciseMapper;
import pl.org.workout.repositories.ExcerciseRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class ExcerciseServiceImpl implements ExcerciseService{

    ExcerciseRepository excerciseRepository;
    ExcerciseMapper excerciseMapper;
    @Override
    public Optional<List<ExcerciseResponse>> getAll() {
        return Optional.ofNullable(excerciseMapper.toExcerciseResponseList(excerciseRepository.findAll()));
    }

    @Override
    public Optional<ExcerciseResponse> getExcerciseById(String excerciseId){
        return Optional.ofNullable(excerciseMapper.toExcerciseResponse(excerciseRepository.findById(excerciseId)));
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
