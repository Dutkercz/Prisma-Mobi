package com.PrismaMobi.Prisma_Mobi.controllers;

import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverDTO;
import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverRegisterDTO;
import com.PrismaMobi.Prisma_Mobi.services.DriverService;
import jakarta.validation.Valid;
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
    public ResponseEntity<DriverDTO> registerDriver(@RequestBody @Valid DriverRegisterDTO driverRegisterDTO, UriComponentsBuilder builder){

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Driver driver = driverService.saveDriver(driverRegisterDTO, login);
        URI uri = builder.path("api/driver/register/{id}").buildAndExpand(driver.getId()).toUri();
        return ResponseEntity.created(uri).body(new DriverDTO(driver));
    }

    @GetMapping("/find/{plate}")
    public ResponseEntity<DriverRegisterDTO> findByPlate(@PathVariable String plate){
        System.out.println("Placa no cotroller" + plate.toUpperCase());
        return ResponseEntity.ok(driverService.findByPlate(plate.toUpperCase()));
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteById (){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Driver driver = driverService.deleteDriver(login);
        return ResponseEntity.noContent().build();
    }
    
 }
