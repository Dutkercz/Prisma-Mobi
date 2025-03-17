package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.models.Passenger;
import com.PrismaMobi.Prisma_Mobi.models.PassengerDTO;
import com.PrismaMobi.Prisma_Mobi.respositories.PassengerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger save(@Valid PassengerDTO passengerDTO) {
        return passengerRepository.save(new Passenger(null, passengerDTO.name(),
                passengerDTO.cpf(), true, passengerDTO.gender()));
    }
}
