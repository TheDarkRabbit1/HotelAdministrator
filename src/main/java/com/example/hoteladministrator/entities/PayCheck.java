package com.example.hoteladministrator.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.LocalDate;
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

    public String generatePro(){
        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.toString();
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
