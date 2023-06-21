package pl.org.workout.dtos.Request;

import lombok.Builder;


@Builder
public record LoginRequest(String username, String password) {
}
