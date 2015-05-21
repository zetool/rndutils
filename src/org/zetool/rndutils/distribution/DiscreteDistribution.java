
package org.zetool.rndutils.distribution;

/**
 * The class {@code DiscreteDistribution} represents a discrete probability
 * distribution.
 * @author Jan-Philipp Kappmeier
 */
public abstract class DiscreteDistribution extends Distribution<Integer> {

	/**
	 * Creates a new instance of {@code DiscreteDistribution}.
	 * @param min the minimal value that can be returned by the distribution
	 * @param max the maximal value that can be returned by the distribution
	 */
	public DiscreteDistribution( int min, int max ) {
		super( min, max );
	}

	/**
	 * {@inheritDoc}
	 * @throws IllegalArgumentException if min is greater than max
	 */
	@Override
	public void setParameter( Integer min, Integer max ) throws IllegalArgumentException	{
		if( min > max ) {
			throw new IllegalArgumentException ( "Minimum value is greater than maximum." );
		}
		this.min = min;
		this.max = max;
	}

	/**
	 * Returns the name of the class.
	 * @return the name of the class
	 */
	@Override
	public String toString() {
		return "DiscreteDistribution";
	}
}
