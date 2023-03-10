package com.magneticreason.fitnium.plugins.tokens;

import java.util.Random;

public class RandomToken extends FitniumToken {
	
    public String getString() {
		return Long.toString(System.currentTimeMillis(), Character.MAX_RADIX).toUpperCase();
	}

	public String getEmail() {
		return getString() + "@gmail.com";
	}

	public String getInt() {
		Random generator = new Random(System.currentTimeMillis());
		return Integer.toString(generator.nextInt(10000));
	}

	public String getDigits(int digits) {
		Random generator = new Random(System.currentTimeMillis());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digits; i++) {
			sb.append(generator.nextInt(10));
		}
		return sb.toString();
	}

}
