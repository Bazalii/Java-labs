package com.itmo.cats.owners.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OwnerDto extends JpaRepository<OwnerDbModel, Integer> {
}
