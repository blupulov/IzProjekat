package com.example.owl.connector;

import com.example.owl.model.CaseDescription;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.util.FileIO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

public class CsvConnector implements Connector{

    @Override
    public Collection<CBRCase> retrieveAllCases() {
        LinkedList<CBRCase> cases = new LinkedList<CBRCase>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("../data/comps.csv")));

            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || (line.length() == 0))
                    continue;
                String[] values = line.split(";");

                CBRCase cbrCase = new CBRCase();

                CaseDescription caseDescription = new CaseDescription();

                // TODO
                caseDescription.setCorsNumber(Integer.parseInt(values[0]));
                caseDescription.setCpuFrequency(Double.parseDouble(values[1]));
                caseDescription.setCpuCache(Integer.parseInt(values[2]));
                caseDescription.setCpuBrand(values[3]);
                caseDescription.setRamType(values[4]);
                caseDescription.setRamCapacity(Integer.parseInt(values[5]));
                caseDescription.setRamFrequency(Integer.parseInt(values[6]));
                caseDescription.setStorageType(values[7]);
                caseDescription.setStorageCapacity(Integer.parseInt(values[8]));
                caseDescription.setRws(Integer.parseInt(values[9]));
                caseDescription.setCpuName(values[10]);

                cbrCase.setDescription(caseDescription);
                cases.add(cbrCase);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cases;
    }

    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter arg0) {
        return null;
    }

    @Override
    public void storeCases(Collection<CBRCase> arg0) {
    }

    @Override
    public void close() {
    }

    @Override
    public void deleteCases(Collection<CBRCase> arg0) {
    }

    @Override
    public void initFromXMLfile(URL arg0) throws InitializingException {
    }
}
