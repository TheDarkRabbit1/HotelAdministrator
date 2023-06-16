package com.example.hoteladministrator.validators;

import com.example.hoteladministrator.entities.Guest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class GuestFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Guest.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        Guest guest = (Guest) target;
        LocalDate today = LocalDate.now();

        if (guest.getArrivalDate() != null && guest.getArrivalDate().isBefore(today)) {
            errors.rejectValue("arrivalDate", "error.guest",
                    "Arrival date must be after today's date.");
        }

        if (guest.getDepartureDate() != null && guest.getDepartureDate().isBefore(today)) {
            errors.rejectValue("departureDate", "error.guest",
                    "Departure date must be after today's date.");
        }

        if (guest.getArrivalDate() != null && guest.getDepartureDate() != null &&
                guest.getArrivalDate().isAfter(guest.getDepartureDate())) {
            errors.rejectValue("arrivalDate", "error.guest",
                    "Arrival date must be before departure date.");
        }
    }
}
