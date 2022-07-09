package com.example.owl.services;

import com.example.owl.connector.CsvConnector;
import com.example.owl.dtos.SimilarityDto;
import com.example.owl.model.CaseDescription;
import org.springframework.stereotype.Service;
import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.*;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.EqualsStringIgnoreCase;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Threshold;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PointFourService implements StandardCBRApplication {
    Connector _connector;  /** Connector object */
    CBRCaseBase _caseBase;  /** CaseBase object */
    NNConfig simConfig;  /** KNN configuration */

    public PointFourService() { }

    public void configure() throws ExecutionException {
        _connector =  new CsvConnector();

        _caseBase = new LinealCaseBase();

        simConfig = new NNConfig();
        simConfig.setDescriptionSimFunction(new Average());

        // TODO
        simConfig.addMapping(new Attribute("corsNumber", CaseDescription.class), new Equal());
        simConfig.addMapping(new Attribute("cpuFrequency", CaseDescription.class), new Interval(5));
        simConfig.addMapping(new Attribute("cpuCache", CaseDescription.class), new Interval(100));
        simConfig.addMapping(new Attribute("cpuBrand", CaseDescription.class), new EqualsStringIgnoreCase());
        simConfig.addMapping(new Attribute("ramType", CaseDescription.class), new EqualsStringIgnoreCase());
        simConfig.addMapping(new Attribute("ramCapacity", CaseDescription.class), new Interval(10));
        simConfig.addMapping(new Attribute("ramFrequency", CaseDescription.class), new Threshold(800));
        simConfig.addMapping(new Attribute("storageType", CaseDescription.class), new EqualsStringIgnoreCase());
        simConfig.addMapping(new Attribute("rws", CaseDescription.class), new Threshold(800));
        simConfig.addMapping(new Attribute("storageCapacity", CaseDescription.class), new Interval(1800));

        // Equal - returns 1 if both individuals are equal, otherwise returns 0 [cn, ramType, ]
        // Interval - returns the similarity of two number inside an interval: sim(x,y) = 1-(|x-y|/interval) [other]
        // Threshold - returns 1 if the difference between two numbers is less than a threshold, 0 in the other case [ramFreq]
        // EqualsStringIgnoreCase - returns 1 if both String are the same despite case letters, 0 in the other case
        // MaxString - returns a similarity value depending of the biggest substring that belong to both strings
        // EnumDistance - returns the similarity of two enum values as the their distance: sim(x,y) = |ord(x) - ord(y)|
        // EnumCyclicDistance - computes the similarity between two enum values as their cyclic distance
        // Table - uses a table to obtain the similarity between two values. Allowed values are Strings or Enums. The table is read from a text file.
        // TableSimilarity(List<String> values).setSimilarity(value1,value2,similarity)
    }

    public void cycle(CBRQuery query) throws ExecutionException {
        Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
        eval = SelectCases.selectTopKRR(eval, 5);

        List<String> cases = new ArrayList<>();
        for (RetrievalResult nse : eval)
            cases.add(nse.get_case().getDescription().toString());

        addToFile(cases);
    }

    private void addToFile(List<String> cases) {
        try{
            String content = "";
            for (String c : cases)
                content += c + "\n";

            String path="..\\data\\ret.txt";
            File file = new File(path);

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(content);
            bw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    private List<SimilarityDto> readFromFile() {
        List<SimilarityDto> descriptions = new ArrayList<>();
        try {
            String path="..\\data\\ret.txt";
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;

            while ((st = br.readLine()) != null)
                descriptions.add(processSingleResult(st));
        } catch (Exception e) {
            System.out.println(e);
        }
        return descriptions;
    }

    private SimilarityDto processSingleResult(String result) {
        String[] parts = result.split(",");
        int corsNumber = Integer.parseInt(parts[0].split("=")[1]);
        double cpuFrequency = Double.parseDouble(parts[1].split("=")[1]);
        int cpuCache = Integer.parseInt(parts[2].split("=")[1]);
        String cpuBrand = parts[3].split("=")[1];
        String cpuName = parts[4].split("=")[1];
        String ramType = parts[5].split("=")[1];
        int ramCapacity = Integer.parseInt(parts[6].split("=")[1]);
        int ramFrequency = Integer.parseInt(parts[7].split("=")[1]);
        String storageType = parts[8].split("=")[1];
        int rws = Integer.parseInt(parts[9].split("=")[1]);
        int storageCapacity = Integer.parseInt(parts[10].split("=")[1]);

        return new SimilarityDto(corsNumber, cpuFrequency, cpuCache, cpuBrand, cpuName, ramType, ramCapacity,
                ramFrequency, storageType, rws, storageCapacity);
    }

    public void postCycle() throws ExecutionException {

    }

    public CBRCaseBase preCycle() throws ExecutionException {
        _caseBase.init(_connector);
        java.util.Collection<CBRCase> cases = _caseBase.getCases();
        //for (CBRCase c: cases)
        //    System.out.println(c.getDescription());
        return _caseBase;
    }

    public List<SimilarityDto> test(int cn, double cf, int cc, String cb, String rt, int rc, int rf, String st,
                                      int rws, int sc) {
        StandardCBRApplication recommender = new PointFourService();
        try {
            recommender.configure();

            recommender.preCycle();

            CBRQuery query = new CBRQuery();
            CaseDescription caseDescription = new CaseDescription();

            // TODO
            caseDescription.setCorsNumber(cn);
            caseDescription.setCpuFrequency(cf);
            caseDescription.setCpuCache(cc);
            caseDescription.setCpuBrand(cb);

            caseDescription.setRamType(rt);
            caseDescription.setRamCapacity(rc);
            caseDescription.setRamFrequency(rf);

            caseDescription.setStorageType(st);
            caseDescription.setRws(rws);
            caseDescription.setStorageCapacity(sc);

            query.setDescription( caseDescription );

            recommender.cycle(query);

            return readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
