package gminers.kitchensink;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.gameminers.kitchensink.KitchenSinkMod;


/**
 * RandomPool is a static wrapper around {@link Random} that allows a mod to access a shared pool of random bits, therefore decreasing the
 * chance for accidental creation of Randoms with the same seed.<br/>
 * <br/>
 * It also implements a few methods for convenience, such as <code>getBooleanWithChance</code> to generate a boolean with a 1/n chance,
 * instead of a 1/2, and <code>getRandomFrom</code> which implements a method to get a random value from an Iterable, Iterator, List,
 * Collection, or array.
 * 
 * @author Aesen Vismea
 * 
 */
public class RandomPool {
	
	private static Random	rand	= null;
	private static int		calls	= 0;
	
	private static void checkRandom() {
		if (rand == null || calls >= 100000) {
			KitchenSinkMod.LOG.info("Creating new RNG for RandomPool");
			rand = new Random(System.currentTimeMillis() ^ System.nanoTime());
			calls = 0;
		}
		calls++;
	}
	
	/**
	 * Returns the next pseudorandom, Gaussian ("normally") distributed {@code double} value with mean {@code mean} and standard
	 * deviation {@code dev} from this random number generator's sequence.
	 * <p>
	 * The general contract of {@code nextGaussian} is that one {@code double} value, chosen from (approximately) the usual normal
	 * distribution with mean {@code mean} and standard deviation {@code dev}, is pseudorandomly generated and returned.
	 * 
	 * 
	 * @return the next pseudorandom, Gaussian ("normally") distributed {@code double} value with mean {@code mean} and
	 *         standard deviation {@code dev} from this random number
	 *         generator's sequence
	 */
	public static double nextGaussian(final double mean, final double dev) {
		final double range = dev - mean;
		final double result = nextGaussian() * range;
		return (result > 0) ? result + mean : result - mean;
	}
	
	public static int getIntInRange(final int min, final int max) {
		return nextInt(max - min) + min;
	}
	
	/**
	 * Gets a random biased boolean.
	 * 
	 * @param chance
	 *            the chance value
	 * @return the value - has a 1/<code>chance</code> chance of returning <code>true</code>
	 */
	public static boolean getBooleanWithChance(final int chance) {
		if (chance < 1) {
			return false;
		}
		checkRandom();
		return nextInt(chance) == 0;
	}
	
	/**
	 * Gets a random biased boolean.
	 * 
	 * @param chance
	 *            the chance value, between 0 and 1
	 * @return the value - has a (chance*100)% chance of returning <code>true</code>
	 */
	public static boolean getBooleanWithPercentageChance(final double chance) {
		if (chance <= 0) {
			return false;
		}
		checkRandom();
		final double randy = nextDouble();
		return randy <= chance;
	}
	
	/**
	 * Selects a random value from the passed array using nextInt.
	 * 
	 * @return Selected value
	 * @param arr
	 *            The array to select from
	 */
	public static <T> T getRandomFrom(final T[] arr) {
		if (arr.length == 0) {
			return null;
		}
		checkRandom();
		return arr[nextInt(arr.length)];
	}
	
	/**
	 * Selects a random value from the passed List using nextInt.
	 * 
	 * @return Selected value
	 * @param list
	 *            The list to select from
	 */
	public static <T> T getRandomFrom(final List<T> list) {
		if (list.isEmpty()) {
			return null;
		}
		checkRandom();
		return list.get(nextInt(list.size()));
	}
	
	/**
	 * Selects a random value from the passed Collection using nextInt.
	 * 
	 * @return Selected value
	 * @param collection
	 *            The collection to select from
	 */
	public static <T> T getRandomFrom(final Collection<T> collection) {
		if (collection.isEmpty()) {
			return null;
		}
		checkRandom();
		final Iterator<T> iterator = collection.iterator();
		T result = iterator.next();
		final int index = nextInt(collection.size());
		if (index > 0) {
			for (int i = 0; i < nextInt(collection.size()); i++) {
				result = iterator.next();
			}
		}
		return result;
	}
	
	/**
	 * Selects a random value from the passed Iterable.<br/>
	 * <i>This method is somewhat inefficient, and converts
	 * the Iterable into a List before actually getting a
	 * random value - getRandomFrom for Lists, arrays, and
	 * Collections are more efficient.</i>
	 * 
	 * @return Selected value
	 * @param iter
	 *            The Iterable to select from
	 */
	public static <T> T getRandomFrom(final Iterable<T> iter) {
		return getRandomFrom(iter.iterator());
	}
	
