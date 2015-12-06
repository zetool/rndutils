package org.zetool.rndutils.generators;

import java.util.Random;
import org.zetool.rndutils.generators.GeneralRandom;

/**
 * Implements the {@link Random} interface and uses a {@link GeneralRandom} generator to generate the random numbers.
 * 
 * @author Jan-Philipp Kappmeier
 */
public class GeneralRandomWrapper extends Random {
    private final GeneralRandom r;
    private boolean initialized = false;
    private long initialSeed;

    public GeneralRandomWrapper(GeneralRandom r) {
        this.r = r;
        initialized = true;
        r.setSeed(initialSeed);
    }

    @Override
    public synchronized void setSeed(long seed) {
        if(!initialized) {
            initialized = true;
            initialSeed = seed;
            return;
        }
        r.setSeed(seed);
    }

    @Override
    public void nextBytes(byte[] bytes) {
        r.nextBytes(bytes);
    }

    @Override
    public int nextInt() {
        return r.nextInt();
    }

    @Override
    public int nextInt(int bound) {
        return r.nextInt(bound);
    }

    @Override
    public long nextLong() {
        return r.nextLong();
    }

    @Override
    public boolean nextBoolean() {
        return r.nextBoolean();
    }

    @Override
    public float nextFloat() {
        return r.nextFloat();
    }

    @Override
    public double nextDouble() {
        return r.nextDouble();
    }

}
