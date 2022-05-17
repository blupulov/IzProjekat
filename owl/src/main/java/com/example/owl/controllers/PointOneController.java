package com.example.owl.controllers;

import com.example.owl.dtos.CpuDto;
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
}
