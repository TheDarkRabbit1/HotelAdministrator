package com.example.hoteladministrator.repositories;

import com.example.hoteladministrator.entities.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    @Override
    Optional<RoomType> findById(Long Long);

    @Override
    List<RoomType> findAll();

    Optional<RoomType> findRoomTypeByTypeName(String name);
}
