package gminers.kitchensink;


import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.asin;
import static java.lang.Math.cos;
import static java.lang.Math.random;
import static java.lang.Math.round;
import static java.lang.Math.sin;
import static java.lang.Math.tan;


/**
 * A type of wave, used for animation or audio.<br>
 * The waves supported by this enum are sine, tangent, triangle, square, sawtooth, secant, and their co-, inverse-, and absolute- variants.
 * (or any combination thereof)<br/>
 * The utility psuedo-waves floor, ceiling, and random are also supplied.<br>
 * <br>
 * Co- and inverse- waves are <b>not</b> the same thing - they appear the same on the graphs, but when used to animate, for example in a
 * ping-pong animation, it makes the ping-ponging object come from the other side instead of just being a different pattern than it's
 * regular variant.
 * 
 * 
 * @author Aesen Vismea
 * 
 */
// it may or may not have been 5 am when i wrote the majority of these comments
// but i find some of them funny so i'm keeping them
// who said comments were supposed to be professional? i can have fun too
public enum WaveType {
	// --- Sine ---//
	/**
	 * <img src="http://dl.gameminers.com/graphs/sine.png"/><br>
	 */
	SINE,
	/**
	 * <img src="http://dl.gameminers.com/graphs/absolute_sine.png"/>
	 */
	ABSOLUTE_SINE, // this is the wave used by the splash text in the main menu
	/**
	 * <img src="http://dl.gameminers.com/graphs/cosine.png"/>
	 */
	COSINE,
	/**
	 * <img src="http://dl.gameminers.com/graphs/absolute_cosine.png"/>
	 */
	ABSOLUTE_COSINE,
	
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_sine.png"/>
	 */
	INVERSE_SINE,
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_absolute_sine.png"/>
	 */
	INVERSE_ABSOLUTE_SINE,
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_cosine.png"/>
	 */
	INVERSE_COSINE,
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_absolute_cosine.png"/>
	 */
	INVERSE_ABSOLUTE_COSINE,
	
	// --- Tangent ---//
	/**
	 * <img src="http://dl.gameminers.com/graphs/tangent.png"/>
	 */
	TANGENT, // this wave looks similar to the Windows 8 progress animations when used on a progress bar
	/**
	 * <img src="http://dl.gameminers.com/graphs/absolute_tangent.png"/>
	 */
	ABSOLUTE_TANGENT,
	/**
	 * <img src="http://dl.gameminers.com/graphs/cotangent.png"/>
	 */
	COTANGENT,
	/**
	 * <img src="http://dl.gameminers.com/graphs/absolute_cotangent.png"/>
	 */
	ABSOLUTE_COTANGENT,
	
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_tangent.png"/>
	 */
	INVERSE_TANGENT,
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_absolute_tangent.png"/>
	 */
	INVERSE_ABSOLUTE_TANGENT,
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_cotangent.png"/>
	 */
	INVERSE_COTANGENT,
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_absolute_cotangent.png"/>
	 */
	INVERSE_ABSOLUTE_COTANGENT,
	
	// --- Triangle ---//
	/**
	 * <img src="http://dl.gameminers.com/graphs/triangle.png"/>
	 */
	TRIANGLE,
	/**
	 * <img src="http://dl.gameminers.com/graphs/absolute_triangle.png"/>
	 */
	ABSOLUTE_TRIANGLE,
	/**
	 * <img src="http://dl.gameminers.com/graphs/cotriangle.png"/>
	 */
	COTRIANGLE,
	/**
	 * <img src="http://dl.gameminers.com/graphs/absolute_cotriangle.png"/>
	 */
	ABSOLUTE_COTRIANGLE,
	
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_triangle.png"/>
	 */
	INVERSE_TRIANGLE,
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_absolute_triangle.png"/>
	 */
	INVERSE_ABSOLUTE_TRIANGLE,
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_cotriangle.png"/>
	 */
	INVERSE_COTRIANGLE,
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_absolute_cotriangle.png"/>
	 */
	INVERSE_ABSOLUTE_COTRIANGLE,
	
	// --- Square ---//
	/**
	 * <b>Absolute variant:</b> CEILING<br>
	 * <img src="http://dl.gameminers.com/graphs/square.png"/>
	 */
	SQUARE,
	/**
	 * <b>Absolute variant:</b> CEILING<br>
	 * <img src="http://dl.gameminers.com/graphs/cosquare.png"/>
	 */
	COSQUARE,
	
	/**
	 * <b>Absolute variant:</b> FLOOR<br>
	 * <img src="http://dl.gameminers.com/graphs/inverse_square.png"/>
	 */
	INVERSE_SQUARE,
	/**
	 * <b>Absolute variant:</b> FLOOR<br>
	 * <img src="http://dl.gameminers.com/graphs/inverse_cosquare.png"/>
	 */
	INVERSE_COSQUARE,
	
