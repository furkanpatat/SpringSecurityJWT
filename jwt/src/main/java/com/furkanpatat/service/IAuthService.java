package com.furkanpatat.service;

import com.furkanpatat.dto.DtoUser;
import com.furkanpatat.jwt.AuthRequest;
import com.furkanpatat.jwt.AuthResponse;

public interface IAuthService {
    public DtoUser register(AuthRequest request);
    public AuthResponse authenticate(AuthRequest request);
}
