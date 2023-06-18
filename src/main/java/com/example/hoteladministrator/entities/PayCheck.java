package com.example.hoteladministrator.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "paycheck")
public class PayCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String guestFullName;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private LocalDate arrivalDate;
    @Column(nullable = false)
    private LocalDate departureDate;
    @Column
    private LocalDate checkOutDate;
    @Column
    private LocalDate extendedStayDate;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public double getTotalPrice() {
        double basePrice = room.getRoomType().getPricing();
        int numberOfGuests = room.getGuests().size();
        int days = (int) ChronoUnit.DAYS.between(arrivalDate, departureDate);
        double totalPrice = basePrice * numberOfGuests * days;

        if (numberOfGuests > 1) {
            double discountPercentage = 0.01 * (numberOfGuests - 1);
            double discountAmount = totalPrice * discountPercentage;
            totalPrice -= discountAmount;
        }
        return totalPrice;
    }

    public double getExtendedStayPrice() {
        double basePrice = room.getRoomType().getPricing();
        int numberOfGuests = room.getGuests().size();
        int days = (int) ChronoUnit.DAYS.between(arrivalDate, departureDate);
        double totalPrice = basePrice * numberOfGuests * days;

        if (numberOfGuests > 1) {
            double discountPercentage = 0.01 * (numberOfGuests - 1);
            double discountAmount = totalPrice * discountPercentage;
            totalPrice -= discountAmount;
        }

        if (extendedStayDate != null) {
            int extendedStayDays = (int) ChronoUnit.DAYS.between(departureDate, extendedStayDate);
            double extendedStayPricePerDay = room.getRoomType().getPricing();
            double extendedStayTotalPrice = extendedStayPricePerDay * numberOfGuests * extendedStayDays;
            totalPrice += extendedStayTotalPrice;
        }

        return totalPrice;
    }
    public boolean wasEarlyCheckedOut(){
        return ChronoUnit.DAYS.between(arrivalDate,checkOutDate)<0;
    }
    public double getEarlyCheckOutPrice() {
        int days = (int) ChronoUnit.DAYS.between(arrivalDate, departureDate);
        double totalWithoutPenalty = days*room.getRoomType().getPricing();
        double penaltyPercentage = 0.25;
        double penaltyAmount = totalWithoutPenalty * penaltyPercentage;

        return totalWithoutPenalty + (penaltyAmount * days);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PayCheck payCheck = (PayCheck) o;
        return getId() != null && Objects.equals(getId(), payCheck.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }



}
