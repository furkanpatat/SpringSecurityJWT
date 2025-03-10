package com.furkanpatat.service.impl;

import com.furkanpatat.jwt.AuthResponse;
import com.furkanpatat.jwt.JwtService;
import com.furkanpatat.jwt.RefreshTokenRequest;
import com.furkanpatat.model.RefreshToken;
import com.furkanpatat.model.User;
import com.furkanpatat.repository.RefreshTokenRepository;
import com.furkanpatat.service.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

   @Autowired
    private RefreshTokenRepository refreshTokenRepository;

   @Autowired
   private JwtService jwtService;

   public RefreshToken createRefreshToken(User user){
       RefreshToken refreshToken = new RefreshToken();
       refreshToken.setRefreshToken(UUID.randomUUID().toString());
       refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
       refreshToken.setUser(user);
       return refreshToken;
   }

   public boolean isRefreshTokenExpired(Date expiredDate){
       return new Date().before(expiredDate);
   }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
        if (optional.isEmpty()){
            //EXCEPTION
            System.out.println("REFRESH TOKEN GEÇERSİZDİR : "+request.getRefreshToken());
        }
        RefreshToken refreshToken = optional.get();
        if (!isRefreshTokenExpired(refreshToken.getExpiredDate())){
            //EXCEPTION
            System.out.println("REFRESH TOKEN EXPIRE OLMUŞTUR : "+request.getRefreshToken());
        }
        String accessToken = jwtService.generateToken(refreshToken.getUser());
        RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(refreshToken.getUser()));
        return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
   }
}
