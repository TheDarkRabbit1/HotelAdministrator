package com.example.hoteladministrator.repositories;

import com.example.hoteladministrator.entities.Guest;
import com.example.hoteladministrator.entities.Room;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Override
    List<Room> findAll();

    @Override
    Optional<Room> findById(Long aLong);

    @Override
    <S extends Room> S save(S entity);

    @Override
    void delete(Room entity);

    @Transactional
    default void updateGuests(Long roomId, List<Guest> newGuests) {
        Room room = findById(roomId).orElseThrow(() -> new EntityNotFoundException("Room not found"));
        room.getGuests().clear();
        room.getGuests().addAll(newGuests);
        save(room);
    }
}
