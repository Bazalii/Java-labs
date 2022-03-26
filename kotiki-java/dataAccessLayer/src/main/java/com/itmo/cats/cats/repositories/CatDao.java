package com.itmo.cats.cats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatDao extends JpaRepository<CatDbModel, Integer> {
}
