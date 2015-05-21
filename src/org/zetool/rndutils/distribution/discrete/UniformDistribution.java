
package org.zetool.rndutils.distribution.discrete;

import org.zetool.rndutils.RandomUtils;
import org.zetool.rndutils.distribution.DiscreteDistribution;

/**
 * The class {@code UniformDistribution} represents a discrete uniformly
 * distributet random variable.
 * @author Jan-Philipp Kappmeier
 */
public class UniformDistribution extends DiscreteDistribution {
	private double p;

	/**
	 * Creates a new instance of {@code BinomialDistribution}.
	 * @param min the minimum value of the uniform distribution
	 * @param max the maximum value of the uniform distribution
	 */
	public UniformDistribution( int min, int max ) {
		super( min, max );
		p = 1.0/(double)(max-min+1);
	}

	@Override
	public double getDensityAt( double x ) {
		return getProbability( (int)Math.floor( x ) );
	}

	@Override
	public Integer getNextRandom() {
		double inv = RandomUtils.getInstance().getRandomGenerator().nextDouble();
		double sum = 0;
		int I = -1;
		do {
			++I;
			sum += p;
		} while( sum < inv && I < (max-min+1) );
		return I + min;
	}

	public double getProbability( int k ) {
		return k < min || k > max ? 0 : p;
	}
}
