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
package org.zetool.rndutils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.zetool.rndutils.generators.GeneralRandom;

public class RandomUtilsTest {

    @Test
    public void testChooseRandomlyAbsolute() {
        final double NUMBER_OF_TRIES = 1000000;
        double[] probabilities = new double[10];
        double[] frequencies = new double[10];

        probabilities[0] = 5.0;
        probabilities[1] = 5.0;
        probabilities[2] = 5.0;
        probabilities[3] = 20.0;
        probabilities[4] = 15.0;
        probabilities[5] = 5.0;
        probabilities[6] = 0.0;
        probabilities[7] = 10.0;
        probabilities[8] = 20.0;
        probabilities[9] = 25.0;

        for (int i = 0; i < NUMBER_OF_TRIES; i++) {
            int randomIndex = RandomUtils.getInstance().chooseRandomlyAbsolute(probabilities);
            frequencies[randomIndex]++;
        }

        double sum = 0.0;
        for (int i = 0; i < probabilities.length; i++) {
            sum += probabilities[i];
        }

        for (int i = 0; i < probabilities.length; i++) {
            System.out.print("Index " + i + " ");
            System.out.print("absolute value " + probabilities[i] + " ");
            System.out.print("probability " + (probabilities[i] / sum) + " ");
            System.out.println("frequency " + (frequencies[i] / NUMBER_OF_TRIES));
        }
    }

    @Test
    public void accessAllProbabilities() {
        double[] probabilities = new double[]{20, 49, 30, 1};
        GeneralRandom r = mock(GeneralRandom.class);
        (RandomUtils.getInstance()).setRandomGenerator(r);

        int sum = 0;
        for (int j = 0; j < probabilities.length; ++j) {
            sum += (int)probabilities[j];
            for (int i = sum - (int)probabilities[j]; i < sum; ++i) {
                // Add some very small value, should be in the correct index
                when(r.nextDouble()).thenReturn(1 / 100.0 * i + 0.0001);
                assertThat(RandomUtils.getInstance().chooseRandomlyAbsolute(probabilities), is(equalTo(j)));
                
                // Add a relatively large value, less than one percent. Should be still in the correct range.
                when(r.nextDouble()).thenReturn(1 / 100.0 * i + 0.00999);
                assertThat(RandomUtils.getInstance().chooseRandomlyAbsolute(probabilities), is(equalTo(j)));
            }
        }
    }

}
