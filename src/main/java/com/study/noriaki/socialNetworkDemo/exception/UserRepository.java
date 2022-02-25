package com.study.noriaki.socialNetworkDemo.exception;

import com.study.noriaki.socialNetworkDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.name like %:name%")
    List<User> findByName(@Param("name") String name);

    List<User> findByLoginAndPassword(String login, String password);
}