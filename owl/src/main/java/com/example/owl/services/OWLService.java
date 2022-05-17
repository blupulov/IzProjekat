package com.example.owl.services;

import org.apache.jena.base.Sys;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class OWLService {
    private OWLOntologyManager manager;
    private OWLOntology ontology;
    private Model model;

    public OWLService() {
        createManager();
        loadOntology();
        loadModel();
    }

    public OWLOntologyManager getManager() {
        return this.manager;
    }

    public OWLOntology getOntology() {
        return this.ontology;
    }

    public Model getModel() {
        return this.model;
    }

    public void testOntology() {
        System.out.println(this.ontology);
    }

    public void executeTestQuery(String query)  {
        QueryExecution qe = QueryExecutionFactory.create(QueryFactory.create(query),model);
        ResultSet rs = qe.execSelect();
        while (rs.hasNext()) {
            QuerySolution qs = rs.nextSolution();
            System.out.println(qs.toString());
        }
    }

    public List<QuerySolution> executeQuery(String query) {
        List<QuerySolution> retVal = new ArrayList<>();
        QueryExecution qe = QueryExecutionFactory.create(QueryFactory.create(query), model);
        ResultSet rs = qe.execSelect();
        while (rs.hasNext()) {
            retVal.add(rs.nextSolution());
        }
        return retVal;
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

    private void loadModel() {
        String filePath = "..\\owl-data\\classes&individuals.owl";
        this.model = ModelFactory.createDefaultModel();
        try {
            InputStream is = new FileInputStream(filePath);
            RDFDataMgr.read(this.model, is, Lang.TURTLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
