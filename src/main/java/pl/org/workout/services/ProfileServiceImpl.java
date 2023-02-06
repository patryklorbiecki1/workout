package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Request.ProfileUpdateRequest;
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
    public ProfileResponse updateInfo(ProfileUpdateRequest request) throws EntityNotFoundException {
        Profile profile = profileRepository
                .findById(request.getId())
                .orElseThrow(()-> new EntityNotFoundException(Profile.class));
        if(request.getFirstname()!=null){
            profile.setFirstname(request.getFirstname());
        }
        if(request.getLastname()!=null){
            profile.setLastname(request.getLastname());
        }
        if(request.getWeight()!=null){
            profile.setWeight(request.getWeight());
        }
        if(request.getHeight()!=null){
            profile.setHeight(request.getHeight());
        }
        if(request.getTrainingGoal()!=null){
            profile.setTrainingGoal(request.getTrainingGoal());
        }
        return ProfileResponse.from(profileRepository.save(profile));
    }

    @Override
    public ProfileResponse get(String id) throws EntityNotFoundException{
        return ProfileResponse.from(profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Profile.class)));
    }
}