	// --- Sawtooth ---//
	/**
	 * <b>Absolute variant:</b> TRIANGLE<br>
	 * <img src="http://dl.gameminers.com/graphs/sawtooth.png"/>
	 */
	SAWTOOTH,
	/**
	 * <b>Absolute variant:</b> COTRIANGLE<br>
	 * <img src="http://dl.gameminers.com/graphs/cosawtooth.png"/>
	 */
	COSAWTOOTH,
	
	/**
	 * <b>Absolute variant:</b> INVERSE_TRIANGLE<br>
	 * <img src="http://dl.gameminers.com/graphs/inverse_sawtooth.png"/>
	 */
	INVERSE_SAWTOOTH,
	/**
	 * <b>Absolute variant:</b> INVERSE_COTRIANGLE<br>
	 * <img src="http://dl.gameminers.com/graphs/inverse_cosawtooth.png"/>
	 */
	INVERSE_COSAWTOOTH,
	
	// --- Secant ---//
	/**
	 * <img src="http://dl.gameminers.com/graphs/secant.png"/>
	 */
	SECANT, // this is Aesen's personal favorite for progress bar animations
	/**
	 * <img src="http://dl.gameminers.com/graphs/cosecant.png"/>
	 */
	COSECANT,
	/**
	 * <img src="http://dl.gameminers.com/graphs/absolute_secant.png"/>
	 */
	ABSOLUTE_SECANT,
	/**
	 * <img src="http://dl.gameminers.com/graphs/absolute_cosecant.png"/>
	 */
	ABSOLUTE_COSECANT,
	
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_secant.png"/>
	 */
	INVERSE_SECANT,
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_cosecant.png"/>
	 */
	INVERSE_COSECANT,
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_absolute_secant.png"/>
	 */
	INVERSE_ABSOLUTE_SECANT,
	/**
	 * <img src="http://dl.gameminers.com/graphs/inverse_absolute_cosecant.png"/>
	 */
	INVERSE_ABSOLUTE_COSECANT,
	
	// --- Psuedo-Waves ---//
	/**
	 * <b>This is a psuedo-wave.</b> FLOOR is always 0, no matter what X is passed.<br>
	 * <img src="http://dl.gameminers.com/graphs/floor.png"/>
	 */
	FLOOR,
	/**
	 * <b>This is a psuedo-wave.</b> CEILING is always 1, no matter what X is passed.<br>
	 * <img src="http://dl.gameminers.com/graphs/ceiling.png"/>
	 */
	CEILING,
	/**
	 * <b>This is a psuedo-wave.</b> RANDOM is a randomly generated value from 0 to 1, and pays no heed to the passed X.<br>
	 * <img src="http://dl.gameminers.com/graphs/random.png"/>
	 */
	RANDOM;
	
	private static final double	TAU	= PI * 2.0;
	private final boolean		inverted;
	private final String		inverseOf;
	
	private WaveType() {
		if (name().startsWith("INVERSE_")) {
			inverted = true;
			// these string hacks are just
			// they are so amazing for reducing the infinite amount of case statements
			// but they also kind of ruin the strong-typing of enums
			// ...
			// who cares it makes my life easier
			inverseOf = name().substring(8);
		} else {
			inverted = false;
			inverseOf = null;
		}
	}
	
	
	private double clam(final double y) {
		return (y + 1.0) / 2.0;
	}
	
	/**
	 * Returns the Y value in a graph from 0 to 1 given the passed X value, calculating using this graph type.
	 * 
	 * @param x
	 *            The X coordinate in the imaginary graph
	 * @return The Y coordinate, from 0 to 1, in the imaginary graph
	 */
	public double calculate(final double x) {
		final double y = _calculate(x);
		return (y < 0 ? 0 : (y > 1 ? 1 : y)); // truncate it if it's out of bounds to maintain the contract - a 'just in case' truncation
	}
	
	/**
	 * Returns the Y value in a graph from -1 to 1 given the passed X value, calculating using this graph type.
	 * 
	 * @param x
	 *            The X coordinate in the imaginary graph
	 * @return The Y coordinate, from -1 to 1, in the imaginary graph
	 */
	public double calculateClassical(final double x) {
		final double y = (_calculate(x) - 0.5) * 2; // reverse the clamping math, as the wave code is designed around the 0-1 contract
		// it would be more efficient to just not call the clamp math in the calculate method, but that would require putting math on the
		// absolute waves instead of just the non-absolute waves, and since this class is mostly intended for animation, the 0-1 method is
		// more useful anyway.
		return (y < -1 ? -1 : (y > 1 ? 1 : y));
	}
	
	/**
	 * Returns the cowave of this wave.<br>
	 * <code>wave</code>.co().co() is guaranteed to return <code>wave</code>.
	 * 
	 * @return The 'co' variant of this wave
	 */
	public WaveType co() {
		switch (this) {
			case CEILING:
				return CEILING; // coceiling would disappoint ceiling cat
			case FLOOR:
				return FLOOR; // cofloor would be the same as floor
			case RANDOM:
				return RANDOM; // there is no point to a corandom, so just return random, as nextDouble is uniformly distributed
			default: {
				// this wonderful hack brought to you by strings
				if (name().contains("CO")) {
					return valueOf(name().replace("CO", ""));
				} else {
					final StringBuilder builder = new StringBuilder(name());
					builder.insert(builder.lastIndexOf("_") + 1, "CO");
					return valueOf(builder.toString());
				}
			}
		}
	}
	
