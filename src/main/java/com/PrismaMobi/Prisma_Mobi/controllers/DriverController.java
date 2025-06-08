package com.PrismaMobi.Prisma_Mobi.controllers;

import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverDTO;
import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverRegisterDTO;
import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverUpdateDTO;
import com.PrismaMobi.Prisma_Mobi.services.DriverService;
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
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping
    public ResponseEntity<DriverDTO> registerDriver(@RequestBody @Valid DriverRegisterDTO driverRegisterDTO,
                                                    UriComponentsBuilder builder) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Driver driver = driverService.saveDriver(driverRegisterDTO, login);
        URI uri = builder.path("api/driver/register/{id}").buildAndExpand(driver.getId()).toUri();
        return ResponseEntity.created(uri).body(new DriverDTO(driver));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DriverDTO>> findAll(Pageable pageable) {
        Page<DriverDTO> driverPage = driverService.findAll(pageable).map(DriverDTO::new);
        return ResponseEntity.ok().body(driverPage);
    }

    @GetMapping("/{plate}")
    public ResponseEntity<DriverDTO> findByPlate(@PathVariable String plate) {
        return ResponseEntity.ok(driverService.findByPlate(plate.toUpperCase()));
    }

    @PutMapping
    public ResponseEntity<DriverDTO> update(@PathVariable DriverUpdateDTO updateDTO) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Driver driver = driverService.updateDriver(updateDTO, login);
        return ResponseEntity.ok(new DriverDTO(driver));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        driverService.deleteDriver(login);
        return ResponseEntity.noContent().build();
    }

}
