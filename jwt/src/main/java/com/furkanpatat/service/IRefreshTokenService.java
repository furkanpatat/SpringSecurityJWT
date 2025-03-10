package com.furkanpatat.service;

import com.furkanpatat.jwt.AuthResponse;
import com.furkanpatat.jwt.RefreshTokenRequest;

public interface IRefreshTokenService {
    public AuthResponse refreshToken(RefreshTokenRequest request);
}
