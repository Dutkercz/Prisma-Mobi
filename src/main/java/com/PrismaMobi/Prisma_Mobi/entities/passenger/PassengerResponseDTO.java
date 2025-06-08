package com.PrismaMobi.Prisma_Mobi.entities.passenger;

import com.PrismaMobi.Prisma_Mobi.entities.enums.Gender;

public record PassengerResponseDTO(Long id, String name, Gender gender) {
    public PassengerResponseDTO(Passenger passenger) {
        this(passenger.getId(), passenger.getName(), passenger.getGender());
    }
}
