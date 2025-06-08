package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.PassengerRequestDTO;
import com.PrismaMobi.Prisma_Mobi.entities.users.Users;
import com.PrismaMobi.Prisma_Mobi.repositories.PassengerRepository;
import com.PrismaMobi.Prisma_Mobi.repositories.UsersRepository;
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
    public Passenger save(@Valid PassengerRequestDTO passengerRequestDTO, String login) {
        Users users = usersRepository.findByLogin(login);
        return passengerRepository.save(new Passenger(null, passengerRequestDTO.name(),
                passengerRequestDTO.cpf(), true, passengerRequestDTO.gender(), users));
    }

    public Passenger findById(Long id) {
        return passengerRepository.getReferenceById(id);

    }

    public Page<Passenger> findAll(Pageable pageable) {
        return passengerRepository.findAllByActiveTrue(pageable);
    }

    @Transactional
    public void deleteLoggedPassenger(String login) {
        Users users = usersRepository.findByLogin(login);
        Passenger passenger = passengerRepository.findPassengerByUsersId(users.getId())
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado"));
        passenger.setInactive();
    }

    public Passenger details(String login) {
        Users users = usersRepository.findByLogin(login);
        return passengerRepository.findPassengerByUsersIdAndActiveTrue(users.getId())
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado"));
    }

    @Transactional
    public Passenger updatePassengerName(String name, String login) {
        Users users = usersRepository.findByLogin(login);
        Passenger passenger = passengerRepository.findPassengerByUsersId(users.getId())
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado"));
        if (name != null) {
            passenger.setName(name.trim());
            return passenger;
        }
        return null;
    }
}
