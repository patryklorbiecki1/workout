package pl.org.workout;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.org.workout.controllers.UserController;
import pl.org.workout.dtos.Request.AddUserRequest;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.UserResponse;
import pl.org.workout.security.JwtTokenUtil;
import pl.org.workout.services.UserService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(UserController.class)
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private PasswordEncoder encoder;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @WithMockUser(username="user")
    public void givenListOfUsers_whenGetAllUsers_thenReturnUsersList() throws Exception{
        List<UserResponse> users = new ArrayList<>();
        users.add(UserResponse.builder().id("1").email("adam@gmail.com").username("adamsky").build());
        users.add(UserResponse.builder().id("2").email("pawel@gmail.com").username("pawelek").build());
        given(userService.getAll()).willReturn(users);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/all"));

        response.andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @WithMockUser(username="user")
    public void givenUserId_whenGetUser_thenReturnUserObject() throws Exception{
        String userId = "id";
        UserResponse userResponse = UserResponse.builder()
                .id("id")
                .email("adam@gmail.com")
                .username("adamsky")
                .build();
        given(userService.get(userId)).willReturn(userResponse);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{id}",userId));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id",is(userResponse.id())))
                .andExpect(jsonPath("$.username",is(userResponse.username())))
                .andExpect(jsonPath("$.email",is(userResponse.email())));
    }

    @Test
    @WithMockUser(username = "user")
    public void givenUserObject_whenCreateUser_thenReturnSavedUser() throws Exception{
        AddUserRequest addUserRequest = new AddUserRequest("adamsky","adam@gmail.com","secret");
        MessageResponse messageResponse = MessageResponse.builder().message("User: "+addUserRequest.getUsername()+" created successfully").build();
        given(userService.addUser(addUserRequest)).willReturn(messageResponse);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/add_user/")
                .content(objectMapper.writeValueAsString(addUserRequest))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message",is("User: " + addUserRequest.getUsername() + " created successfully")));
    }

}
