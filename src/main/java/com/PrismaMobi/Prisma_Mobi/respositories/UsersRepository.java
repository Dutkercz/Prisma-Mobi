package com.PrismaMobi.Prisma_Mobi.respositories;

import com.PrismaMobi.Prisma_Mobi.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByLogin(String login);
}
