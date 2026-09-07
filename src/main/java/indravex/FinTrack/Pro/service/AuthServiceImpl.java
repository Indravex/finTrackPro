package indravex.FinTrack.Pro.service;

import indravex.FinTrack.Pro.dto.LoginRequest;
import indravex.FinTrack.Pro.dto.LoginResponse;
import indravex.FinTrack.Pro.dto.UserDto;
import indravex.FinTrack.Pro.entity.User;
import indravex.FinTrack.Pro.exception.InvalidCredentialsException;
import indravex.FinTrack.Pro.repository.UserRepository;
import indravex.FinTrack.Pro.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), String.valueOf(user.getRole()));

        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getRole());

        return new LoginResponse(true, "Login successful", token, userDto);
    }
}