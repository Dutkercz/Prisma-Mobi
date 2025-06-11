package com.PrismaMobi.Prisma_Mobi.entities.ride;

import com.PrismaMobi.Prisma_Mobi.entities.address.Destination;
import com.PrismaMobi.Prisma_Mobi.entities.address.Origin;
import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import com.PrismaMobi.Prisma_Mobi.entities.enums.CancelledBy;
import com.PrismaMobi.Prisma_Mobi.entities.enums.RideStatus;
import com.PrismaMobi.Prisma_Mobi.entities.enums.Roles;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import com.PrismaMobi.Prisma_Mobi.entities.users.Users;
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

    private LocalDateTime rideRequestDate; // data/hora em que pedido da "corrida" é gerado
    private LocalDateTime rideAcceptDate; // data/hora em que o motorista aceita a "corrida"
    private LocalDateTime rideStartDate; // data/hora em que o passageiro embarca no carro
    private LocalDateTime rideFinishDate; // data/hora em que chega no destino/ou cancela a "corrida"
    private String rideComment;

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    @Setter
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    @Enumerated(EnumType.STRING)
    private CancelledBy cancelledBy;

    public void acceptBy(Driver driver) {
        this.driver = driver;
        this.rideAcceptDate = LocalDateTime.now();
        this.rideStatus = RideStatus.ACCEPTED;
    }

    public void startRide() {
        this.rideStatus = RideStatus.IN_PROGRESS;
        this.rideStartDate = LocalDateTime.now();
    }

    public void finishRide() {
        this.rideStatus = RideStatus.FINISHED;
        this.rideFinishDate = LocalDateTime.now();
    }

    public void canceledRide(String comment, Users user) {
        this.rideStatus = RideStatus.CANCELED;
        this.rideFinishDate = LocalDateTime.now();
        this.rideComment = comment;

        if (user.getRoles() == Roles.ROLE_PASSENGER){
            this.cancelledBy = CancelledBy.CANCELLED_BY_PASSENGER;
        }
        else{
            this.cancelledBy = CancelledBy.CANCELLED_BY_DRIVER;
        }
    }
}
