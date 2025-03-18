package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.PassengerDTO;
import com.PrismaMobi.Prisma_Mobi.respositories.PassengerRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Transactional
    public Passenger save(@Valid PassengerDTO passengerDTO) {
        return passengerRepository.save(new Passenger(null, passengerDTO.name(),
                passengerDTO.cpf(), true, passengerDTO.gender()));
    }

    public PassengerDTO findById(Long id) {
        Passenger passenger = passengerRepository.getReferenceById(id);
        return new PassengerDTO(passenger.getName(), passenger.getCpf(), passenger.getGender());
    }

    public Page<PassengerDTO> findAll(Pageable pageable) {
        return passengerRepository.findAllByActiveTrue(pageable)
                .map(x -> new PassengerDTO(x.getName(), x.getCpf(), x.getGender()));
    }
}
