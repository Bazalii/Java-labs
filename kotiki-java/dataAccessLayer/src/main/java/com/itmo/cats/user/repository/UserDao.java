package com.itmo.cats.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserDbModel, Integer> {
    @Query("select u from UserDbModel u where u._username = ?1")
    UserDbModel findByUsername(String username);
}
