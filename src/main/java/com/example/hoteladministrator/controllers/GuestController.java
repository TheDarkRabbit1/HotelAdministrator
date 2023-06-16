package com.example.hoteladministrator.controllers;

import com.example.hoteladministrator.entities.Guest;
import com.example.hoteladministrator.services.GuestService;
import com.example.hoteladministrator.services.RoomService;
import com.example.hoteladministrator.validators.GuestFormValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/guests")
@AllArgsConstructor
public class GuestController {
    private final GuestService guestService;
    private final RoomService roomService;
    private final GuestFormValidator guestValidator;

    @GetMapping
    public String guestList(Model model) {
        List<Guest> guests = guestService.getGuestList();
        model.addAttribute("guests", guests);
        return "guest/guests";
    }

    @GetMapping("/guestForm")
    public String guestForm(Model model,
                            @RequestParam("roomId") long roomId,
                            @RequestParam(value = "guestId", required = false) Optional<Long> guestId) {
        if (guestId.isPresent()) {
            Guest guest = guestService.getGuestById(guestId.get());
            model.addAttribute("guest", guest);
        } else {
            model.addAttribute("guest", new Guest());//todo:try removing
        }
        model.addAttribute("roomId", roomId);
        return "guest/guestForm";
    }

    @PostMapping("/add")
    public String guestAdd(@ModelAttribute("guest") @Valid Guest guest, BindingResult result, Model model,
                           @RequestParam Long roomId) {
        guestValidator.validate(guest, result);
        if (result.hasErrors()){
            model.addAttribute("roomId", roomId);
            return "guest/guestForm";
        }
        if (guest.getId() == null) {
            guest.setRoom(roomService.getRoomById(roomId));
            guestService.addGuest(guest);
        } else {
            Guest existingGuest = guestService.getGuestById(guest.getId());
            existingGuest.setFirstName(guest.getFirstName());
            existingGuest.setLastName(guest.getLastName());
            existingGuest.setDateOfBirth(guest.getDateOfBirth());
            existingGuest.setPhoneNumber(guest.getPhoneNumber());
            existingGuest.setArrivalDate(guest.getArrivalDate());
            existingGuest.setDepartureDate(guest.getDepartureDate());
            guestService.updateGuest(existingGuest);
        }
        return "redirect:/rooms/roomInfo?roomId=" + roomId;
    }


    @GetMapping("/guestInfo")
    public String getGuest(Model model, @RequestParam("guestId") long id) {
        Guest guest = guestService.getGuestById(id);
        model.addAttribute("guest", guest);
        return "guest/guestInfo";
    }
}
