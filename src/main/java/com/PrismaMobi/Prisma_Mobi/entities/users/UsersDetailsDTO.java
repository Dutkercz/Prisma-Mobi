package com.PrismaMobi.Prisma_Mobi.entities.users;

import com.PrismaMobi.Prisma_Mobi.entities.enums.Roles;

public record UsersDetailsDTO(String login, Roles roles) {
    public UsersDetailsDTO(Users users) {
        this(users.getLogin(), users.getRoles());
    }
}
