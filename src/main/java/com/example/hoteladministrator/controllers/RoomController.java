package com.example.hoteladministrator.controllers;

import com.example.hoteladministrator.entities.Guest;
import com.example.hoteladministrator.entities.Room;
import com.example.hoteladministrator.entities.RoomClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    @GetMapping
    public String showRooms(Model model) {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1L, 2, RoomClass.DELUXE, new LinkedHashSet<>()));
        rooms.add(new Room(2L, 4, RoomClass.SUITE, new LinkedHashSet<>()));
        model.addAttribute("rooms", rooms);
        return "room/rooms";
    }
    @GetMapping("/roomInfo")
    public String getRoomInfo(@RequestParam("roomId") long roomId, Model model) {
        Room room = new Room(roomId, 3, RoomClass.DELUXE, new LinkedHashSet<>());
        List<Guest> guests = new ArrayList<>();

        Guest guest = new Guest();
        guest.setId(1L);
        guest.setFirstName("John");
        guest.setLastName("doe");

        Guest guest1 = new Guest();
        guest1.setId(2L);
        guest1.setFirstName("Bruh");
        guest1.setLastName("Lee");

        guests.add(guest);
        guests.add(guest1);
        model.addAttribute("room", room);
        model.addAttribute("guests", guests);

        return "room/roomInfo";
    }
}

