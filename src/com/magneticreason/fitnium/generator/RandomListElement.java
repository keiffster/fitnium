package com.magneticreason.fitnium.generator;

public class RandomListElement implements FitniumUniqueValueGenerator {

    private java.util.Random rand = new java.util.Random();
    @SuppressWarnings("unused")
	private int range = 0;
    private String[] elements = null;

    public void initialise(String params) throws Exception {
        this.elements = params.split(",");
    }

    public String getNextValue() throws Exception {
        return this.elements[this.rand.nextInt(this.elements.length)];
    }

    public String getNextValue(String params) throws Exception {
        return this.getNextValue();
    }

    public void reset(String intialValue) throws Exception {
        range = Integer.parseInt(intialValue);
    }

    public void reset() throws Exception {
    }
}
