package pl.org.workout.mapper;

import org.mapstruct.Mapper;
import pl.org.workout.dtos.Response.ProfileResponse;
import pl.org.workout.enitities.Profile;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileResponse toProfileResponse(Profile profile);

}
