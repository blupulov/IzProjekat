package com.example.owl.dtos;

public class StorageValidityDto {
    private String storageName;
    private int rws;

    public StorageValidityDto() {}

    public StorageValidityDto(String name, int rws) {
        this.rws = rws;
        this.storageName = name;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public int getRws() {
        return rws;
    }

    public void setRws(int rws) {
        this.rws = rws;
    }
}
