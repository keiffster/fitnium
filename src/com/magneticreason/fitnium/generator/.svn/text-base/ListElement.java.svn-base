package com.magneticreason.fitnium.generator;

public class ListElement implements FitniumUniqueValueGenerator {

    private int counter = 0;
    private String[] elements = null;

    public void initialise(String params) throws Exception {
        this.elements = params.split(",");
    }

    public String getNextValue() throws Exception {
        if (this.counter == this.elements.length) {
            this.counter = 0;
        }
        return this.elements[this.counter++];
    }

    public String getNextValue(String params) throws Exception {
        int value = Integer.parseInt(params);
        return this.elements[value];
    }

    public void reset(String intialValue) throws Exception {
        this.counter = Integer.parseInt(intialValue);
    }

    public void reset() throws Exception {
        this.counter = 0;
    }
}
