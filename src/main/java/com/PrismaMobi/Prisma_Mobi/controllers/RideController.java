package com.PrismaMobi.Prisma_Mobi.controllers;

import com.PrismaMobi.Prisma_Mobi.entities.Ride;
import com.PrismaMobi.Prisma_Mobi.entities.RideCoordinates;
import com.PrismaMobi.Prisma_Mobi.entities.RideDetails;
import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import com.PrismaMobi.Prisma_Mobi.services.RideService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/ride")
@SecurityRequirement(name = "bearer-key")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping
    public ResponseEntity<?> newRide(@RequestBody RideCoordinates rideCoordinates,
                                  UriComponentsBuilder builder) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Ride ride = rideService.saveNewRide(rideCoordinates, login);
        URI uri = builder.path("/ride/{id}").buildAndExpand(ride.getId()).toUri();
        return ResponseEntity.created(uri).body(new RideDetails(ride));
    }

    @PostMapping("/{id}/aceitar")
    public ResponseEntity<?> rideAccept(@PathVariable Long id){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Ride ride = rideService.acceptRide(login, id);
    }
}
