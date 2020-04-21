package org.zetool.rndutils.distribution.discrete;

import org.zetool.rndutils.RandomUtils;
import org.zetool.rndutils.distribution.DiscreteDistribution;
import org.zetool.math.Combinatorics;

/**
 * The class {@code BinomialDistribution} represents a binomial distributed
 * random variable.
 * @author Jan-Philipp Kappmeier
 */
public class BinomialDistribution extends DiscreteDistribution {
	double p;
	double q;
	int n;

	/**
	 * Creates a new instance of {@code BinomialDistribution} with default success parameter {@literal p = 0.5}.
	 * @param n the number of repetitions
	 */
	public BinomialDistribution( int n ) {
		this( 0, n, 0.5 );
	}

	/**
	 * Creates a new instance of {@code BinomialDistribution}.
	 * @param n the number of repetitions
	 * @param p the success probability
	 */
	public BinomialDistribution( int n, double p ) {
		this( 0, n, p );
	}

	/**
	 *
	 * @param min the minimal binomial value
	 * @param n the number of repettions
	 * @param p the success probability
	 */
	public BinomialDistribution( int min, int n, double p ) {
		super( min, n + min );
		this.p = p;
		this.q = 1-p;
		this.n = n;
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
		do
			sum += getProbability( ++I );
		while( sum < inv && I < n );
		return I + min;
	}

	public double getProbability( int k ) {
		double a = Math.pow( p, k );
		double b = Math.pow( q, n-k );
		long c = Combinatorics.bico( n, k );
		return a * b * c;
	}

	/**
	 * Returns the name of the class.
	 * @return the name of the class
	 */
	@Override
	public String toString() {
		return "BinomialDistribution";
	}
}