	/**
	 * Selects a random value from the passed Iterator,
	 * with the minimum possible value being the iterator's
	 * current position plus one.
	 * <i>This method is somewhat inefficient, and converts
	 * the Iterator into a List before actually getting a
	 * random value - getRandomFrom for Lists, arrays, and
	 * Collections are more efficient.</i>
	 * 
	 * @param iterator
	 * @return Selected value
	 */
	public static <T> T getRandomFrom(final Iterator<T> iterator) {
		if (!iterator.hasNext()) {
			return null;
		}
		checkRandom();
		final List<T> list = new ArrayList<T>();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return getRandomFrom(list);
	}
	
	/**
	 * Selects a random value from the passed Enum.
	 * 
	 * @param clazz
	 *            The enum to select from.
	 * @return Selected value
	 */
	public static <E extends Enum<E>> Enum<E> randomFrom(final Class<Enum<E>> clazz) {
		if (clazz.getDeclaredFields().length == 0) {
			return null;
		}
		checkRandom();
		final Enum<E>[] constants = clazz.getEnumConstants();
		return getRandomFrom(constants);
	}
	
	/**
	 * Generates random bytes and places them into a user-supplied
	 * byte array. The number of random bytes produced is equal to
	 * the length of the byte array.
	 * 
	 * <p>
	 * The method {@code nextBytes} is implemented by class {@code Random} as if by:
	 * 
	 * <pre>
	 * {@code
	 * public void nextBytes(byte[] bytes) {
	 *   for (int i = 0; i < bytes.length; )
	 *     for (int rnd = nextInt(), n = Math.min(bytes.length - i, 4);
	 *          n-- > 0; rnd >>= 8)
	 *       bytes[i++] = (byte)rnd;
	 * }}
	 * </pre>
	 * 
	 * @param bytes
	 *            the byte array to fill with random bytes
	 * @throws NullPointerException
	 *             if the byte array is null
	 * @since 1.1
	 */
	public static void nextBytes(final byte[] bytes) {
		checkRandom();
		rand.nextBytes(bytes);
	}
	
	/**
	 * Returns the next pseudorandom, uniformly distributed {@code int} value from this random number generator's sequence. The general
	 * contract of {@code nextInt} is that one {@code int} value is
	 * pseudorandomly generated and returned. All 2<font size="-1"><sup>32
	 * </sup></font> possible {@code int} values are produced with
	 * (approximately) equal probability.
	 * 
	 * <p>
	 * The method {@code nextInt} is implemented by class {@code Random} as if by:
	 * 
	 * <pre>
	 * {@code
	 * public int nextInt() {
	 *   return next(32);
	 * }}
	 * </pre>
	 * 
	 * @return the next pseudorandom, uniformly distributed {@code int} value from this random number generator's sequence
	 */
	public static int nextInt() {
		checkRandom();
		return rand.nextInt();
	}
	
	/**
	 * Returns a pseudorandom, uniformly distributed {@code int} value
	 * between 0 (inclusive) and the specified value (exclusive), drawn from
	 * this random number generator's sequence. The general contract of {@code nextInt} is that one {@code int} value in the specified range
	 * is pseudorandomly generated and returned. All {@code n} possible {@code int} values are produced with (approximately) equal
	 * probability. The method {@code nextInt(int n)} is implemented by
	 * class {@code Random} as if by:
	 * 
	 * <pre>
	 * {@code
	 * public int nextInt(int n) {
	 *   if (n <= 0)
	 *     throw new IllegalArgumentException("n must be positive");
	 * 
	 *   if ((n & -n) == n)  // i.e., n is a power of 2
	 *     return (int)((n * (long)next(31)) >> 31);
	 * 
	 *   int bits, val;
	 *   do {
	 *       bits = next(31);
	 *       val = bits % n;
	 *   } while (bits - val + (n-1) < 0);
	 *   return val;
	 * }}
	 * </pre>
	 * 
	 * <p>
	 * The hedge "approximately" is used in the foregoing description only because the next method is only approximately an unbiased source
	 * of independently chosen bits. If it were a perfect source of randomly chosen bits, then the algorithm shown would choose {@code int}
	 * values from the stated range with perfect uniformity.
	 * <p>
	 * The algorithm is slightly tricky. It rejects values that would result in an uneven distribution (due to the fact that 2^31 is not
	 * divisible by n). The probability of a value being rejected depends on n. The worst case is n=2^30+1, for which the probability of a
	 * reject is 1/2, and the expected number of iterations before the loop terminates is 2.
	 * <p>
	 * The algorithm treats the case where n is a power of two specially: it returns the correct number of high-order bits from the
	 * underlying pseudo-random number generator. In the absence of special treatment, the correct number of <i>low-order</i> bits would be
	 * returned. Linear congruential pseudo-random number generators such as the one implemented by this class are known to have short
	 * periods in the sequence of values of their low-order bits. Thus, this special case greatly increases the length of the sequence of
	 * values returned by successive calls to this method if n is a small power of two.
	 * 
	 * @param n
	 *            the bound on the random number to be returned. Must be
	 *            positive.
	 * @return the next pseudorandom, uniformly distributed {@code int} value between {@code 0} (inclusive) and {@code n} (exclusive)
	 *         from this random number generator's sequence
	 * @exception IllegalArgumentException
	 *                if n is not positive
	 * @since 1.2
	 */
	public static int nextInt(final int n) {
		checkRandom();
		return rand.nextInt(n);
	}
	
