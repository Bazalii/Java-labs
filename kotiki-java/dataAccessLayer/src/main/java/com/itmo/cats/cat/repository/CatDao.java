package com.itmo.cats.cat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatDao extends JpaRepository<CatDbModel, Integer> {
    @Query("select c from CatDbModel c where c._owner._id = ?1")
    List<CatDbModel> findAllByOwnerId(int userId);
}
