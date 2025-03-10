package com.furkanpatat.controller;


import com.furkanpatat.dto.DtoUser;
import com.furkanpatat.jwt.AuthRequest;
import com.furkanpatat.jwt.AuthResponse;
import com.furkanpatat.jwt.RefreshTokenRequest;

public interface IRestAuthController {
    public DtoUser register(AuthRequest request);
    public AuthResponse authenticate(AuthRequest request);
    public AuthResponse refreshToken(RefreshTokenRequest request);
}
