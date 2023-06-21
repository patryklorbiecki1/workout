package pl.org.workout.mapper;

import org.mapstruct.Mapper;
import pl.org.workout.dtos.Response.TrainingResponse;
import pl.org.workout.enitities.Training;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrainingMapper {

    TrainingResponse toTrainingResponse(Training training);

    List<TrainingResponse> toTrainingResponseList(List<Training> trainings);

}
