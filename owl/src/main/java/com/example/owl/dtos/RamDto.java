package com.example.owl.dtos;

public class RamDto {
    private String name;
    private String brand;
    private String cap;

    public RamDto() { }

    public RamDto(String ram, String brand, String cap) {
        this.name = ram;
        this.brand = brand;
        this.cap = cap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getCap() {
        return cap;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }
}
