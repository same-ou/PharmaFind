package com.ensam.pharmafind.service;

import com.ensam.pharmafind.entities.Client;
import com.ensam.pharmafind.entities.Token;
import com.ensam.pharmafind.entities.User;
import com.ensam.pharmafind.enums.EmailTemplateName;
import com.ensam.pharmafind.repository.RoleRepository;
import com.ensam.pharmafind.repository.TokenRepository;
import com.ensam.pharmafind.repository.UserRepository;
import com.ensam.pharmafind.dto.responses.AuthenticationResponse;
import com.ensam.pharmafind.util.JwtUtil;
import com.ensam.pharmafind.dto.requests.LoginRequest;
import com.ensam.pharmafind.dto.requests.RegistrationRequest;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    @Transactional
    public void register(RegistrationRequest request) throws MessagingException {
        String roleName = request.getRole() != null ? request.getRole() : "USER";
        var user = Client.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleRepository.findByName(roleName).orElseThrow())
                .accountLocked(false)
                .enabled(false)
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var token = generateAndSaveActivationToken(user);
        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                token,
                "Activate your account"
        );
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationToken(6);
        var token = Token.builder()
                .token(generatedToken)
                .user(user)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }
    private String generateActivationToken(int length) {
        String characters = "0123456789";
        StringBuilder token = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            token.append(characters.charAt(random.nextInt(characters.length())));
        }
        return token.toString();
    }

    public AuthenticationResponse login(LoginRequest request) {
       var auth= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));

        var user = (User) auth.getPrincipal();
        var token = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse activate(String token) throws MessagingException {
        var tokenEntity = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found"));

        if(LocalDateTime.now().isAfter(tokenEntity.getExpiresAt())){
            sendValidationEmail(tokenEntity.getUser());
            throw new IllegalStateException("Token expired, A new one has been sent to your email");
        }
        if (tokenEntity.getValidatedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }
        var user = userRepository.findById(tokenEntity.getUser().getId())
                .orElseThrow(() -> new IllegalStateException("User not found"));
        user.setEnabled(true);
        tokenEntity.setValidatedAt(LocalDateTime.now());
        userRepository.save(user);
        tokenRepository.save(tokenEntity);
        return AuthenticationResponse.builder()
                .token(jwtUtil.generateToken(user))
                .build();
    }
}
