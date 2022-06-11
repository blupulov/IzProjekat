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

    public int getCpuCoreNumbers(String cpuName) {
        List<QuerySolution> results = service.executeQuery(makeCpuCoreNumbersQuery(cpuName));
        for (QuerySolution qs : results){
            return qs.get("cn").asLiteral().getInt();
        }
        return 0;
    }

    public int getMemCapacity(String memName) {
        List<QuerySolution> results = service.executeQuery(makeMemCapacityQuery(memName));
        for (QuerySolution qs : results){
            return qs.get("mc").asLiteral().getInt();
        }
        return 0;
    }

    public int getPowerSupply(String power) {
        List<QuerySolution> results = service.executeQuery(makePowerSupplyQuery(power));
        for (QuerySolution qs : results){
            return qs.get("ps").asLiteral().getInt();
        }
        return 0;
    }

    public int getHddRws(String hddName) {
        List<QuerySolution> results = service.executeQuery(makeHDDStorageQuery(hddName));
        for (QuerySolution qs : results){
            return qs.get("rws").asLiteral().getInt();
        }
        return 0;
    }

    public int getSsdRws(String ssdName) {
        List<QuerySolution> results = service.executeQuery(makeSSDStorageQuery(ssdName));
        for (QuerySolution qs : results){
            return qs.get("rws").asLiteral().getInt();
        }
        return 0;
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

    private String makeCpuCoreNumbersQuery(String cpuName) {
        return getPrefixes() +
                "SELECT ?cn \n" +
                "WHERE {\n" +
                "	inst:"+cpuName+" :coresNumber ?cn .\n" +
                "}\n";
    }

    private String makeMemCapacityQuery(String memName) {
        return getPrefixes() +
                "SELECT ?mc \n" +
                "WHERE {\n" +
                "	inst:"+memName+" :InternalMemoryCapacity ?mc .\n" +
                "}\n";
    }

    private String makePowerSupplyQuery(String power) {
        return getPrefixes() +
                "SELECT ?ps \n" +
                "WHERE {\n" +
                "	inst:"+power+" :voltage ?ps .\n" +
                "}\n";
    }

    private String makeSSDStorageQuery(String ssdName) {
        return getPrefixes() +
                "SELECT ?rws \n" +
                "WHERE {\n" +
                "	inst:"+ssdName+" :ssdRWS ?rws .\n" +
                "}\n";
    }

    private String makeHDDStorageQuery(String hddName) {
        return getPrefixes() +
                "SELECT ?rws \n" +
                "WHERE {\n" +
                "	inst:"+hddName+" :hddRWS ?rws .\n" +
                "}\n";
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
