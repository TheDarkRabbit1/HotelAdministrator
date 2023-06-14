package com.example.hoteladministrator.controllers;

import com.example.hoteladministrator.entities.Guest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestController {
    @GetMapping
    public String guestList(Model model){
        Guest guest = new Guest();
        guest.setId(1L);
        guest.setFirstName("John");
        guest.setLastName("Doe");

        Guest guest1 = new Guest();
        guest1.setId(2L);
        guest1.setFirstName("Hor");
        guest1.setLastName("Lee");
        List<Guest> guests = new ArrayList<>();
        guests.add(guest);
        guests.add(guest1);
        model.addAttribute("guest",guests);
        return "guest/guests";
    }
    @GetMapping("/guestForm")
    public String guestForm(){
        return "guest/guestForm";
    }
    @PostMapping("/add")
    public String guestAdd(){
        return "guest/guests";
    }
}
