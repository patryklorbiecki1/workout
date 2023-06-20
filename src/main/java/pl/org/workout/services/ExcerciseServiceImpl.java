package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Request.AddExcerciseRequest;
import pl.org.workout.dtos.Request.ExcerciseUpdateRequest;
import pl.org.workout.dtos.Response.ExcerciseResponse;
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
        return excerciseRepository.findById(excerciseId).stream()
                .map(excerciseMapper::toExcerciseResponse).findFirst();
    }

    @Override
    public ExcerciseResponse add(AddExcerciseRequest request) {
        Excercise excercise = Excercise.builder()
                .name(request.getName())
                .reps(request.getReps())
                .sets(request.getSets())
                .weight(request.getWeight())
                .build();
        excerciseRepository.save(excercise);
        return excerciseMapper.toExcerciseResponse(excercise);
    }

    @Override
    public Optional<ExcerciseResponse> update(ExcerciseUpdateRequest request) {
        Optional<Excercise> excercise = excerciseRepository.findById(request.id());
        excercise.ifPresent(ex ->{
            Optional.ofNullable(request.name()).ifPresent(ex::setName);
            Optional.ofNullable(request.reps()).ifPresent(ex::setReps);
            Optional.ofNullable(request.sets()).ifPresent(ex::setSets);
            Optional.ofNullable(request.weight()).ifPresent(ex::setWeight);
            excerciseRepository.save(ex);
        });

        return excerciseRepository.findById(request.id()).stream()
                .map(excerciseMapper::toExcerciseResponse).findFirst();
    }

    @Override
    public void remove(String excerciseId) {
        excerciseRepository.deleteById(excerciseId);
    }
}
