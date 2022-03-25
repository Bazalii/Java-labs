package com.itmo.cats.cats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatsDao extends JpaRepository<CatDbModel, Integer> {
}
