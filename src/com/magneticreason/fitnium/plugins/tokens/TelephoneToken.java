package com.magneticreason.fitnium.plugins.tokens;

import java.util.Random;

public class TelephoneToken extends FitniumToken {

	public String getMobile ( ) {
		return "07" + digits(9);
	}
	
	public String getLandline ( ) {
		return "01" + digits(9);
	}
	
	protected String digits(int digits) {
		Random generator = new Random(System.currentTimeMillis());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digits; i++) {
			sb.append(generator.nextInt(10));
		}
		return sb.toString();
	}

}
