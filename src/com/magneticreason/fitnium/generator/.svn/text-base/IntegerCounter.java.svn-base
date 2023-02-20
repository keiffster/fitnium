package com.magneticreason.fitnium.generator;

public class IntegerCounter implements FitniumUniqueValueGenerator {

	private int currentValue = 0;
	
	public void initialise ( String params ) throws Exception {
	}

	public String getNextValue()  throws Exception {
		return Integer.toString( currentValue++ );
	}

	public String getNextValue ( String params )  throws Exception {
		return Integer.toString( currentValue++ );
	}

	public void reset(String intialValue)  throws Exception {
		currentValue = Integer.parseInt( intialValue );
	}

	public void reset()  throws Exception {
		currentValue = 0;
	}
}
