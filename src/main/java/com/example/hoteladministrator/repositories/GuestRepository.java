package com.example.hoteladministrator.repositories;

import com.example.hoteladministrator.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Long> {
    @Override
    List<Guest> findAll();
    @Override
    Optional<Guest> findById(Long id);

    Optional<Guest> findGuestByArrivalDate(LocalDate arrivalDate);
    Optional<Guest> findGuestByDepartureDate(LocalDate departureDate);
    Optional<Guest> findGuestByArrivalDateAndDepartureDate(LocalDate arrivalDate, LocalDate departureDate);

    @Override
    <S extends Guest> S save(S entity);

    @Override
    void delete(Guest entity);



}
