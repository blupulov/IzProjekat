package com.example.owl.controllers;

import com.example.owl.dtos.ProbabilityDTO;
import com.example.owl.services.PointThreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("three")
public class PointThreeController {
    private final PointThreeService pointThreeService;

    @Autowired
    public PointThreeController(PointThreeService pointThreeService) {
        this.pointThreeService = pointThreeService;
    }

    @GetMapping("/probability/{nodeName}")
    public ResponseEntity<List<ProbabilityDTO>> getProbability(@PathVariable String nodeName) throws Exception {
        return new ResponseEntity<>( this.pointThreeService.getAllProbabilities(nodeName), HttpStatus.OK);
    }
}
