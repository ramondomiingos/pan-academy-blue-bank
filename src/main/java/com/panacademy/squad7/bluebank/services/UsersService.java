package com.panacademy.squad7.bluebank.services;

import com.panacademy.squad7.bluebank.domain.models.User;

import java.util.List;

public interface UsersService {

    User create(User user);

    User update(User user, Long id);

    void delete(Long id);

    User findById(Long id);

    List<User> findAll();
}
