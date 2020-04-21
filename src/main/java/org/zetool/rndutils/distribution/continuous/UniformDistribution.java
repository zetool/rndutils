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
package org.zetool.rndutils.distribution.continuous;

import org.zetool.rndutils.RandomUtils;
import org.zetool.rndutils.distribution.ContinousDistribution;
import org.zetool.rndutils.distribution.Distribution;

/**
 * Represents a uniformly distributed random variable. All values are uniformly distributed within the
 * interval between min and max.
 * @see Distribution
 * @author Jan-Philipp Kappmeier
 */
public class UniformDistribution extends ContinousDistribution {
  
  /**
   * Creates a new instance of {@code UniformDistribution} with values in the interval 0 and 1.
   */
  public UniformDistribution() {
    super( 0, 1 );
  }
  
  /**
   * Creates a new instance of {@code UniformDistribution} within a given interval.
   * @param min the minimal value that can be taken by the random variable
   * @param max the maximal value that can be taken by the random variable
   * @throws IllegalArgumentException if min is smaller than max
   */
  public UniformDistribution( double min, double max ) throws IllegalArgumentException {
    super( min, max );
  }
  
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getDensityAt( double x ) {
		if( x <= getMin() || x >= getMax() )
			return 0;
		else
			return 1/(getMax() - getMin());
	}
	
  /**
   * Gets the next random number according to this distribution. The generated
   * value is uniformly distributed in the interval defined by the values {@link #getMin()} and {@link #getMax()}.
   * @return the random value
   */
  @Override
  public Double getNextRandom() {
		double randomNumber = RandomUtils.getInstance().getRandomGenerator().nextDouble();
		return getMin() + (getMax() - getMin()) * randomNumber;
  }

	@Override
	public Object clone() throws CloneNotSupportedException {
		UniformDistribution d = (UniformDistribution)super.clone();
		return d;
	}
}
