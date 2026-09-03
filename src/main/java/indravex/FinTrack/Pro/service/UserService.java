package indravex.FinTrack.Pro.service;

import indravex.FinTrack.Pro.dto.RegisterRequest;
import indravex.FinTrack.Pro.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(RegisterRequest request);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    void deleteUser(Long id);

}