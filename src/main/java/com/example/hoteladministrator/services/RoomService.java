package com.example.hoteladministrator.services;

import com.example.hoteladministrator.entities.Guest;
import com.example.hoteladministrator.entities.Room;
import com.example.hoteladministrator.entities.RoomType;
import com.example.hoteladministrator.repositories.RoomRepository;
import com.example.hoteladministrator.repositories.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;

    public Room getRoomById(long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isEmpty()){
            throw new RuntimeException("no room by id");
        }
        return room.get();
    }
    public List<Room> getRoomList(){
        return roomRepository.findAll();
    }
    public Room addRoom(Room room, String roomTypeName){
        Optional<RoomType> roomType = roomTypeRepository.findRoomTypeByTypeName(roomTypeName);
        if (roomType.isEmpty())
            throw new RuntimeException("No such Room Type");
        room.setRoomType(roomType.get());
        return roomRepository.save(room);
    }
    public void deleteRoomById(long id){
        roomRepository.deleteById(id);
    }
    public void deleteRoom(Room room){
        roomRepository.delete(room);
    }

    public List<RoomType> getRoomTypes() {
        return roomTypeRepository.findAll();
    }

    public RoomType getRoomTypeById(long id){
        Optional<RoomType> roomType = roomTypeRepository.findById(id);
        if (roomType.isEmpty()){
            throw new RuntimeException("No such Room Type");
        }
        return roomType.get();
    }
}
