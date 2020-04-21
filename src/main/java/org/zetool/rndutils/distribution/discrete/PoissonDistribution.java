
package org.zetool.rndutils.distribution.discrete;

import org.zetool.rndutils.distribution.DiscreteDistribution;

/**
 * The class {@code PoissonDistribution} represents a poisson distributed
 * random variable.
 * @author Jan-Philipp Kappmeier
 */
public class PoissonDistribution extends DiscreteDistribution {
	/** */
  private final double lambda = 1;

	/**
	 * Creates a new instance of {@code PoissonDistribution}.
	 * @param lambda the parameter describing the expected value
	 */
	public PoissonDistribution( double lambda ) {
		super( 0, Integer.MAX_VALUE );
	}

	/**
	 *
	 * @param min the minimal value returned by the distribution
	 * @param lambda the parameter describing the expected value
	 */
	public PoissonDistribution( int min, double lambda ) {
		super( min, Integer.MAX_VALUE );
	}

	/**
	 * Returns the name of the class.
	 * @return the name of the class
	 */
	@Override
	public String toString() {
		return "PoissonDistribution";
	}

	@Override
	public double getDensityAt( double x ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	@Override
	public Integer getNextRandom() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}
}
