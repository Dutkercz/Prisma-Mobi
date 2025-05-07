package com.PrismaMobi.Prisma_Mobi.repositories;

import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long>{
    Page<Passenger> findAllByActiveTrue (Pageable pageable);

    Optional<Passenger> findByCpf(String cpf);

    Optional<Passenger> findPassengerByUsersId(Long id);

    Optional<Passenger> findPassengerByUsersIdAndActiveTrue(Long id);
}
