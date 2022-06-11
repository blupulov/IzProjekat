package com.example.owl.services;

import com.example.owl.dtos.ComputerTypeDTO;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PointTwoService {
    @Autowired
    private PointOneService pointOneService;
    private final FIS fis;

    public PointTwoService() {
        String filePath = "..\\jfl-data\\temp.fcl";
        fis = FIS.load(filePath, true);
        if (fis == null) {
            System.err.println("Can't load file");
            System.exit(1);
        }
    }

    public void Test() {
        fis.setVariable("cpu_num", 4);
        fis.setVariable("mem_cap", 8);
        fis.setVariable("power", 800);
        fis.setVariable("rws", 2400);

        fis.evaluate();

        Variable analise = fis.getFunctionBlock("purpose_analysis").getVariable("purpose");
        System.out.println(analise.getValue());
    }

    public ComputerTypeDTO getComputerTypeDTOWithNumbers(int cpuNum, int memCap, int power, int rws) {
        double result = getComputerTypeValue(cpuNum, memCap, power, rws);
        return calculateComputerType(result);
    }

    public ComputerTypeDTO getComputerTypeDTOWithName(String cpuName, String memName, String powerName, String storageName, String storageType) {
        int cn = pointOneService.getCpuCoreNumbers(cpuName);
        int mc = pointOneService.getMemCapacity(memName);
        int ps = pointOneService.getPowerSupply(powerName);
        int rws = 0;
        if (Objects.equals(storageType, "hdd"))
            rws = pointOneService.getHddRws(storageName);
        else
            rws = pointOneService.getSsdRws(storageName);
        if (cn == 0 || mc == 0 || ps == 0 || rws == 0)
            return null;

        double result = getComputerTypeValue(cn, mc, ps, rws);

        return calculateComputerType(result);
    }

    private ComputerTypeDTO calculateComputerType(double result) {
        ComputerTypeDTO dto = new ComputerTypeDTO();

        dto.setHomePC(100 - (Math.abs(10 - result)/result) * 100);
        dto.setBusinessPC(100 - (Math.abs(30 - result)/result) * 100);
        dto.setGamingPC(100 - (Math.abs(50 - result)/result) * 100);
        dto.setMiningPC(100 - (Math.abs(70 - result)/result) * 100);
        return dto;
    }

    private double getComputerTypeValue(int cpuNum, int memCap, int power, int rws) {
        fis.setVariable("cpu_num", cpuNum);
        fis.setVariable("mem_cap", memCap);
        fis.setVariable("power", power);
        fis.setVariable("rws", rws);

        fis.evaluate();

        Variable analise = fis.getFunctionBlock("purpose_analysis").getVariable("purpose");

        return analise.getValue();
    }
}
