package pl.org.workout.mapper;

import org.mapstruct.Mapper;
import pl.org.workout.dtos.Response.TrainingResponse;
import pl.org.workout.enitities.Training;

import java.util.List;
import java.util.Optional;
@Mapper(componentModel = "spring")
public interface TrainingMapper {
    TrainingResponse toTrainingResponse(Optional<Training> training);
    List<TrainingResponse> toTrainingResponseList(List<Training> trainings);

}
