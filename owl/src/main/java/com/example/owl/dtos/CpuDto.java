package com.example.owl.dtos;

public class CpuDto {
    private String name;
    private String mf;
    private String bf;
    private String cn;

    public CpuDto(String name, String mf, String bf, String cn){
        this.bf = bf;
        this.cn = cn;
        this.mf = mf;
        this.name = name;
    }

    public CpuDto() { }

    public String getBf() {
        return bf;
    }

    public String getCn() {
        return cn;
    }

    public String getMf() {
        return mf;
    }

    public String getName() {
        return name;
    }

    public void setBf(String bf) {
        this.bf = bf;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public void setMf(String mf) {
        this.mf = mf;
    }

    public void setName(String name) {
        this.name = name;
    }

}
