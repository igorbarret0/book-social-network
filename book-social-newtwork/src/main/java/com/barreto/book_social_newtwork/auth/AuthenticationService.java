package com.barreto.book_social_newtwork.auth;

import com.barreto.book_social_newtwork.role.RoleRepository;
import com.barreto.book_social_newtwork.security.JwtService;
import com.barreto.book_social_newtwork.user.User;
import com.barreto.book_social_newtwork.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(RegisterRequest request) {


        var userRole = roleRepository.findByName("USER")
                // todo - better exception handling
                .orElseThrow(() -> new RuntimeException("Role USER was not initialized"));

        var newUser = new User();
        newUser.setFirstNane(request.firstName());
        newUser.setLastName(request.lastName());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setAccountLocked(false);
        newUser.setEnabled(true);
        newUser.setRoles(List.of(userRole));

        userRepository.save(newUser);
    }


    public LoginResponse login(LoginRequest request) {

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        var claims = new HashMap<String, Object>();
        var user = (User) auth.getPrincipal();
        claims.put("fullname", user.getFirstNane());

        var jwtToken = jwtService.generateToken(claims, user);

        return new LoginResponse(jwtToken);
    }
}
