package com.PrismaMobi.Prisma_Mobi.entities;

import com.PrismaMobi.Prisma_Mobi.entities.address.Destination;
import com.PrismaMobi.Prisma_Mobi.entities.address.Origin;
import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private Passenger passenger;

    @ManyToOne(cascade = CascadeType.ALL)
    private Driver driver;
}
