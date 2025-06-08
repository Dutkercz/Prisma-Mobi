package com.PrismaMobi.Prisma_Mobi.controllers;

import com.PrismaMobi.Prisma_Mobi.entities.passenger.*;
import com.PrismaMobi.Prisma_Mobi.services.PassengerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/passengers")
@SecurityRequirement(name = "bearer-key")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping
    public ResponseEntity<PassengerResponseDTO> passengerRegister(@RequestBody @Valid PassengerRequestDTO passengerRequestDTO,
                                                                  UriComponentsBuilder builder) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Passenger passenger = passengerService.save(passengerRequestDTO, login);
        URI uri = builder.path("/passengers/register/{id}").buildAndExpand(passenger.getId()).toUri();
        return ResponseEntity.created(uri).body(new PassengerResponseDTO(passenger));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerResponseDTO> findById(@PathVariable Long id) {
        Passenger passenger = passengerService.findById(id);
        return ResponseEntity.ok().body(new PassengerResponseDTO(passenger));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PassengerResponseDTO>> findAll(Pageable pageable) {
        Page<PassengerResponseDTO> passengerDTOS = passengerService.findAll(pageable).map(PassengerResponseDTO::new);
        return ResponseEntity.ok().body(passengerDTOS);
    }

    @GetMapping("/details")
    public ResponseEntity<PassengerDetailsDTO> details() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Passenger passenger = passengerService.details(login);
        return ResponseEntity.ok().body(new PassengerDetailsDTO(passenger));
    }

    @PutMapping
    public ResponseEntity<PassengerResponseDTO> updatePassenger(@RequestBody @Valid PassengerUpdateDTO update) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Passenger passenger = passengerService.updatePassengerName(update.name(), login);
        return ResponseEntity.ok(new PassengerResponseDTO(passenger));
    }

    @DeleteMapping
    public ResponseEntity<?> deletePassenger() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        passengerService.deleteLoggedPassenger(login);
        return ResponseEntity.noContent().build();
    }

}
