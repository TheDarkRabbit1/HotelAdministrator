package com.example.hoteladministrator.repositories;

import com.example.hoteladministrator.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Long> {
    @Transactional
    @Modifying
    @Query("update Guest g set g.firstName = ?1, g.lastName = ?2, g.phoneNumber = ?3, g.dateOfBirth = ?4, g.arrivalDate = ?5, g.departureDate = ?6 where g.id = ?7")
    void updateGuest(String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth, LocalDate arrivalDate, LocalDate departureDate, Long guestId);

    @Override
    List<Guest> findAll();
    @Override
    Optional<Guest> findById(Long id);

    @Override
    <S extends Guest> S save(S entity);

    @Override
    void delete(Guest entity);


}
