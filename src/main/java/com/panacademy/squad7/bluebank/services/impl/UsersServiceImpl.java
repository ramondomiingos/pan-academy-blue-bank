package com.panacademy.squad7.bluebank.services.impl;

import com.panacademy.squad7.bluebank.domain.models.User;
import com.panacademy.squad7.bluebank.domain.repositories.UsersRepository;
import com.panacademy.squad7.bluebank.exceptions.ContentNotFoundException;
import com.panacademy.squad7.bluebank.services.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository UsersRepository;

    public UsersServiceImpl(UsersRepository UsersRepository) {
        this.UsersRepository = UsersRepository;
    }

    @Override
    public User create(User User) {
        return UsersRepository.save(User);
    }


    @Override
    public User update(User User, Long id) {
        return UsersRepository.findById(id).map(a -> {
            User.setId(id);
            return UsersRepository.save(User);
        }).orElseThrow(() -> new ContentNotFoundException("User not found with id " + id));
    }

    @Override
    public void delete(Long id) {
        if (UsersRepository.existsById(id)) {
            UsersRepository.deleteById(id);
        } else {
            throw new ContentNotFoundException("User not found with id " + id);
        }
    }

    @Override
    public User findById(Long id) {
        return UsersRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("User not found with id " + id));
    }

    @Override
    public List<User> findAll() {
        return UsersRepository.findAll();
    }
}