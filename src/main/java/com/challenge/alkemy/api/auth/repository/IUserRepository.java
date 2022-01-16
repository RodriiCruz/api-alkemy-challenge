package com.challenge.alkemy.api.auth.repository;

import com.challenge.alkemy.api.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Rodrigo Cruz
 */
public interface IUserRepository extends JpaRepository<UserEntity, Long>{
    UserEntity findByUsername(String username);
}
