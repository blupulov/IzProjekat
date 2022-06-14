package com.example.owl;

import com.example.owl.services.OWLService;
import com.example.owl.services.PointThreeService;
import com.example.owl.services.PointTwoService;
import org.apache.jena.base.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OwlApplication implements CommandLineRunner {
	@Autowired
	private OWLService service;
	@Autowired
	private PointTwoService p2s;
	@Autowired
	private PointThreeService p3s;

	public static void main(String[] args) {
		SpringApplication.run(OwlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("********************************************************************************");
		System.out.println("********************************************************************************");
		System.out.println("********************************************************************************");
		System.out.println("********************************************************************************");
		p3s.printProbabilities("FrozenScreen");
	}
}
