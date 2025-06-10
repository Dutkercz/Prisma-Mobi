package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import com.PrismaMobi.Prisma_Mobi.entities.enums.Roles;
import com.PrismaMobi.Prisma_Mobi.entities.users.Users;
import com.PrismaMobi.Prisma_Mobi.repositories.DriverRepository;
import com.PrismaMobi.Prisma_Mobi.repositories.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class DriverValidationService {

    private final UsersRepository usersRepository;
    private final DriverRepository driverRepository;

    public DriverValidationService(UsersRepository usersRepository, DriverRepository driverRepository) {
        this.usersRepository = usersRepository;
        this.driverRepository = driverRepository;
    }

    public Driver getValidateDriver(String login) {
        Users users = usersRepository.findByLogin(login);

        if (users.getRoles() != Roles.ROLE_DRIVER) {
            throw new AccessDeniedException("Usuário não tem permissões para esta ação");
        }

        return driverRepository.findByUsersIdAndActive(users.getId(), true)
                .orElseThrow(() -> new EntityNotFoundException("Motorista não encontrado"));
    }
}
