package com.itmo.cats.owners.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerDao extends JpaRepository<OwnerDbModel, Integer> {
}
