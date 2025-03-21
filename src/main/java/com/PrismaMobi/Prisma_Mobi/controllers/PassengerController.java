package com.PrismaMobi.Prisma_Mobi.controllers;

import com.PrismaMobi.Prisma_Mobi.entities.Users;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.PassengerDTO;
import com.PrismaMobi.Prisma_Mobi.respositories.UsersRepository;
import com.PrismaMobi.Prisma_Mobi.services.PassengerService;
import com.PrismaMobi.Prisma_Mobi.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/register")
    public ResponseEntity<PassengerDTO> passengerRegister(@RequestBody @Valid PassengerDTO passengerDTO,
                                            UriComponentsBuilder builder){
        //teste
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Nome >> "+ login);
        Users users = usersRepository.findByLogin(login);
        //fim do teste

        Passenger passenger = passengerService.save(passengerDTO, users);
        URI uri = builder.path("/api/passenger/register/{id}").buildAndExpand(passenger.getId()).toUri();
        return ResponseEntity.created(uri).body(passengerDTO.listing());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerDTO> findById(@PathVariable Long id){
        PassengerDTO passenger = passengerService.findById(id);
        return ResponseEntity.ok().body(passenger.listing());
    }

    @GetMapping("/all")
    public Page<PassengerDTO> findAll(Pageable pageable){
        return passengerService.findAll(pageable);
    }

}
