package com.example.owl.controllers;

import com.example.owl.dtos.*;
import com.example.owl.services.PointOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("one")
public class PointOneController {
    private final PointOneService pointOneService;

    @Autowired
    public PointOneController(PointOneService pointOneService) {
        this.pointOneService = pointOneService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cpu/{mbName}/{mbBrand}")
    public ResponseEntity<List<CpuDto>> upgradeCPU(@PathVariable String mbBrand, @PathVariable String mbName) {
        return new ResponseEntity<>(pointOneService.upgradeCPU(mbName, mbBrand), HttpStatus.OK);
    }

    @GetMapping("/ram/{mbName}/{mbBrand}")
    public ResponseEntity<List<RamDto>> upgradeRAM(@PathVariable String mbBrand, @PathVariable String mbName) {
        return new ResponseEntity<>(pointOneService.upgradeRAM(mbName, mbBrand), HttpStatus.OK);
    }

    @GetMapping("/cpu")
    public ResponseEntity<List<CpuValidityDto>> getAllCpus() {
        return new ResponseEntity<>(pointOneService.getAllCpus(), HttpStatus.OK);
    }

    @GetMapping("/ram")
    public ResponseEntity<List<RamValidityDto>> getAllRams() {
        return new ResponseEntity<>(pointOneService.getAllRams(), HttpStatus.OK);
    }

    @GetMapping("/powerSupply")
    public ResponseEntity<List<PowerSupplyValidityDto>> getAllPowerSupplies() {
        return new ResponseEntity<>(pointOneService.getAllPowerSupplies(), HttpStatus.OK);
    }

    @GetMapping("/storage")
    public ResponseEntity<List<StorageValidityDto>> getAllStorages() {
        return new ResponseEntity<>(pointOneService.getAllStorages(), HttpStatus.OK);
    }

    @GetMapping("/motherboard")
    public ResponseEntity<List<String>> getAllMotherboards() {
        return new ResponseEntity<>(pointOneService.getAllMotherboards(), HttpStatus.OK);
    }

}