	/**
	 * Returns the next pseudorandom, uniformly distributed {@code long} value from this random number generator's sequence. The general
	 * contract of {@code nextLong} is that one {@code long} value is
	 * pseudorandomly generated and returned.
	 * 
	 * <p>
	 * The method {@code nextLong} is implemented by class {@code Random} as if by:
	 * 
	 * <pre>
	 * {@code
	 * public long nextLong() {
	 *   return ((long)next(32) << 32) + next(32);
	 * }}
	 * </pre>
	 * 
	 * Because class {@code Random} uses a seed with only 48 bits, this algorithm will not return all possible {@code long} values.
	 * 
	 * @return the next pseudorandom, uniformly distributed {@code long} value from this random number generator's sequence
	 */
	public static long nextLong() {
		checkRandom();
		return rand.nextLong();
	}
	
	/**
	 * Returns the next pseudorandom, uniformly distributed {@code boolean} value from this random number generator's
	 * sequence. The general contract of {@code nextBoolean} is that one {@code boolean} value is pseudorandomly generated and returned. The
	 * values {@code true} and {@code false} are produced with
	 * (approximately) equal probability.
	 * 
	 * <p>
	 * The method {@code nextBoolean} is implemented by class {@code Random} as if by:
	 * 
	 * <pre>
	 * {@code
	 * public boolean nextBoolean() {
	 *   return next(1) != 0;
	 * }}
	 * </pre>
	 * 
	 * @return the next pseudorandom, uniformly distributed {@code boolean} value from this random number generator's
	 *         sequence
	 * @since 1.2
	 */
	public static boolean nextBoolean() {
		checkRandom();
		return rand.nextBoolean();
	}
	
	/**
	 * Returns the next pseudorandom, uniformly distributed {@code float} value between {@code 0.0} and {@code 1.0} from this random
	 * number generator's sequence.
	 * 
	 * <p>
	 * The general contract of {@code nextFloat} is that one {@code float} value, chosen (approximately) uniformly from the range
	 * {@code 0.0f} (inclusive) to {@code 1.0f} (exclusive), is pseudorandomly generated and returned. All 2<font
	 * size="-1"><sup>24</sup></font> possible {@code float} values of the form <i>m&nbsp;x&nbsp</i>2<font size="-1"><sup>-24</sup></font>,
	 * where <i>m</i> is a positive integer less than 2<font size="-1"><sup>24</sup> </font>, are produced with (approximately) equal
	 * probability.
	 * 
	 * <p>
	 * The method {@code nextFloat} is implemented by class {@code Random} as if by:
	 * 
	 * <pre>
	 * {@code
	 * public float nextFloat() {
	 *   return next(24) / ((float)(1 << 24));
	 * }}
	 * </pre>
	 * 
	 * <p>
	 * The hedge "approximately" is used in the foregoing description only because the next method is only approximately an unbiased source
	 * of independently chosen bits. If it were a perfect source of randomly chosen bits, then the algorithm shown would choose
	 * {@code float} values from the stated range with perfect uniformity.
	 * <p>
	 * [In early versions of Java, the result was incorrectly calculated as:
	 * 
	 * <pre>
	 * {@code
	 *   return next(30) / ((float)(1 << 30));}
	 * </pre>
	 * 
	 * This might seem to be equivalent, if not better, but in fact it introduced a slight nonuniformity because of the bias in the rounding
	 * of floating-point numbers: it was slightly more likely that the low-order bit of the significand would be 0 than that it would be 1.]
	 * 
	 * @return the next pseudorandom, uniformly distributed {@code float} value between {@code 0.0} and {@code 1.0} from this
	 *         random number generator's sequence
	 */
	public static float nextFloat() {
		checkRandom();
		return rand.nextFloat();
	}
	
