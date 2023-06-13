package com.example.hoteladministrator.entities;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @jakarta.persistence.Column(name = "id", nullable = false)
    private Long id;

    @Column
    private LocalDate arrivalDate;

    @Column
    private LocalDate departureDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Guest guest = (Guest) o;
        return getId() != null && Objects.equals(getId(), guest.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
