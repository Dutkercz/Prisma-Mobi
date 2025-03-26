package com.PrismaMobi.Prisma_Mobi.respositories;

import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findDriverByVehiclePlate(String plate);

    @Query(value = """
            SELECT x FROM Driver x
            WHERE
            x.active = true
            ORDER BY RAND()
            LIMIT 1
            """, nativeQuery = true)
    Driver findRandomDriver();
}
