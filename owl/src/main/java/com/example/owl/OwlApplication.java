package com.example.owl;

import com.example.owl.dtos.ComputerTypeDTO;
import com.example.owl.services.OWLService;
import com.example.owl.services.PointOneService;
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
	private PointOneService p1s;
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
		//p3s.printProbabilities("FrozenScreen");
		//p1s.getAllCpus().forEach(c -> System.out.println(c.getCpuName() + ", " + c.getCorsNum()));
		//p1s.getAllRams().forEach(m -> System.out.println(m.getRamName() + " " + m.getRamCap()));
		//p1s.getAllPowerSupplies().forEach(ps -> System.out.println(ps.getPowerSupplyName() + " " + ps.getPower()));
		//p1s.getAllStorages().forEach(s -> System.out.println(s.getStorageName() + " " + s.getRws()));

		//
		//ComputerTypeDTO dto = p2s.getComputerTypeDTOWithNumbers(10, 6, 650, 2500);
		//System.out.println("h: " + dto.getHomePC());
		//System.out.println("b: " + dto.getBusinessPC());
		//System.out.println("g: " + dto.getGamingPC());
		//System.out.println("m: " + dto.getMiningPC());

		//p1s.getAllMotherboards().forEach(System.out::println);
	}
}
