package com.study.noriaki.socialNetworkDemo.service;

import com.study.noriaki.socialNetworkDemo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User object);

    Optional<User> findById(long id);

    User getById(long id);

    List<User> findAll();

    List<User> findByName(String name);

    List<User> findByLoginAndPassword(String login, String password);

}