package com.panacademy.squad7.bluebank.domain.repositories;

import com.panacademy.squad7.bluebank.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

