package com.example.owl.services;

import com.example.owl.dtos.CpuDto;
import org.apache.jena.query.QuerySolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointOneService {
    private final OWLService service;

    @Autowired
    public PointOneService(OWLService service) {
        this.service = service;
    }

    public List<CpuDto> upgradeCPU(String mbName, String mbBrand) {
        List<QuerySolution> results = service.executeQuery(makeUpgradeCPUQuery(mbName, mbBrand));
        return processCPUResults(results);
    }

    private List<CpuDto> processCPUResults(List<QuerySolution> results) {
        List<CpuDto> retVal = new ArrayList<>();
        for(QuerySolution qs : results) {
            String cpu = qs.get("proc").asResource().getLocalName();
            String mf = qs.get("mf").asLiteral().getValue().toString();
            String bf = qs.get("bf").asLiteral().getValue().toString();
            String cn = qs.get("cn").asLiteral().getValue().toString();
            retVal.add(new CpuDto(cpu, mf, bf, cn));
        }
        return retVal;
    }

    private String makeUpgradeCPUQuery(String mbName, String mbBrand) {
        return getPrefixes() +
                "SELECT ?proc ?mf ?bf ?cn\n" +
                "WHERE {\n" +
                "	inst:"+mbName+" :haveBrand inst:"+mbBrand+" .\n"  +
                "	inst:"+mbName+" :haveChipsetType ?ct .\n" +
                "	inst:"+mbName+" :haveSocket ?s . \n" +
                "	?proc rdf:type :Processor .\n" +
                "	?proc :haveSocket ?s .\n" +
                "	?proc :haveChipsetType ?ct .\n" +
                "	?proc :processorMainFrequency ?mf .\n" +
                "	?proc :processorBoostFrequency ?bf .\n" +
                "	?proc :coresNumber ?cn .\n" +
                "}\n";
    }

    private String getPrefixes() {
        return "prefix : <http://www.semanticweb.org/bojan/ontologies/2022/3/untitled-ontology-6#>\n" +
                "prefix inst: <http://www.semanticweb.org/bojan/ontologies/2022/3/untitled-ontology-33#>\n" +
                "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "prefix xml: <http://www.w3.org/XML/1998/namespace>\n" +
                "prefix xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n";
    }
}
