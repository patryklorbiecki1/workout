package pl.org.workout.mapper;

import org.mapstruct.Mapper;
import pl.org.workout.dtos.Response.UserResponse;
import pl.org.workout.enitities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);

}
