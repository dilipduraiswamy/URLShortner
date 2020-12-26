package com.aeiou.util;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class Generator.
 */
@Component
public class Generator {
	
	/**
	 * Gets the alpha numeric string.
	 *
	 * @param n the n
	 * @return the alpha numeric string
	 */
	public String getAlphaNumericString(int n) {

		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			int index = (int) (AlphaNumericString.length() * Math.random());

			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}
}
