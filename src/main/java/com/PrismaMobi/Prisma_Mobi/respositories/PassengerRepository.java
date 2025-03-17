package com.PrismaMobi.Prisma_Mobi.respositories;

import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long>{
}
