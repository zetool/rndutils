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
