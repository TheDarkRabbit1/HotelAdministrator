package com.example.hoteladministrator.services;

import com.example.hoteladministrator.entities.Guest;
import com.example.hoteladministrator.repositories.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;
    public Guest getGuestById(long id){
        Optional<Guest> guest = guestRepository.findById(id);
        if (guest.isEmpty())
            throw new RuntimeException("No guest was found");
        return guest.get();
    }
    public List<Guest> getGuestList(){
        return guestRepository.findAll();
    }
     public Guest addGuest(Guest guest){
        return guestRepository.save(guest);
     }
     public void deleteGuest(Guest guest){
        guestRepository.delete(guest);
     }
     public void deleteGuestById(long id){
        guestRepository.deleteById(id);
     }
}
