package com.PrismaMobi.Prisma_Mobi.entities.passenger;

import com.PrismaMobi.Prisma_Mobi.entities.enums.Gender;
import com.PrismaMobi.Prisma_Mobi.entities.users.UsersDetailsDTO;

public record PassengerDetailsDTO(
        String name,
        String cpf,
        Gender gender,
        Boolean active,
        UsersDetailsDTO usersDetailsDTO
) {
    public PassengerDetailsDTO(Passenger passenger) {
        this(passenger.getName(), passenger.getCpf(), passenger.getGender(),
                passenger.getActive(), new UsersDetailsDTO(passenger.getUsers()));
    }
}
