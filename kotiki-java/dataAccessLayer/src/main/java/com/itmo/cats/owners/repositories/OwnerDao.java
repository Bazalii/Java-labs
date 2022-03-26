package com.itmo.cats.owners.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerDao extends JpaRepository<OwnerDbModel, Integer> {
}
