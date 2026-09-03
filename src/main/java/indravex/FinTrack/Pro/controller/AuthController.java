package indravex.FinTrack.Pro.controller;


import indravex.FinTrack.Pro.dto.LoginRequest;
import indravex.FinTrack.Pro.dto.LoginResponse;
import indravex.FinTrack.Pro.dto.RegisterRequest;
import indravex.FinTrack.Pro.dto.UserDto;
import indravex.FinTrack.Pro.service.AuthService;
import indravex.FinTrack.Pro.service.UserService;
import indravex.FinTrack.Pro.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDto>> register(@Valid @RequestBody RegisterRequest request) {

        UserDto created = userService.createUser(request);
        ApiResponse<UserDto> response = new ApiResponse<>(true, "User registered successfully", created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Object>> logout() {
        // JWT is stateless — logout is handled client-side by discarding the token.
        // (Optional: implement a token blacklist here if you need server-side invalidation.)
        return ResponseEntity.ok(new ApiResponse<>(true, "Logout successful", null));
    }
}