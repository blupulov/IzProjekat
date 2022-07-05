package com.example.owl.dtos;

public class PowerSupplyValidityDto {
    private String powerSupplyName;
    private int power;

    public PowerSupplyValidityDto() {}

    public PowerSupplyValidityDto(String name, int p) {
        this.power = p;
        this.powerSupplyName = name;
    }

    public String getPowerSupplyName() {
        return powerSupplyName;
    }

    public void setPowerSupplyName(String powerSupplyName) {
        this.powerSupplyName = powerSupplyName;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
