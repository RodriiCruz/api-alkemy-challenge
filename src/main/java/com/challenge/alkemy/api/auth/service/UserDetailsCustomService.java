package com.challenge.alkemy.api.auth.service;

import com.challenge.alkemy.api.auth.dto.UserDTO;
import com.challenge.alkemy.api.auth.entity.UserEntity;
import com.challenge.alkemy.api.auth.repository.IUserRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rodrigo Cruz
 */
@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

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
        userEntity.setPassword(userDTO.getPassword());

        userRepository.save(userEntity);
    }
}
