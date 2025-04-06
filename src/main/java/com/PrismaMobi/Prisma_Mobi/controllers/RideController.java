package com.PrismaMobi.Prisma_Mobi.controllers;

import com.PrismaMobi.Prisma_Mobi.entities.Ride;
import com.PrismaMobi.Prisma_Mobi.entities.RideCoordinates;
import com.PrismaMobi.Prisma_Mobi.entities.RideDetails;
import com.PrismaMobi.Prisma_Mobi.services.RideService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/ride")
@SecurityRequirement(name = "bearer-key")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping()
    public ResponseEntity<?> ride(@RequestBody RideCoordinates rideCoordinates, UriComponentsBuilder builder){

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Ride ride = rideService.saveRide(rideCoordinates, login);
        URI uri = builder.path("/api/ride/{id}").buildAndExpand(ride.getId()).toUri();
        return ResponseEntity.created(uri).body(new RideDetails(ride));
    }
}
