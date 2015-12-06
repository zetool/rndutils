
package org.zetool.rndutils.generators;

import org.zetool.rndutils.generators.GeneralRandomWrapper;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import org.junit.Test;
import org.zetool.rndutils.generators.GeneralRandom;


/**
 *
 * @author Jan-Philipp Kappmeier
 */
public class TestGeneralRandomWrapper {
    private static class ExpectedAssertionError extends AssertionError {
    }
    private static class SeedAssertionError extends AssertionError {
    }
    
    GeneralRandom mgr = new GeneralRandom() {
        private boolean called = false;
        @Override
        public void setSeed(long seed) {
            if(!called) {
                called = true;
                return;
            }
            throw new SeedAssertionError();
        }

        @Override
        public boolean nextBoolean() {
            throw new ExpectedAssertionError();
        }

        @Override
        public void nextBytes(byte[] bytes) {
            throw new ExpectedAssertionError();
        }

        @Override
        public byte nextByte() {
            throw new ExpectedAssertionError();
        }

        @Override
        public char nextChar() {
            throw new ExpectedAssertionError();
        }

        @Override
        public short nextShort() {
            throw new ExpectedAssertionError();
        }

        @Override
        public int nextInt() {
            throw new ExpectedAssertionError();
        }

        @Override
        public int nextInt(int n) {
            throw new ExpectedAssertionError();
        }

        @Override
        public long nextLong() {
            throw new ExpectedAssertionError();
        }

        @Override
        public float nextFloat() {
            throw new ExpectedAssertionError();
        }

        @Override
        public double nextDouble() {
            throw new ExpectedAssertionError();
        }

        @Override
        public double nextGaussian() {
            throw new ExpectedAssertionError();
        }

        @Override
        public String getName() {
            throw new ExpectedAssertionError();
        }

        @Override
        public String getDesc() {
            throw new ExpectedAssertionError();
        }
            
            };
    GeneralRandomWrapper r = new GeneralRandomWrapper(mgr);

    @Test(expected = SeedAssertionError.class)
    public void setSeed() {
        r.setSeed(1);
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextBytes() {
        byte[] bytes = new byte[]{};
        r.nextBytes(bytes);
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextIntWithBound() {
        int bound = 1;
        r.nextInt(bound);
    }
    
    @Test(expected = ExpectedAssertionError.class)
    public void nextInt() {
        r.nextInt();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextLong() {
        r.nextLong();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextBoolean() {
        r.nextBoolean();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextFloat() {
        r.nextFloat();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextDouble() {
        r.nextDouble();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextGaussian() {
        r.nextGaussian();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void ints() {
        long streamSize = 2;
        
        int randomNumberOrigin = 0;
        int randomNumberBound = 1;
        IntStream is = r.ints();
        r.ints(streamSize);
        r.ints(randomNumberOrigin, randomNumberBound);
        r.ints(streamSize, randomNumberOrigin, randomNumberBound);
        is.iterator().next();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void longs() {
        long streamSize = 0;
        long randomNumberOrigin = 0;
        long randomNumberBound = 1;
        LongStream ls = r.longs();
        r.longs(streamSize);
        r.longs(randomNumberOrigin, randomNumberBound);
        r.longs(streamSize, randomNumberOrigin, randomNumberBound);
        ls.iterator().next();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void doubles() {
        long streamSize = 0;
        double randomNumberOrigin = 0;
        double randomNumberBound = 1;
        r.doubles().iterator().next();
        r.doubles(streamSize);
        r.doubles(randomNumberOrigin, randomNumberBound);
        r.doubles(streamSize, randomNumberOrigin, randomNumberBound);
    }
}
