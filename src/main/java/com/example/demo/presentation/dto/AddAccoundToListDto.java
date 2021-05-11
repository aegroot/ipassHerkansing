package com.example.demo.presentation.dto;

public class AddAccoundToListDto {
    private  long target;
    private  long adder;

    public AddAccoundToListDto(long target, long adder) {
        this.target = target;
        this.adder = adder;
    }

    public long getAdder() {
        return adder;
    }

    public long getTarget() {
        return target;
    }
}
