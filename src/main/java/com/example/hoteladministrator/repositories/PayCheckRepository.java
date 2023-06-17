package com.example.hoteladministrator.repositories;

import com.example.hoteladministrator.entities.PayCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayCheckRepository extends JpaRepository<PayCheck,Long> {
    @Override
    <S extends PayCheck> S save(S entity);
}
