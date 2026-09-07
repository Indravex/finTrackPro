package indravex.FinTrack.Pro.service;


import indravex.FinTrack.Pro.dto.LoginRequest;
import indravex.FinTrack.Pro.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}