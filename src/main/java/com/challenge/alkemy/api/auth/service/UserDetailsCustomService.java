package com.challenge.alkemy.api.auth.service;

import com.challenge.alkemy.api.auth.dto.AuthenticationRequest;
import com.challenge.alkemy.api.auth.dto.UserDTO;
import com.challenge.alkemy.api.auth.entity.UserEntity;
import com.challenge.alkemy.api.auth.repository.IUserRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rodrigo Cruz
 */
@Service
public class UserDetailsCustomService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;
    private IUserRepository userRepository;
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    

    @Autowired
    public void setAttributes(IUserRepository userRepository, JwtUtils jwtUtils, @Lazy AuthenticationManager authenticationManager, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("No se ha encotrado el usuario ingresado");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    public void save(UserDTO userDTO) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(userEntity);
    }

    public String signin(AuthenticationRequest authRequest) throws Exception {

        UserDetails userDetails;

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            userDetails = (UserDetails) auth.getPrincipal();

            return jwtUtils.generateToken(userDetails);
        } catch (BadCredentialsException e) {
            throw new Exception("Usuario o contraseña incorrectos", e);
        }
    }
}
