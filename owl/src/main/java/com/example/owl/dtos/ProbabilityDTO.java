package com.example.owl.dtos;

public class ProbabilityDTO {
    private String nodeName;
    private double probability;

    public ProbabilityDTO() { }

    public ProbabilityDTO(String name, double probability) {
        this.nodeName = name;
        this.probability = probability;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }


}
