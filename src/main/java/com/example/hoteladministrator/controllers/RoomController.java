package com.example.hoteladministrator.controllers;

import com.example.hoteladministrator.entities.Guest;
import com.example.hoteladministrator.entities.Room;
import com.example.hoteladministrator.entities.RoomType;
import com.example.hoteladministrator.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;
    @GetMapping
    public String showRooms(Model model) {
        List<Room> rooms = roomService.getRoomList();
        model.addAttribute("rooms",rooms);
        return "room/rooms";
    }
    @GetMapping("/roomInfo")
    public String getRoomInfo(@RequestParam("roomId") long roomId, Model model) {
        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);
        model.addAttribute("guests", room.getGuests());
        return "room/roomInfo";
    }
    @GetMapping("/roomForm")
    public String roomForm(Model model){
        List<RoomType> roomTypeList = roomService.getRoomTypes();
        model.addAttribute("room", new Room());
        model.addAttribute("roomTypeNames",roomTypeList);
        return "room/roomForm";
    }
    @PostMapping("/add")
    public String guestAdd(@ModelAttribute Room room, @RequestParam("roomTypeId") long roomTypeId) {
        roomService.addRoom(room, roomTypeId);
        return "redirect:/rooms";
    }
}

