package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.ProfileResponse;
import pl.org.workout.enitities.Profile;
import pl.org.workout.exceptions.EntityNotFoundException;
import pl.org.workout.repositories.ProfileRepository;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProfileServiceImpl implements ProfileService{

    ProfileRepository profileRepository;

    @Override
    public MessageResponse updateInfo() {
        return null;
    }

    @Override
    public ProfileResponse get(String id) throws EntityNotFoundException{
        return ProfileResponse.from(profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Profile.class)));
    }
}
