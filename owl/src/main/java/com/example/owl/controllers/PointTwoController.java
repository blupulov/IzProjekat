package com.example.owl.controllers;

import com.example.owl.dtos.ComputerTypeDTO;
import com.example.owl.services.PointTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("two")
public class PointTwoController {
    private final PointTwoService pointTwoService;

    @Autowired
    public PointTwoController(PointTwoService pointTwoService) {
        this.pointTwoService = pointTwoService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/numbers/{cpuNum}/{memCap}/{power}/{rws}")
    public ResponseEntity<ComputerTypeDTO> getComputerTypeWithNumbers(
            @PathVariable int cpuNum, @PathVariable int memCap, @PathVariable int power, @PathVariable int rws) {
        ComputerTypeDTO dto = pointTwoService.getComputerTypeDTOWithNumbers(cpuNum, memCap, power, rws);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/names/{cpuName}/{memName}/{powerName}/{storageName}/{type}")
    public ResponseEntity<ComputerTypeDTO> getComputerTypeWithNames(
            @PathVariable String cpuName, @PathVariable String memName, @PathVariable String powerName,
            @PathVariable String storageName, @PathVariable String type) {
        ComputerTypeDTO dto = pointTwoService.getComputerTypeDTOWithName(
                cpuName, memName, powerName, storageName, type);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
