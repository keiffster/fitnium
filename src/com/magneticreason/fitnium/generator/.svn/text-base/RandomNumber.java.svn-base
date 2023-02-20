package com.magneticreason.fitnium.generator;

public class RandomNumber implements FitniumUniqueValueGenerator {

	private java.util.Random rand = new java.util.Random ();
	private int range = Integer.MAX_VALUE;
	
	public void initialise ( String params ) throws Exception {
	}

	public String getNextValue() throws Exception  {
		return Integer.toString( this.rand.nextInt( this.range ) );
	}

	public String getNextValue ( String params ) throws Exception  {
		return getNextValue ();
	}

	public void reset(String intialValue)  throws Exception {
		range = Integer.parseInt( intialValue );
	}

	public void reset()  throws Exception {
	}
}
