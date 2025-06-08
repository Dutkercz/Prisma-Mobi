package com.PrismaMobi.Prisma_Mobi.entities.users;

public record UsersResponseDTO(Long id, String login) {
    public UsersResponseDTO(Users user) {
        this(user.getId(), user.getLogin());
    }
}
