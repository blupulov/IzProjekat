package com.example.owl.dtos;

public class ComputerTypeDTO {
    private double homePC;
    private double businessPC;
    private double gamingPC;
    private double miningPC;

    public ComputerTypeDTO() {}

    public ComputerTypeDTO(double h, double b, double g, double m) {
        homePC = h;
        businessPC = b;
        gamingPC = g;
        miningPC = m;
    }

    public double getHomePC() {
        return homePC;
    }

    public void setHomePC(double homePC) {
        this.homePC = homePC;
    }

    public double getBusinessPC() {
        return businessPC;
    }

    public void setBusinessPC(double businessPC) {
        this.businessPC = businessPC;
    }

    public double getGamingPC() {
        return gamingPC;
    }

    public void setGamingPC(double gamingPC) {
        this.gamingPC = gamingPC;
    }

    public double getMiningPC() {
        return miningPC;
    }

    public void setMiningPC(double miningPC) {
        this.miningPC = miningPC;
    }
}
