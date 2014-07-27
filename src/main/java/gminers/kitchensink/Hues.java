package gminers.kitchensink;


public class Hues {
	public static final int	RED			= 0;
	public static final int	ORANGE		= 30;
	public static final int	YELLOW		= 60;
	public static final int	CHARTREUSE	= 90;
	public static final int	GREEN		= 120;
	public static final int	TEAL		= 150;
	public static final int	CYAN		= 180;
	public static final int	CAPRI		= 210;
	public static final int	BLUE		= 240;
	public static final int	PURPLE		= 270;
	public static final int	MAGENTA		= 300;
	
	/**
	 * Converts a hue value to RGB.
	 * 
	 * @param hue
	 *            The hue value
	 * @return An RGB representation of the passed hue value
	 */
	public static int hueToRGB(final int hue) {
		final double step = (hue / 60D);
		final int max = 255;
		final int val = (int) ((1D - Math.abs(step % 2D - 1D)) * 255D);
		
		final int cat = (int) Math.floor(step);
		switch (cat) {
			case 0:
				return combine(max, val, 0);
			case 1:
				return combine(val, max, 0);
			case 2:
				return combine(0, max, val);
			case 3:
				return combine(0, val, max);
			case 4:
				return combine(val, 0, max);
			default:
				return combine(max, 0, val);
		}
	}
	
	private static int combine(final int r, final int g, final int b) {
		return r << 16 | g << 8 | b;
	}
	
	private Hues() {
	}
}
