package de.tu_berlin.math.coga.rndutils;

import de.tu_berlin.math.coga.rndutils.generators.GeneralRandom;
import de.tu_berlin.math.coga.rndutils.generators.MersenneTwister;
import java.security.SecureRandom;

/**
 * This singleton class provides access to a project wide random number generator and some useful methods 
 * concerning random numbers.
 * 
 * @author Matthias Woste, Jan-Philipp Kappmeier
 *
 */

public class RandomUtils {
	
	/** Contains the RandomUtils object. */
	private static RandomUtils INSTANCE;
	
	/** Contains the Random object which is responsible for creating random numbers. */
	private GeneralRandom randomGenerator;
	
	/** This is the initial seed of the random number generator  */
	private long seed;
	
	/**
	 * Returns this singleton object
	 * @return object of RandomUtils
	 */
	public static RandomUtils getInstance(){
    if (INSTANCE == null){
	INSTANCE = new RandomUtils() ;
	}
	return INSTANCE ;
        //return INSTANCE;
	}
	
	/**
	 * Initializes an Random object with a seed generated by a SecureRandom object
	 */
	private RandomUtils() {
          SecureRandom secRandom = new SecureRandom();
          //byte[] byteSeed = secRandom.generateSeed(8);
          //secRandom.generateS
          secRandom.setSeed( SecureRandom.getSeed( 16 ) );
          //long l = Long.valueOf(new String(byteSeed)).longValue();
          seed = secRandom.nextLong();
					seed = -7566781686001723112l;
					//System.out.println( "Benutzter seed:" + seed );
          randomGenerator = new MersenneTwister(seed);
	}
	
	/**
	 * Returns the initials seed used.
	 * @return the seed.
	 */
	public long getSeed(){
		return seed;
	}
	
	/**
	 * Sets the seed to the passed value and restarts the random number generator with this seed.
	 * @param seed The seed used for initialization.
	 */
	public void setSeed(long seed){
		this.seed = seed;
		//System.out.println( "Gesetzter seed:" + seed );
		restart();
	}
	
	/**
	 * Restarts the random number generator with the seed saved in this object
	 */
	public void restart(){
		randomGenerator = new MersenneTwister(seed);
	}
	
	/**
	 * Returns the random number generator as an object of type Random.
	 * @return an object of type Random.
	 */
	public GeneralRandom getRandomGenerator(){
		return randomGenerator;
	}
	
	/**
	 * Takes a random, binary decision.
	 * @param probability A number between 0 and 1
	 * @return <code>true</code> with probability <code>probability</code> and
	 * <code>false</code> otherwise.
	 */
	public boolean binaryDecision(double probability){
		double randomNumber = getRandomGenerator().nextDouble();
		//System.out.println( "Randomnumber " + randomNumber + " in binaryDecision" );
		return (randomNumber <= probability);
	}
	
	/**
	 * Picks an integer with respect to a discrete distribution.
	 * @param probabilities A discrete distribution. The entry with index i should
	 * contain the probability of i
	 * @return A number between <code>0</code> and <code>probabilities.length-1</code>
	 */
	public int chooseRandomly(double[] probabilities){
	    int probability = 0;
	    double randomNumber = getRandomGenerator().nextDouble();
			//System.out.println( "Randomnumber " + randomNumber + " in chooseRandomly" );
	    int i=0;
	    while(probability <= randomNumber && i < probabilities.length){
            if(probabilities[i] < 0) {
                throw new IllegalArgumentException("Negative probabilities are not " +
                        "allowed! (" + probabilities[i] + " at index " + i + ")");
            }
	        probability += probabilities[i];
	        i++;
	    }
	    
	    return i-1;
	}
	
	/**
     * Picks an integer with respect to a discrete distribution given by 
     * an array of absolute frequencies.
     * @param frequencies A discrete distribution. The entry with index i should
     * contain the frequency of i
     * @return A number between <code>0</code> and <code>probabilities.length-1</code>
     */
    public int chooseRandomlyAbsolute(double[] frequencies){
        int sum = 0;
        for(int i=0; i < frequencies.length; i++){
            if(frequencies[i] < 0) {
                throw new IllegalArgumentException("Negative frequencies are not " +
                		"allowed! (" + frequencies[i] + " at index " + i + ")");
            }
            
            sum += frequencies[i];
        }
        
        double probability = 0;
        double randomNumber = getRandomGenerator().nextDouble();
				//System.out.println( "Randomnumber " + randomNumber + " in chooseRandomlyAbsolute" );
        int i=0;
        while(probability <= randomNumber*sum && i < frequencies.length){
            probability += frequencies[i];
            i++;
        }
        
        return i-1;
    }
}