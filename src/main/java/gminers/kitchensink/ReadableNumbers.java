package gminers.kitchensink;


import java.text.NumberFormat;


public class ReadableNumbers {
	
	/**
	 * Converts bytes to a human-readable amount of kilobytes, megabytes, etc.
	 * 
	 * @param bytes
	 *            The amount of bytes
	 * @param si
	 *            Whether or not to use si-units, aka 1000 bytes per kilobyte (KiB vs KB)
	 * @return A string representing the byte count in a human-readable form
	 */
	public static String humanReadableByteCount(final long bytes, final boolean si) {
		final int unit = si ? 1000 : 1024;
		if (bytes < unit) {
			return bytes + " B";
		}
		final int exp = (int) (Math.log(bytes) / Math.log(unit));
		final String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
	
	private static final NumberFormat	nFormat	= NumberFormat.getInstance();
	
	static {
		nFormat.setMaximumFractionDigits(1);
		nFormat.setGroupingUsed(true);
	}
	
	/**
	 * Converts milliseconds to a human-readable amount of seconds, minutes, hours, or days.<br/>
	 * The special value "7 eternities" is returned if the millis amount is over 1 year.
	 * 
	 * @param millis
	 *            The amount of milliseconds
	 * @return A human-readable representation of millis
	 */
	public static String humanReadableMillis(final long millis) {
		if (millis < 60000) {
			final double unit = millis / 1000.0;
			return nFormat.format(unit) + (unit == 1 ? " second" : " seconds");
		} else if (millis < 3600000) {
			final double unit = millis / 60000.0;
			return nFormat.format(unit) + (unit == 1 ? " minute" : " minutes");
		} else if (millis < 86400000) {
			final double unit = millis / (60000.0 * 60);
			return nFormat.format(unit) + (unit == 1 ? " hour" : " hours");
		} else {
			final double unit = millis / (60000.0 * 60 * 24);
			if (unit > 365) {
				return "7 eternities";
			}
			return nFormat.format(unit) + (unit == 1 ? " day" : " days");
		}
	}
	
	public static String siCount(final int num) {
		final int unit = 1000;
		if (num < unit) {
			return num + "";
		}
		final int exp = (int) (Math.log(num) / Math.log(unit));
		final char pre = "kMGTPEZY".charAt(exp - 1);
		return String.format("%.1f %sB", num / Math.pow(unit, exp), pre);
	}
	
}
