package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Request.ProfileUpdateRequest;
import pl.org.workout.dtos.Response.ProfileResponse;
import pl.org.workout.enitities.Profile;
import pl.org.workout.enitities.User;
import pl.org.workout.mapper.ProfileMapper;
import pl.org.workout.repositories.ProfileRepository;
import pl.org.workout.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileServiceImpl implements ProfileService {

    UserRepository userRepository;
    ProfileRepository profileRepository;
    ProfileMapper profileMapper;

    @Override
    public ProfileResponse updateInfo(ProfileUpdateRequest request) {
        Optional<User> user = userRepository.findUserByEmail(request.getEmail());
        user.ifPresent(u -> {
            Profile profile = user.orElseThrow().getProfile();
            profileRepository.delete(profile);
            Optional.ofNullable(request.getFirstname()).ifPresent(profile::setFirstname);
            Optional.ofNullable(request.getLastname()).ifPresent(profile::setLastname);
            Optional.ofNullable(request.getWeight()).ifPresent(profile::setWeight);
            Optional.ofNullable(request.getHeight()).ifPresent(profile::setHeight);
            Optional.ofNullable(request.getTrainingGoal()).ifPresent(profile::setTrainingGoal);
            Optional.ofNullable(request.getTrainings()).ifPresent(profile::setTrainings);
            user.orElseThrow().setProfile(profile);
            profileRepository.save(profile);
            userRepository.save(u);
        });
        return user.map(u -> profileMapper.toProfileResponse(u.getProfile())).orElseThrow();
    }

    @Override
    public ProfileResponse get(String email) {
        return ProfileResponse.from(userRepository.findUserByEmail(email).orElseThrow().getProfile());
    }

    @Override
    public List<ProfileResponse> getAll() {
        return profileRepository.findAll().stream().map(ProfileResponse::from).collect(Collectors.toList());
    }
}
