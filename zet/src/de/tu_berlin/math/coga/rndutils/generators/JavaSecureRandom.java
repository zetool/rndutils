/**
 * Class JavaSecureRandom
 * Erstellt 04.05.2008, 23:58:07
 */

package de.tu_berlin.math.coga.rndutils.generators;

import java.security.SecureRandom;

/**
 * A wrapper for the {@link java.security.SecureRandom} class.
 * @author Jan-Philipp Kappmeier
 */
public class JavaSecureRandom extends SecureRandom implements GeneralRandom {
	/**
	 * A wrapper class for the {@link SecureRandom} generator.
	 * @see SecureRandom
	 */
	public JavaSecureRandom() {
		super();
  }

	/**
	 * A wrapper class for the {@link SecureRandom} generator.
	 * @param seed an array of seeds
	 * @see SecureRandom
	 */
	public JavaSecureRandom( byte seed[]) {
		super( seed );
	}

	public String getName() {
		return "Java Secure Random Number Generator";
	}

	public String getDesc() {
		return "";
	}
}