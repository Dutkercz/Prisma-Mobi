package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.PassengerDTO;
import com.PrismaMobi.Prisma_Mobi.respositories.PassengerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger save(@Valid PassengerDTO passengerDTO) {
        return passengerRepository.save(new Passenger(null, passengerDTO.name(),
                passengerDTO.cpf(), true, passengerDTO.gender()));
    }
}
