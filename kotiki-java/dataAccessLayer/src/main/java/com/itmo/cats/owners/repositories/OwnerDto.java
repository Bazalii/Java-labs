package com.itmo.cats.owners.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerDto extends JpaRepository<OwnerDbModel, Integer> {
}
