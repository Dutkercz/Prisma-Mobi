package com.PrismaMobi.Prisma_Mobi.controllers;

import com.PrismaMobi.Prisma_Mobi.entities.Users;
import com.PrismaMobi.Prisma_Mobi.entities.UsersDTO;
import com.PrismaMobi.Prisma_Mobi.respositories.UsersRespository;
import com.PrismaMobi.Prisma_Mobi.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<UsersDTO> registerUser(@RequestBody @Valid UsersDTO usersDTO, UriComponentsBuilder builder){
        UsersDTO user = usersService.register(usersDTO);
        URI uri = builder.path("/api/users/register/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UsersDTO users){
        Boolean authenticated = usersService.authorizeLogin(users.login(), users.password());
        if(authenticated){
            return ResponseEntity.ok("Login successfully");
        }
        return ResponseEntity.badRequest().body("Login failed, please check your credentials");
    }
}
