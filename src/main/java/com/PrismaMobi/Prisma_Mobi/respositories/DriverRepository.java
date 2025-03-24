package com.PrismaMobi.Prisma_Mobi.respositories;

import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
