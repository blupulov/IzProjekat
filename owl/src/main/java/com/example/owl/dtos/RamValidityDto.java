package com.example.owl.dtos;

public class RamValidityDto {
    private String ramName;
    private int ramCap;

    public RamValidityDto() {}

    public RamValidityDto(String name, int rc) {
        this.ramCap = rc;
        this.ramName = name;
    }

    public String getRamName() {
        return ramName;
    }

    public void setRamName(String ramName) {
        this.ramName = ramName;
    }

    public int getRamCap() {
        return ramCap;
    }

    public void setRamCap(int ramCap) {
        this.ramCap = ramCap;
    }
}
