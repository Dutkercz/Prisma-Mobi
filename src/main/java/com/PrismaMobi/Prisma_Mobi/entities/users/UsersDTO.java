package com.PrismaMobi.Prisma_Mobi.entities.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsersDTO(
        Long id,

        @NotBlank(message = "O campo LOGIN nome não pode estar em branco.")
        @Email(message = "O campo login deve corresponder a um email.")
        String login,

        @NotBlank(message = "O campo PASSWORD nome não pode estar em branco.")
        @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres.")
        String password
) {
}
