package com.PrismaMobi.Prisma_Mobi.controllers;

import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverDTO;
import com.PrismaMobi.Prisma_Mobi.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/register")
    public ResponseEntity<DriverDTO> registerDriver(@RequestBody DriverDTO driverDTO, UriComponentsBuilder builder){

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Driver driver = driverService.saveDriver(driverDTO, login);
        URI uri = builder.path("api/driver/register/{id}").buildAndExpand(driver.getId()).toUri();
        return ResponseEntity.created(uri).body(driverDTO.listing());
    }

    @GetMapping("/find/{plate}")
    public ResponseEntity<DriverDTO> findByPlate(@PathVariable String plate){
        return ResponseEntity.ok(driverService.findByPlate(plate));
    }
 }
