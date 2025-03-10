package com.furkanpatat.service.impl;

import com.furkanpatat.dto.DtoUser;
import com.furkanpatat.jwt.AuthRequest;
import com.furkanpatat.jwt.AuthResponse;
import com.furkanpatat.jwt.JwtService;
import com.furkanpatat.model.RefreshToken;
import com.furkanpatat.model.User;
import com.furkanpatat.repository.RefreshTokenRepository;
import com.furkanpatat.repository.UserRepository;
import com.furkanpatat.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
        refreshToken.setUser(user);

        return refreshToken;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
            authenticationProvider.authenticate(auth);

            Optional<User> optional = userRepository.findByUsername(request.getUsername());
            String accessToken = jwtService.generateToken(optional.get());

            RefreshToken refreshToken = createRefreshToken(optional.get());
            refreshTokenRepository.save(refreshToken);
            return new AuthResponse(accessToken,refreshToken.getRefreshToken());
        }catch (Exception e){
            //Exception
            System.out.println("Kullanıcı Adı veya şifre hatalı.");
        }
        return null;
    }

    @Override
    public DtoUser register(AuthRequest request) {
        User user = new User();
        DtoUser dtoUser = new DtoUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);
        BeanUtils.copyProperties(savedUser,dtoUser);
        return dtoUser;
    }


}
