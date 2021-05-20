package com.example.demo.presentation.dto;

public class RegulateAccoundListDto {
    private String target;
    private String adder;

    public RegulateAccoundListDto(String target, String adder) {
        this.target = target;
        this.adder = adder;
    }

    public void setAdder(String adder) {
        this.adder = adder;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAdder() {
        return adder;
    }

    public String getTarget() {
        return target;
    }
}
