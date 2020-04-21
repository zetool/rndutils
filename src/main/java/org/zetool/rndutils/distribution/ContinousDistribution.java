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
package org.zetool.rndutils.distribution;

/**
 * The class {@code ContinousDistribution} represents a continous
 * probability distribution with values in the real numbers.
 * @author Jan-Philipp Kappmeier
 */
public abstract class ContinousDistribution extends Distribution<Double> {

	/**
	 * Creates a new instance of {@code ContinousDistribution}.
	 * @param min the minimal value that can be taken by the distribution
	 * @param max the maximal value that can be taken by the distribution
	 */
	public ContinousDistribution( double min, double max ) {
		super( min, max );
	}

	/**
	 * Returns the name of the class.
	 * @return the name of the class
	 */
	@Override
	public String toString() {
		return "ContinousDistribution";
	}

	/**
	 * {@inheritDoc}
	 * @throws IllegalArgumentException if min is greater than max
	 */
	@Override
	public void setParameter( Double min, Double max ) throws IllegalArgumentException	{
		if( min > max ) {
			throw new IllegalArgumentException ( "Minimum value is greater than maximum." );
		}
		this.min = min;
		this.max = max;
	}
}
