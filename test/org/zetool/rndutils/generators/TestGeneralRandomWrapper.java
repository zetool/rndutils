package org.zetool.rndutils.generators;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author Jan-Philipp Kappmeier
 */
public class TestGeneralRandomWrapper {

    private static class ExpectedAssertionError extends AssertionError {
    }

    private static class SeedAssertionError extends AssertionError {
    }
    
    private static final long SEED = 23;
    private static final byte[] BYTES = new byte[] {2, 4};
    private static final int INT = 6;
    private static final int BOUNDED_INT = 7;
    private static final long LONG = 12345678932L;
    private static final float FLOAT = 3.123f;
    private static final double DOUBLE = 0.80;
    
    private AtomicLong al = new AtomicLong();
    private final GeneralRandom MOCK_RANDOM = new GeneralRandom() {

        @Override
        public double nextDouble() {
            return DOUBLE;
        }

        @Override
        public float nextFloat() {
            return FLOAT;
        }

        @Override
        public boolean nextBoolean() {
            return true;
        }

        @Override
        public long nextLong() {
            return LONG;
        }

        @Override
        public int nextInt(int bound) {
            return BOUNDED_INT;
        }

        @Override
        public int nextInt() {
            return INT;
        }

        @Override
        public void nextBytes(byte[] bytes) {
            System.arraycopy(BYTES, 0, bytes, 0, BYTES.length);
        }

        @Override
        public void setSeed(long seed) {
            al.set(seed);
        }

        @Override
        public byte nextByte() {
            throw new UnsupportedOperationException("Unsupported.");
        }

        @Override
        public char nextChar() {
            throw new UnsupportedOperationException("Unsupported.");
        }

        @Override
        public short nextShort() {
            throw new UnsupportedOperationException("Unsupported.");
        }

        @Override
        public double nextGaussian() {
            throw new UnsupportedOperationException("Unsupported.");
        }

        @Override
        public String getName() {
            throw new UnsupportedOperationException("Unsupported.");
        }

        @Override
        public String getDesc() {
            throw new UnsupportedOperationException("Unsupported.");
        }
        
        public long getSeed() {
            return 1;
        }
    };
    private final GeneralRandom failRandom = new GeneralRandom() {
        private boolean called = false;

        @Override
        public void setSeed(long seed) {
            if (!called) {
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
    private final GeneralRandomWrapper failRandomWrapper = new GeneralRandomWrapper(failRandom);
    private final GeneralRandomWrapper workingRandomWrapper = new GeneralRandomWrapper(MOCK_RANDOM);

    @Test(expected = SeedAssertionError.class)
    public void setSeed() {
        workingRandomWrapper.setSeed(SEED);
        assertThat(al.get(), is(equalTo(SEED)));
        failRandomWrapper.setSeed(1);
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextBytes() {
        byte[] bytes = new byte[BYTES.length];
        workingRandomWrapper.nextBytes(bytes);
        assertThat(bytes, is(equalTo(BYTES)));
        failRandomWrapper.nextBytes(bytes);
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextIntWithBound() {
        int bound = 1;
        assertThat(workingRandomWrapper.nextInt(bound), is(equalTo(BOUNDED_INT)));
        failRandomWrapper.nextInt(bound);
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextInt() {
        assertThat(workingRandomWrapper.nextInt(), is(equalTo(INT)));
        failRandomWrapper.nextInt();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextLong() {
        assertThat(workingRandomWrapper.nextLong(), is(equalTo(LONG)));
        failRandomWrapper.nextLong();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextBoolean() {
        assertThat(workingRandomWrapper.nextBoolean(), is(true));
        failRandomWrapper.nextBoolean();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextFloat() {
        assertThat(workingRandomWrapper.nextFloat(), is(equalTo(FLOAT)));
        failRandomWrapper.nextFloat();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextDouble() {
        assertThat(workingRandomWrapper.nextDouble(), is(equalTo(DOUBLE)));
        failRandomWrapper.nextDouble();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void nextGaussian() {
        Random fakeRandom = new Random() {
            @Override
            public double nextDouble() {
                return DOUBLE;
            }
        };
        double expectedGaussian = fakeRandom.nextGaussian();
        assertThat(workingRandomWrapper.nextGaussian(), is(equalTo(expectedGaussian)));
        failRandomWrapper.nextGaussian();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void ints() {
        long streamSize = 2;

        int randomNumberOrigin = 0;
        int randomNumberBound = 1;
        IntStream is = failRandomWrapper.ints();
        failRandomWrapper.ints(streamSize);
        failRandomWrapper.ints(randomNumberOrigin, randomNumberBound);
        failRandomWrapper.ints(streamSize, randomNumberOrigin, randomNumberBound);
        is.iterator().next();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void longs() {
        long streamSize = 0;
        long randomNumberOrigin = 0;
        long randomNumberBound = 1;
        LongStream ls = failRandomWrapper.longs();
        failRandomWrapper.longs(streamSize);
        failRandomWrapper.longs(randomNumberOrigin, randomNumberBound);
        failRandomWrapper.longs(streamSize, randomNumberOrigin, randomNumberBound);
        ls.iterator().next();
    }

    @Test(expected = ExpectedAssertionError.class)
    public void doubles() {
        long streamSize = 0;
        double randomNumberOrigin = 0;
        double randomNumberBound = 1;
        failRandomWrapper.doubles().iterator().next();
        failRandomWrapper.doubles(streamSize);
        failRandomWrapper.doubles(randomNumberOrigin, randomNumberBound);
        failRandomWrapper.doubles(streamSize, randomNumberOrigin, randomNumberBound);
    }
}
