package com.example.owl.controllers;

import com.example.owl.dtos.ProbabilityDTO;
import com.example.owl.dtos.SimilarityDto;
import com.example.owl.model.CaseDescription;
import com.example.owl.services.PointFourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("four")
public class PointFourController {
    private final PointFourService pointFourService;

    @Autowired
    public PointFourController(PointFourService pointFourService) {
        this.pointFourService = pointFourService;
    }

    @GetMapping("/similarity/{cn}/{cf}/{cc}/{cb}/{rt}/{rc}/{rf}/{st}/{rws}/{sc}")
    public ResponseEntity<List<SimilarityDto>> getSimilarity(@PathVariable int cn,
                                                             @PathVariable double cf,
                                                             @PathVariable int cc,
                                                             @PathVariable String cb,
                                                             @PathVariable String rt,
                                                             @PathVariable int rc,
                                                             @PathVariable int rf,
                                                             @PathVariable String st,
                                                             @PathVariable int rws,
                                                             @PathVariable int sc) throws Exception {

        return new ResponseEntity<>( this.pointFourService.test(cn, cf, cc, cb, rt, rc, rf, st, rws, sc), HttpStatus.OK);
    }
}
