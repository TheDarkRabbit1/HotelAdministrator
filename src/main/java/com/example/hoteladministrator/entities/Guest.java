package com.example.hoteladministrator.entities;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "First name is required")
    @Column(name = "name", nullable = false)
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    @Column(name = "phone_number", nullable = false, length = 24)
    private String phoneNumber;

    @NotNull(message = "Arrival date is required")
    @Column(nullable = false)
    private LocalDate arrivalDate;

    @NotNull(message = "Departure date is required")
    @Column(nullable = false)
    private LocalDate departureDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", room=" + room +
                '}';
    }

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
