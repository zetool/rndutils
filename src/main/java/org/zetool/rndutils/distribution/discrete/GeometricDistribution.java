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

import org.zetool.rndutils.RandomUtils;
import org.zetool.rndutils.distribution.DiscreteDistribution;

/**
 * The class {@code GeometricDistribution} represents a geometrically
 * distributed random variable.
 * @author Jan-Philipp Kappmeier
 */
public class GeometricDistribution extends DiscreteDistribution {
	/** The parameter for the geometric distribution. */
	private double p;
	private double q;

	/**
	 * Creates a new instance of {@code GeometricDistribution}.
	 * @param min the minimal value for the random value
	 * @param max the maximal value for the random value
	 */
	public GeometricDistribution( int min, int max ) {
		this( min, max, 0.5 );
	}

	/**
	 * Creates a new instance of {@code GeometricDistribution}.
	 * @param min the minimal value for the random value
	 * @param max the maximal value for the random value
	 * @param p the parameter for the distribution the 
	 */		
	public GeometricDistribution( int min, int max, double p ) {
		super( min, max );
		this.p = p;
		this.q = 1-p;
	}

	/**
	 * Returns the name of the class.
	 * @return the name of the class
	 */
	@Override
	public String toString() {
		return "GeometricDistribution";
	}

	@Override
	public double getDensityAt( double x ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	@Override
	public Integer getNextRandom() {
		double u = RandomUtils.getInstance().getRandomGenerator().nextDouble();
		double z = Math.log( u )/Math.log( q );
		int k = (int)Math.floor( z );
		return k + 1;
	}
}