	/**
	 * Returns the next pseudorandom, uniformly distributed {@code double} value between {@code 0.0} and {@code 1.0} from this random number
	 * generator's sequence.
	 * 
	 * <p>
	 * The general contract of {@code nextDouble} is that one {@code double} value, chosen (approximately) uniformly from the range
	 * {@code 0.0d} (inclusive) to {@code 1.0d} (exclusive), is pseudorandomly generated and returned.
	 * 
	 * <p>
	 * The method {@code nextDouble} is implemented by class {@code Random} as if by:
	 * 
	 * <pre>
	 * {@code
	 * public double nextDouble() {
	 *   return (((long)next(26) << 27) + next(27))
	 *     / (double)(1L << 53);
	 * }}
	 * </pre>
	 * 
	 * <p>
	 * The hedge "approximately" is used in the foregoing description only because the {@code next} method is only approximately an unbiased
	 * source of independently chosen bits. If it were a perfect source of randomly chosen bits, then the algorithm shown would choose
	 * {@code double} values from the stated range with perfect uniformity.
	 * <p>
	 * [In early versions of Java, the result was incorrectly calculated as:
	 * 
	 * <pre>
	 * {@code
	 *   return (((long)next(27) << 27) + next(27))
	 *     / (double)(1L << 54);}
	 * </pre>
	 * 
	 * This might seem to be equivalent, if not better, but in fact it introduced a large nonuniformity because of the bias in the rounding
	 * of floating-point numbers: it was three times as likely that the low-order bit of the significand would be 0 than that it would be 1!
	 * This nonuniformity probably doesn't matter much in practice, but we strive for perfection.]
	 * 
	 * @return the next pseudorandom, uniformly distributed {@code double} value between {@code 0.0} and {@code 1.0} from this
	 *         random number generator's sequence
	 * @see Math#random
	 */
	public static double nextDouble() {
		checkRandom();
		return rand.nextDouble();
	}
	
	/**
	 * Returns the next pseudorandom, Gaussian ("normally") distributed {@code double} value with mean {@code 0.0} and standard
	 * deviation {@code 1.0} from this random number generator's sequence.
	 * <p>
	 * The general contract of {@code nextGaussian} is that one {@code double} value, chosen from (approximately) the usual normal
	 * distribution with mean {@code 0.0} and standard deviation {@code 1.0}, is pseudorandomly generated and returned.
	 * 
	 * <p>
	 * The method {@code nextGaussian} is implemented by class {@code Random} as if by a threadsafe version of the following:
	 * 
	 * <pre>
	 * {@code
	 * private double nextNextGaussian;
	 * private boolean haveNextNextGaussian = false;
	 * 
	 * public double nextGaussian() {
	 *   if (haveNextNextGaussian) {
	 *     haveNextNextGaussian = false;
	 *     return nextNextGaussian;
	 *   } else {
	 *     double v1, v2, s;
	 *     do {
	 *       v1 = 2 * nextDouble() - 1;   // between -1.0 and 1.0
	 *       v2 = 2 * nextDouble() - 1;   // between -1.0 and 1.0
	 *       s = v1 * v1 + v2 * v2;
	 *     } while (s >= 1 || s == 0);
	 *     double multiplier = StrictMath.sqrt(-2 * StrictMath.log(s)/s);
	 *     nextNextGaussian = v2 * multiplier;
	 *     haveNextNextGaussian = true;
	 *     return v1 * multiplier;
	 *   }
	 * }}
	 * </pre>
	 * 
	 * This uses the <i>polar method</i> of G. E. P. Box, M. E. Muller, and G. Marsaglia, as described by Donald E. Knuth in <i>The Art of
	 * Computer Programming</i>, Volume 3: <i>Seminumerical Algorithms</i>, section 3.4.1, subsection C, algorithm P. Note that it generates
	 * two independent values at the cost of only one call to {@code StrictMath.log} and one call to {@code StrictMath.sqrt}.
	 * 
	 * @return the next pseudorandom, Gaussian ("normally") distributed {@code double} value with mean {@code 0.0} and
	 *         standard deviation {@code 1.0} from this random number
	 *         generator's sequence
	 */
	public static double nextGaussian() {
		checkRandom();
		return rand.nextGaussian();
	}
	
	/**
	 * Create a 'facade' Random that is actually a special class that redirects all it's calls into the RandomPool,
	 * and ignores any methods that cannot be invoked in RandomPool.
	 * 
	 * @return a 'facade' Random
	 */
	public static Random createRandomFacade() {
		return new RandomFacade();
	}
	
	// FORM off
	@SuppressWarnings("sync-override")
	final static class RandomFacade extends Random {
		private static final long	serialVersionUID	= -6778868066787118864L;
		@Override public boolean nextBoolean() { return RandomPool.nextBoolean(); }
		@Override public void nextBytes(final byte[] bytes) { RandomPool.nextBytes(bytes); }
		@Override public double nextDouble() { return RandomPool.nextDouble(); }
		@Override public float nextFloat() { return RandomPool.nextFloat(); }
		@Override public double nextGaussian() { return RandomPool.nextGaussian(); }
		@Override public int nextInt() { return RandomPool.nextInt(); }
		@Override public int nextInt(final int n) { return RandomPool.nextInt(n); }
		@Override public long nextLong() { return RandomPool.nextLong(); }
		@Override public void setSeed(final long seed) { KitchenSinkMod.LOG.warn("Something tried to set the seed for a RandomPool facade."); }
	}
	// FORM on
}
