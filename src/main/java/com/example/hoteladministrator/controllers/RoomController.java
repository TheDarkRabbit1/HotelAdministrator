package com.example.hoteladministrator.controllers;

import com.example.hoteladministrator.entities.Room;
import com.example.hoteladministrator.entities.RoomType;
import com.example.hoteladministrator.services.GuestService;
import com.example.hoteladministrator.services.RoomService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final GuestService guestService;

    @GetMapping
    public String showRooms(Model model) {
        List<Room> rooms = roomService.getRoomList();
        model.addAttribute("rooms",rooms);
        return "room/rooms";
    }
    @GetMapping("/roomInfo")
    public String getRoomInfo(@RequestParam("roomId") long roomId, Model model) {
        Room room = roomService.getRoomById(roomId);

        if (room.getIsBooked()){
            model.addAttribute("room", room);
            model.addAttribute("guests", room.getGuests());
            return "room/roomInfoBooked";
        }else {
            model.addAttribute("room", room);
            model.addAttribute("guests", room.getGuests());
            return "room/roomInfoUnBooked";
        }
    }
    @PostMapping("/bookRoom")
    public String bookRoom(@RequestParam("roomId") long roomId){
        roomService.createPayCheck(roomId);
        roomService.bookRoomById(roomId);
        return "redirect:/rooms/roomInfo?roomId="+roomId;
    }

    @GetMapping("/roomForm")
    public String showRoomForm(Model model) {
        List<RoomType> roomTypes = roomService.getRoomTypes();
        model.addAttribute("roomTypes", roomTypes);
        model.addAttribute("room", new Room());
        return "room/roomForm";
    }
    @PostMapping("/extendBooking")
    public String extendRoomBooking(@RequestParam("roomId") long roomId, @RequestParam("extendBooking") int days){
        roomService.extendBooking(roomId, days);
        return "redirect:/rooms/roomInfo?roomId="+roomId;
    }

    @PostMapping("/add")
    public String addRoom(@ModelAttribute("room") @Valid Room room, BindingResult bindingResult,
                          @RequestParam("roomTypeId") long roomTypeId, Model model) {
        if (bindingResult.hasErrors()) {
            List<RoomType> roomTypes = roomService.getRoomTypes();
            model.addAttribute("roomTypes", roomTypes);
            return "room/roomForm";
        }
        roomService.addRoom(room, roomTypeId);
        return "redirect:/rooms";
    }
    @GetMapping("/bookOut")
    public String bookOut(@RequestParam("roomId") long roomId){
        roomService.getRoomById(roomId)
                .getGuests()
                .forEach(guest -> guestService.deleteGuestById(guest.getId()));
        roomService.bookOutByRoomId(roomId);
        return "redirect:/rooms/roomInfo?roomId="+roomId;
    }

    @PostMapping("/delete")
    public String deleteRoom(@RequestParam("roomId") Long roomId) {
        roomService.deleteRoomById(roomId);
        return "redirect:/rooms";
    }

}

