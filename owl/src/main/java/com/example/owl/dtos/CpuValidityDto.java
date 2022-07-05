package com.example.owl.dtos;

public class CpuValidityDto {
    private String cpuName;
    private int corsNum;

    public CpuValidityDto() { }

    public CpuValidityDto(String name, int cn) {
        this.corsNum = cn;
        this.cpuName = name;
    }

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public int getCorsNum() {
        return corsNum;
    }

    public void setCorsNum(int corsNum) {
        this.corsNum = corsNum;
    }
}
