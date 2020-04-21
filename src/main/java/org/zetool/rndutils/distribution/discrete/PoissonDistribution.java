/* zet evacuation tool copyright (c) 2007-20 zet evacuation team
 *
 * This program is free software; you can redistribute it and/or
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
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
