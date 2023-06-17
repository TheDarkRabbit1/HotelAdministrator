package com.example.hoteladministrator.repositories;

import com.example.hoteladministrator.entities.PayCheck;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PayCheckRepository extends JpaRepository<PayCheck,Long> {
    @Override
    <S extends PayCheck> S save(S entity);

    @Query("select p from PayCheck p where p.room.id = ?1 order by p.room.id desc")
    Optional<PayCheck> getLastPayCheckByRoomId(long roomId);


    @Modifying
    @Transactional
    @Query("update PayCheck p set p.extendedStayDate=?1 where p.room.id=?2")
    void setExtendStayDateByRoomId(LocalDate date, long roomId);
}
