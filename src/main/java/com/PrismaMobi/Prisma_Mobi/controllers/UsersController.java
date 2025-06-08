package com.PrismaMobi.Prisma_Mobi.controllers;

import com.PrismaMobi.Prisma_Mobi.entities.users.Users;
import com.PrismaMobi.Prisma_Mobi.entities.users.UsersDTO;
import com.PrismaMobi.Prisma_Mobi.entities.users.UsersResponseDTO;
import com.PrismaMobi.Prisma_Mobi.services.TokenService;
import com.PrismaMobi.Prisma_Mobi.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<UsersResponseDTO> registerUser(@RequestBody @Valid UsersDTO usersDTO,
                                                         UriComponentsBuilder builder) {
        Users user = usersService.register(usersDTO);
        URI uri = builder.path("/users/register/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsersResponseDTO(user));
    }

}
