package com.itmo.cats.repository;

import com.itmo.cats.dbmodels.owner.OwnerDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerDao extends JpaRepository<OwnerDbModel, Integer> {
}
