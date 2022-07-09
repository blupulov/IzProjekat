package com.example.owl.model;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class CaseDescription implements CaseComponent {
    private int corsNumber;
    private double cpuFrequency;
    private int cpuCache;
    private String cpuBrand;
    private String cpuName;
    private String ramType;
    private int ramCapacity;
    private int ramFrequency;
    private String storageType;
    private int rws;
    private int storageCapacity;

    public CaseDescription() {}

    public CaseDescription(int corsNumber, double cpuFrequency, int cpuCache, String cpuBrand, String cpuName,
                           String ramType, int ramCapacity, int ramFrequency, String storageType, int rws,
                           int st) {
        this.corsNumber = corsNumber;
        this.cpuFrequency = cpuFrequency;
        this.cpuCache = cpuCache;
        this.cpuBrand = cpuBrand;
        this.cpuName = cpuName;
        this.ramType = ramType;
        this.ramCapacity = ramCapacity;
        this.ramFrequency = ramFrequency;
        this.storageType = storageType;
        this.rws = rws;
        this.storageCapacity = st;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public int getCorsNumber() {
        return corsNumber;
    }

    public void setCorsNumber(int corsNumber) {
        this.corsNumber = corsNumber;
    }

    public double getCpuFrequency() {
        return cpuFrequency;
    }

    public void setCpuFrequency(double cpuFrequency) {
        this.cpuFrequency = cpuFrequency;
    }

    public int getCpuCache() {
        return cpuCache;
    }

    public void setCpuCache(int cpuCache) {
        this.cpuCache = cpuCache;
    }

    public String getCpuBrand() {
        return cpuBrand;
    }

    public void setCpuBrand(String cpuBrand) {
        this.cpuBrand = cpuBrand;
    }

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public int getRamCapacity() {
        return ramCapacity;
    }

    public void setRamCapacity(int ramCapacity) {
        this.ramCapacity = ramCapacity;
    }

    public int getRamFrequency() {
        return ramFrequency;
    }

    public void setRamFrequency(int ramFrequency) {
        this.ramFrequency = ramFrequency;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public int getRws() {
        return rws;
    }

    public void setRws(int rws) {
        this.rws = rws;
    }

    @Override
    public String toString() {
        return "corsNumber=" + corsNumber +
                ",cpuFrequency=" + cpuFrequency +
                ",cpuCache=" + cpuCache +
                ",cpuBrand=" + cpuBrand +
                ",cpuName=" + cpuName +
                ",ramType=" + ramType +
                ",ramCapacity=" + ramCapacity +
                ",ramFrequency=" + ramFrequency +
                ",storageType=" + storageType +
                ",rws=" + rws +
                ",storageCapacity=" + storageCapacity;
    }

    @Override
    public Attribute getIdAttribute() {
        return new Attribute("id",this.getClass());
    }
}
