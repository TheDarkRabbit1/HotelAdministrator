package com.example.hoteladministrator.services;

import com.example.hoteladministrator.entities.Guest;
import com.example.hoteladministrator.entities.PayCheck;
import com.example.hoteladministrator.entities.Room;
import com.example.hoteladministrator.entities.RoomType;
import com.example.hoteladministrator.repositories.PayCheckRepository;
import com.example.hoteladministrator.repositories.RoomRepository;
import com.example.hoteladministrator.repositories.RoomTypeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final PayCheckRepository payCheckRepository;

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
    public Room addRoom(Room room, long roomTypeId){
        Optional<RoomType> roomType = roomTypeRepository.findById(roomTypeId);
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
        return roomTypeRepository.findAll().stream().sorted(Comparator.comparing(RoomType::getId)).toList();
    }

    public RoomType getRoomTypeById(long id){
        Optional<RoomType> roomType = roomTypeRepository.findById(id);
        if (roomType.isEmpty()){
            throw new RuntimeException("No such Room Type");
        }
        return roomType.get();
    }

    public void bookRoomById(long roomId) {
        roomRepository.setIsBooked(true,roomId);
    }

    public void extendBooking(long roomId, int days) {
        LocalDate previousDate = getRoomById(roomId).getGuests().stream()
                .map(Guest::getDepartureDate)
                .max(Comparator.naturalOrder())
                .orElseThrow();
        payCheckRepository.setExtendStayDateByRoomId(previousDate.plusDays(days),roomId);
    }

    public void createPayCheck(long roomId) {
        Room room = getRoomById(roomId);
        PayCheck payCheck = new PayCheck();
        payCheck.setRoom(room);
        //gets guest with max departure date
        Guest guest = room.getGuests().stream()
                .max(Comparator.comparing(Guest::getDepartureDate))
                .orElseThrow();
        payCheck.setArrivalDate(guest.getArrivalDate());
        payCheck.setDepartureDate(guest.getDepartureDate());
        payCheck.setGuestFullName(guest.getFirstName()+" "+guest.getLastName());
        payCheck.setPhoneNumber(guest.getPhoneNumber());
        payCheckRepository.save(payCheck);
    }
    public PayCheck getLastPayCheckByRoomId(long roomId){
        Optional <PayCheck> payCheck = payCheckRepository.getLastPayCheckByRoomId(roomId);
        if (payCheck.isEmpty()){
            throw new RuntimeException("No Pay Check Was found for This room");
        }
        return payCheck.get();
    }
    public List<PayCheck> getPayCheckList(){
        return payCheckRepository.findAll().stream().sorted(Comparator.comparing(PayCheck::getId)).toList();
    }

    public void bookOutByRoomId(long roomId) {
        getLastPayCheckByRoomId(roomId).setCheckOutDate(LocalDate.now());

        Room room = getRoomById(roomId);
        room.setIsBooked(false);
        room.setGuests(null);
        roomRepository.save(room);
    }
    @PostConstruct
    private void addBasicRoomTypes(){
        if(roomTypeRepository.findAll().isEmpty()){
            RoomType standard = new RoomType();
            standard.setTypeName("Standard");
            standard.setPricing(100.0);
            roomTypeRepository.save(standard);
            RoomType deluxe = new RoomType();
            deluxe.setTypeName("Deluxe");
            deluxe.setPricing(150.0);
            roomTypeRepository.save(deluxe);
            RoomType suite = new RoomType();
            suite.setTypeName("Suite");
            suite.setPricing(300.0);
            roomTypeRepository.save(suite);
            RoomType psuite = new RoomType();
            psuite.setTypeName("Presidential Suite");
            psuite.setPricing(400.0);
            roomTypeRepository.save(psuite);
        }
    }
}
