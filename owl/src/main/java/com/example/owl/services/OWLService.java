package com.example.owl.services;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;

@Service
public class OWLService {
    private OWLOntologyManager manager;
    private OWLOntology ontology;

    public OWLService() {
        createManager();
        loadOntology();
    }

    public OWLOntologyManager getManager() {
        return this.manager;
    }

    public OWLOntology getOntology() {
        return this.ontology;
    }

    public void testOntology() {
        System.out.println(this.ontology);
        //System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
    }

    private void createManager() {
        this.manager = OWLManager.createOWLOntologyManager();
    }

    private void loadOntology() {
        String filePath = "..\\owl-data\\classes&individuals.owl";
        File file = new File(filePath);

        try {
            this.ontology = this.manager.loadOntologyFromOntologyDocument(file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
