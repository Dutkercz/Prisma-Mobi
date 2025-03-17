package com.PrismaMobi.Prisma_Mobi.controllers;

import com.PrismaMobi.Prisma_Mobi.entities.passenger.PassengerDTO;
import com.PrismaMobi.Prisma_Mobi.services.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
    @RequestMapping("/api/passenger")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @PostMapping("/register")
    public ResponseEntity<PassengerDTO> passengerRegister(@RequestBody @Valid PassengerDTO passengerDTO,
                                            UriComponentsBuilder builder){
        var passenger = passengerService.save(passengerDTO);
        URI uri = builder.path("/api/passenger/register/{id}").buildAndExpand(passenger.getId()).toUri();
        return ResponseEntity.created(uri).body(passengerDTO.listing());
    }

}
