package com.PrismaMobi.Prisma_Mobi.controllers;

import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.PassengerDTO;
import com.PrismaMobi.Prisma_Mobi.services.PassengerService;
import com.PrismaMobi.Prisma_Mobi.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register")
    public ResponseEntity<PassengerDTO> passengerRegister(@RequestBody @Valid PassengerDTO passengerDTO,
                                            @RequestAttribute("subject") String login,
                                            UriComponentsBuilder builder){
        System.out.println("Login do usuário autenticado: " + login);
        Passenger passenger = passengerService.save(passengerDTO);
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
