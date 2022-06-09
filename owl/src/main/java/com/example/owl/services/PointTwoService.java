package com.example.owl.services;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import org.springframework.stereotype.Service;

@Service
public class PointTwoService {

    public PointTwoService() {

    }

    public void Test() {
        String filePath = "..\\jfl-data\\temp.fcl";
        FIS fis = FIS.load(filePath, true);
        if (fis == null) {
            System.err.println("Can't load file");
            System.exit(1);
        }

        fis.setVariable("cpu_num", 4);
        fis.setVariable("mem_cap", 8);
        fis.setVariable("power", 800);
        fis.setVariable("rws", 2400);

        fis.evaluate();

        Variable analise = fis.getFunctionBlock("purpose_analysis").getVariable("purpose");
        System.out.println(analise.getValue());
    }
}
