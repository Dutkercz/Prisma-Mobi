package com.PrismaMobi.Prisma_Mobi.controllers;

import com.PrismaMobi.Prisma_Mobi.entities.Users;
import com.PrismaMobi.Prisma_Mobi.entities.UsersDTO;
import com.PrismaMobi.Prisma_Mobi.services.TokenService;
import com.PrismaMobi.Prisma_Mobi.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UsersDTO> registerUser(@RequestBody @Valid UsersDTO usersDTO,
                                                 UriComponentsBuilder builder){
        UsersDTO user = usersService.register(usersDTO);
        URI uri = builder.path("/api/users/register/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsersDTO users){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(users.login(), users.password());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        String tokenJWT = tokenService.createToken((Users) authenticate.getPrincipal());

        return ResponseEntity.ok(tokenJWT);

    }
}
