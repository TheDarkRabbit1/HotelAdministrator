package com.example.hoteladministrator.controllers;

import com.example.hoteladministrator.entities.Guest;
import com.example.hoteladministrator.entities.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/guests")
public class GuestController {
    @GetMapping
    public String guestList(Model model){
        Room room = new Room();
        room.setId(2L);


        Guest guest = new Guest();
        guest.setId(1L);
        guest.setFirstName("John");
        guest.setLastName("Doe");
        guest.setRoom(room);

        Guest guest1 = new Guest();
        guest1.setId(2L);
        guest1.setFirstName("Hor");
        guest1.setLastName("Lee");
        guest1.setRoom(room);
        List<Guest> guests = new ArrayList<>();
        guests.add(guest);
        guests.add(guest1);

        model.addAttribute("guests",guests);
        return "guest/guests";
    }
    @GetMapping("/guestForm")
    public String guestForm(Model model,
                            @RequestParam("roomId") long roomId,
                            @RequestParam(value = "guestId", required = false) Optional<Long> guestId){
        if (guestId.isPresent()){
            Guest guest = new Guest();
            guest.setId(1L);
            guest.setFirstName("John");
            guest.setLastName("Doe");
            guest.setPhoneNumber("+3413141431");
            guest.setDateOfBirth(LocalDate.now());
            guest.setArrivalDate(LocalDate.now());
            guest.setDepartureDate(LocalDate.now());
            model.addAttribute("guest",guest);
        }else{
            model.addAttribute("guest", new Guest());
        }
        model.addAttribute("roomId", roomId);
        return "guest/guestForm";
    }
    @PostMapping("/add")
    public String guestAdd(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String dateOfBirth,
                           @RequestParam String phoneNumber,
                           @RequestParam String arrivalDate,
                           @RequestParam String departureDate,
                           @RequestParam Long roomId) {
        Guest guest = new Guest();
        guest.setFirstName(firstName);
        guest.setLastName(lastName);
        guest.setDateOfBirth(LocalDate.parse(dateOfBirth));
        guest.setPhoneNumber(phoneNumber);
        guest.setArrivalDate(LocalDate.parse(arrivalDate));
        guest.setDepartureDate(LocalDate.parse(departureDate));
        guest.setRoom(new Room());
        System.out.println("GUEST ADDED:" +guest);
        System.out.println("ROOMID TO ADD HIM:"+roomId);
        return "redirect:/rooms/roomInfo?roomId=" + roomId;
    }
    @GetMapping("/guestInfo")
    public String getGuest(Model model, @RequestParam("guestId") long id){
        Guest guest = new Guest();
        guest.setId(id);
        guest.setFirstName("John");
        guest.setLastName("Doe");
        guest.setPhoneNumber("+3984267896");
        guest.setDateOfBirth(LocalDate.now());
        guest.setArrivalDate(LocalDate.now());
        guest.setDepartureDate(LocalDate.now());

        Room room = new Room();
        room.setId(2L);
        guest.setRoom(room);
        model.addAttribute("guest",guest);
        return "guest/guestInfo";
    }
}