	/**
	 * Returns the inverse of this wave.<br>
	 * <code>wave</code>.inverse().inverse() is guaranteed to return <code>wave</code>.
	 * 
	 * @return The inverted variant of this wave
	 */
	public WaveType inverse() {
		switch (this) {
			case CEILING:
				return FLOOR; // floor and ceiling are defined explicitly since they don't follow the "INVERSE_" convention
			case FLOOR:
				return CEILING;
			case RANDOM:
				return RANDOM; // inverse random would be indiscernible from random, as nextDouble is uniformly distributed
			default: {
				// similar hack to the one in co()
				if (name().contains("INVERSE_")) {
					return valueOf(name().replace("INVERSE_", ""));
				} else {
					final StringBuilder builder = new StringBuilder(name());
					builder.insert(0, "INVERSE_");
					return valueOf(builder.toString());
				}
			}
		}
	}
	
	private double cot(final double x) {
		return tan(x) * -1;
	}
	
	private double sec(final double x) {
		return 1 / sin(x);
	}
	
	private double csc(final double x) {
		return sec(x) * -1;
	}
	
	private double _calculate(final double x) {
		if (inverted) {
			// I'VE HAD ENOUGH OF THESE GODDAMN STRINGS ON THIS GODDAMN ENUM
			return 1 - valueOf(inverseOf).calculate(x);
		}
		// protip: statically import Math.* for infinite happiness
		switch (this) {
			case ABSOLUTE_COSINE:
				return abs(cos(x));
			case ABSOLUTE_COTANGENT:
				return (abs(cot(x))) / 10;
			case ABSOLUTE_COTRIANGLE:
				return abs((asin(sin(x)) / 1.5) * -1);
			case ABSOLUTE_SINE:
				return abs(sin(x));
			case ABSOLUTE_TANGENT:
				return (abs(tan(x))) / 10;
			case ABSOLUTE_TRIANGLE:
				return abs(asin(sin(x)) / 1.5);
			case COSINE:
				return clam(cos(x));
			case COSQUARE:
				return round(clam(cos(x)));
			case COTANGENT:
				return (cot(x) + 10) / 20;
			case COTRIANGLE:
				return ((asin(sin(x)) * -1) + 1.5) / 3;
			case SAWTOOTH:
				return (x % PI) / PI;
			case CEILING:
				return 1;
			case RANDOM:
				return random();
			case SINE:
				return clam(sin(x));
			case SQUARE:
				return round(clam(sin(x)));
			case TANGENT:
				return (tan(x) + 10) / 20;
			case TRIANGLE:
				return (asin(sin(x)) + 1.5) / 3;
			case FLOOR:
				return 0;
			case COSAWTOOTH:
				return (PI - (x % PI)) / PI;
			case ABSOLUTE_COSECANT:
				return abs(csc(x) / 10);
			case ABSOLUTE_SECANT:
				return abs(sec(x) / 10);
			case COSECANT:
				return (csc(x) + 10) / 20;
			case SECANT:
				return (sec(x) + 10) / 20;
			default:
				System.err.println("Wave type " + name() + " is not implemented! This is a bug!");
		}
		System.err.println("Switch statement didn't explicitly return value while calculating wave " + name()
				+ " - this is a bug!");
		return x; // we should never reach here unless something is wrong
	}
	
	// graph fun. requires JFreeChart. used to generate the images used in the javadocs.
	
	// FORM off
	/*
	public static void main(final String[] args) {
		for (final WaveType wt : WaveType.values()) {
			final org.jfree.data.xy.XYSeries series = new org.jfree.data.xy.XYSeries("Value");
			series.add(-0.002, 0);
			series.add(-0.001, 1.001);
			for (double x = 0; x < PI * 4.5; x += 0.01) {
				series.add(x, wt.calculate(x)+0.001);
			}
			final org.jfree.data.xy.XYSeriesCollection dataset = new org.jfree.data.xy.XYSeriesCollection(series);
			final org.jfree.chart.JFreeChart chart = org.jfree.chart.ChartFactory.createXYLineChart("", "", "", dataset);
			final java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(320, 240, java.awt.image.BufferedImage.TYPE_4BYTE_ABGR);
			final java.awt.Graphics2D g = img.createGraphics();
			chart.draw(g, new java.awt.Rectangle(-58, -22, 320 + 86, 240 + 72));
			g.dispose();
			try {
				javax.imageio.ImageIO.write(img, "PNG", new java.io.File("charts/"+wt.name().toLowerCase() + ".png"));
				System.out.println("Created graph for " + wt);
			} catch (final java.io.IOException e) {
				e.printStackTrace();
				System.err.println("Failed to create graph for " + wt);
			}
		}
	}
	*/
	// FORM on
}
