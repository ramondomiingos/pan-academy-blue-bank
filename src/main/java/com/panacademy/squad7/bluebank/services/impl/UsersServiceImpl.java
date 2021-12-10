package com.panacademy.squad7.bluebank.services.impl;

import com.panacademy.squad7.bluebank.domain.models.User;
import com.panacademy.squad7.bluebank.domain.repositories.UsersRepository;
import com.panacademy.squad7.bluebank.exceptions.ContentNotFoundException;
import com.panacademy.squad7.bluebank.services.UsersService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    public static final String NOT_FOUND_MESSAGE = "user not found with id ";
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository UsersRepository) {
        this.usersRepository = UsersRepository;
    }

    @Override
    public User create(User user) {
        return usersRepository.save(user);
    }


    @Override
    public User update(User user, Long id) {
        return usersRepository.findById(id).map(a -> {
            user.setId(id);
            return usersRepository.save(user);
        }).orElseThrow(() -> new ContentNotFoundException(NOT_FOUND_MESSAGE + id));
    }

    @Override
    public void delete(Long id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
        } else {
            throw new ContentNotFoundException(NOT_FOUND_MESSAGE + id);
        }
    }

    @Override
    public User findById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException(NOT_FOUND_MESSAGE + id));
    }

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
    }
}