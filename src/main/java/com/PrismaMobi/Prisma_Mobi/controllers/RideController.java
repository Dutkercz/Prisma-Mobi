package com.PrismaMobi.Prisma_Mobi.controllers;

import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverMonthlyReport;
import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverRidesDTO;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.PassengerRidesDTO;
import com.PrismaMobi.Prisma_Mobi.entities.ride.*;
import com.PrismaMobi.Prisma_Mobi.services.RideService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<RideDetails> newRide(@RequestBody RideCoordinates rideCoordinates,
                                               UriComponentsBuilder builder) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        RideDetails rideDetails = rideService.saveNewRide(rideCoordinates, login);
        URI uri = builder.path("/ride/{id}").buildAndExpand(rideDetails.rideId()).toUri();
        return ResponseEntity.created(uri).body(rideDetails);
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<RideAcceptedResponse> rideAccept(@PathVariable Long id) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        RideAcceptedResponse rideAcceptedResponse = rideService.acceptRide(login, id);
        return ResponseEntity.ok().body(rideAcceptedResponse);
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<RideStartDTO> rideStart(@PathVariable Long id) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        RideStartDTO rideStartDTO = rideService.startRide(login, id);
        return ResponseEntity.ok().body(rideStartDTO);
    }

    @PostMapping("/{id}/finish")
    public ResponseEntity<RideFinishDTO> rideFinish(@PathVariable Long id) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        RideFinishDTO rideFinishDTO = rideService.finishRide(login, id);
        return ResponseEntity.ok().body(rideFinishDTO);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<RideCancelDTO> rideCancel(@PathVariable Long id, @RequestBody String rideComment) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        RideCancelDTO rideCancelDTO = rideService.cancelRide(login, id, rideComment);
        return ResponseEntity.ok().body(rideCancelDTO);
    }

    @GetMapping("/passenger")
    public ResponseEntity<Page<PassengerRidesDTO>> getPassengerRides(Pageable pageable) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<PassengerRidesDTO> ridesDTOS = rideService.findAllPassengerRides(login, pageable);
        return ResponseEntity.ok().body(ridesDTOS);
    }

    @GetMapping("/driver")
    public ResponseEntity<Page<DriverRidesDTO>> gerDriverRides(Pageable pageable) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<DriverRidesDTO> ridesDTOS = rideService.findALlDriverRides(login, pageable);
        return ResponseEntity.ok().body(ridesDTOS);
    }

    @GetMapping("/driver/report")
    public ResponseEntity<DriverMonthlyReport> getMonthlyDriverReport(@RequestParam @Min(2020) @Max(2050) int year,
                                                                      @RequestParam @Min(1) @Max(12) int month) {

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        DriverMonthlyReport driverMonthlyReport = rideService.getDriverMonthlyReport(login, year, month);
        return ResponseEntity.ok().body(driverMonthlyReport);
    }

    @GetMapping("/active")
    public ResponseEntity<RideDetails> rideActiveDetails() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        RideDetails rideDetails = rideService.getActiveRide(login);
        return ResponseEntity.ok().body(rideDetails);
    }
}
