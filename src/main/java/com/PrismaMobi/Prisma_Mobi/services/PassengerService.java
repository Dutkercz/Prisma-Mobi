package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.Users;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.PassengerDTO;
import com.PrismaMobi.Prisma_Mobi.respositories.PassengerRepository;
import com.PrismaMobi.Prisma_Mobi.respositories.UsersRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersService usersService;

    @Transactional
    public Passenger save(@Valid PassengerDTO passengerDTO, String login) {
        Users users = usersRepository.findByLogin(login);

        return passengerRepository.save(new Passenger(null, passengerDTO.name(),
                passengerDTO.cpf(), true, passengerDTO.gender(), users));
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
