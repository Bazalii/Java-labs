package com.itmo.cats.cats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatDao extends JpaRepository<CatDbModel, Integer> {
}
