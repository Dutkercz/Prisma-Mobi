package com.PrismaMobi.Prisma_Mobi.controllers;

import com.PrismaMobi.Prisma_Mobi.entities.UpdatePassengerDTO;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.PassengerDTO;
import com.PrismaMobi.Prisma_Mobi.repositories.UsersRepository;
import com.PrismaMobi.Prisma_Mobi.services.PassengerService;
import com.PrismaMobi.Prisma_Mobi.services.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearer-key")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/register")
    public ResponseEntity<PassengerDTO> passengerRegister(@RequestBody @Valid PassengerDTO passengerDTO,
                                                          UriComponentsBuilder builder) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Passenger passenger = passengerService.save(passengerDTO, login);
        URI uri = builder.path("/api/passenger/register/{id}").buildAndExpand(passenger.getId()).toUri();
        return ResponseEntity.created(uri).body(passengerDTO.listing());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerDTO> findById(@PathVariable Long id) {
        PassengerDTO passenger = passengerService.findById(id);
        return ResponseEntity.ok().body(passenger.listing());
    }

    @GetMapping("/all")
    public Page<PassengerDTO> findAll(Pageable pageable) {
        return passengerService.findAll(pageable);
    }

    @GetMapping("/details")
    public ResponseEntity<Passenger> details(){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Passenger passenger = passengerService.details(login);
        return ResponseEntity.ok().body(passenger);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePassenger(@RequestBody @Valid UpdatePassengerDTO update){

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Passenger passenger = passengerService.updatePassengerName(update.name(), login);
        return ResponseEntity.ok(new PassengerDTO(passenger).listing());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<PassengerDTO> deletePassenger(){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(passengerService.deleteLoggedPassenger(login));
    }

}
