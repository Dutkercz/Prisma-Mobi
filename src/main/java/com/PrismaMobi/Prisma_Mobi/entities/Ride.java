package com.PrismaMobi.Prisma_Mobi.entities;

import com.PrismaMobi.Prisma_Mobi.entities.address.Destination;
import com.PrismaMobi.Prisma_Mobi.entities.address.Origin;
import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import com.PrismaMobi.Prisma_Mobi.entities.enums.RideStatus;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Table
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Origin origin;

    @Embedded
    private Destination destination;

    @Setter
    private Double totalPrice;

    private LocalDateTime rideDate;

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    @Setter
    private Driver driver;

    @Enumerated(EnumType.STRING)
    RideStatus rideStatus;

    public void acceptBy(Driver driver) {
        this.driver = driver;
        this.rideStatus = RideStatus.ACCEPTED;
    }
}
