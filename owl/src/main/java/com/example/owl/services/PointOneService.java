package com.example.owl.services;

import com.example.owl.dtos.CpuDto;
import com.example.owl.dtos.RamDto;
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

    public List<RamDto> upgradeRAM(String mbName, String mbBrand) {
        List<QuerySolution> results = service.executeQuery(makeUpgradeRAMQuery(mbName, mbBrand));
        return processRAMResults(results);
    }

    private List<RamDto> processRAMResults(List<QuerySolution> results) {
        List<RamDto> retVal = new ArrayList<>();
        for (QuerySolution qs : results) {
            String ram = qs.get("ram").asResource().getLocalName();
            String brand = qs.get("rb").asResource().getLocalName();
            String cap = qs.get("cap").asLiteral().getValue().toString();
            retVal.add(new RamDto(ram, brand, cap));
        }
        return retVal;
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

    private String makeUpgradeRAMQuery(String mbName, String mbBrand) {
        return getPrefixes() +
                "SELECT ?rb ?ram ?cap\n" +
                "WHERE {\n" +
                "	inst:"+mbName+" :haveBrand inst:"+mbBrand+" .\n"  +
                "	inst:"+mbName+" :supportMemoryType ?mt .\n" +
                "	?ram rdf:type :Memory . \n" +
                "	?ram :haveInternalMemoryType ?mt .\n" +
                "	?ram :haveBrand ?rb .\n" +
                "	?ram :InternalMemoryCapacity ?cap .\n" +
                "}\n";
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
