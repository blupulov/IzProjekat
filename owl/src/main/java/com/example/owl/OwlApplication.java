package com.example.owl;

import com.example.owl.services.OWLService;
import org.apache.jena.base.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OwlApplication implements CommandLineRunner {
	@Autowired
	private OWLService service;

	public static void main(String[] args) {
		SpringApplication.run(OwlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("********************************************************************************");
		System.out.println("********************************************************************************");
		System.out.println("********************************************************************************");
		String query = getQuery();
		System.out.println(query);
		System.out.println("********************************************************************************");
		service.executeTestQuery(query);
	}

	private String getQuery() {
		return "prefix : <http://www.semanticweb.org/bojan/ontologies/2022/3/untitled-ontology-6#>\n" +
				"prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
				"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
				"prefix xml: <http://www.w3.org/XML/1998/namespace>\n" +
				"prefix xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
				"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
				"SELECT ?x ?y ?z \n" +
				"WHERE {\n" +
				" ?x ?y ?z\n" +
				"}\n";
	}

}
