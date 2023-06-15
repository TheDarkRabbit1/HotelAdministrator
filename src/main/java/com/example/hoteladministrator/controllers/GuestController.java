package com.example.hoteladministrator.controllers;

import com.example.hoteladministrator.entities.Guest;
import com.example.hoteladministrator.entities.Room;
import com.example.hoteladministrator.repositories.RoomRepository;
import com.example.hoteladministrator.services.GuestService;
import com.example.hoteladministrator.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/guests")
@AllArgsConstructor
public class GuestController {
    private final GuestService guestService;
    private final RoomService roomService;

    @GetMapping
    public String guestList(Model model){
        List<Guest> guests = guestService.getGuestList();
        model.addAttribute("guests",guests);
        return "guest/guests";
    }
    @GetMapping("/guestForm")
    public String guestForm(Model model,
                            @RequestParam("roomId") long roomId,
                            @RequestParam(value = "guestId", required = false) Optional<Long> guestId){
        if (guestId.isPresent()){
            Guest guest = guestService.getGuestById(guestId.get());
            model.addAttribute("guest",guest);
        }else{
            model.addAttribute("guest", new Guest());//todo:try removing
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
        guest.setRoom(roomService.getRoomById(roomId));
        if (guestService.addGuest(guest)==null){
            //todo: validate input
        }
        return "redirect:/rooms/roomInfo?roomId=" + roomId;
    }
    @GetMapping("/guestInfo")
    public String getGuest(Model model, @RequestParam("guestId") long id){
        Guest guest = guestService.getGuestById(id);
        model.addAttribute("guest",guest);
        return "guest/guestInfo";
    }
}
