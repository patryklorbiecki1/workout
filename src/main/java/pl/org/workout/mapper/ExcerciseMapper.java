package pl.org.workout.mapper;

import org.mapstruct.Mapper;
import pl.org.workout.dtos.Response.ExcerciseResponse;
import pl.org.workout.enitities.Excercise;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ExcerciseMapper {

    ExcerciseResponse toExcerciseResponse(Optional<Excercise> excercise);
    List<ExcerciseResponse> toExcerciseResponseList(List<Excercise> excercises);
}
