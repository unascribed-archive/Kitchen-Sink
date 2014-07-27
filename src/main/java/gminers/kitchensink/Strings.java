package gminers.kitchensink;


import java.math.BigInteger;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import net.minecraft.util.EnumChatFormatting;


public class Strings {
	
	/**
	 * Takes either a set of words or an enum constant, and formats it as one word in
	 * TitleCase, where the first letter of each "source" word is capitalized.
	 * 
	 * @author Falkreon
	 * @param in
	 *            The name of an enum constant, or a set of words
	 * @return One word, capitalized for legibility and pretty printing.
	 */
	public static String formatTitleCase(final String in) {
		String[] pieces = new String[] {
			in
		};
		if (in.contains(" ")) {
			pieces = in.toLowerCase().split(" ");
		} else if (in.contains("_")) {
			pieces = in.toLowerCase().split("_");
		}
		
		final StringBuilder result = new StringBuilder();
		for (final String s : pieces) {
			if (s == null) {
				continue;
			}
			final String t = s.trim().toLowerCase(Locale.ENGLISH);
			if (t.isEmpty()) {
				continue;
			}
			result.append(Character.toUpperCase(t.charAt(0)));
			if (t.length() > 1) {
				result.append(t.substring(1));
			}
			result.append(" ");
		}
		result.deleteCharAt(result.length() - 1);
		return result.toString();
	}
	
	/**
	 * Prepare a list of Strings for display in chat.
	 * 
	 * @author Falkreon
	 * @param text
	 *            The color of items in the list
	 * @param token
	 *            The color of commas and the word "and"
	 * @param list
	 *            The list of items to format
	 * @return e.g. { "Foo", "Bar", "Baz" } -> "Foo, Bar, and Baz"
	 */
	public static String formatList(final EnumChatFormatting text, final EnumChatFormatting token, final String... list) {
		final StringBuilder result = new StringBuilder();
		for (int i = 0; i < list.length; i++) {
			if (text != null) {
				result.append(text);
			}
			result.append(list[i]);
			if (i < list.length - 1) {
				if (i == list.length - 2) {
					if (token != null) {
						result.append(token);
					}
					result.append(", and ");
				} else {
					if (token != null) {
						result.append(token);
					}
					result.append(", ");
				}
			}
		}
		return result.toString();
	}
	
	/**
	 * Prepare a list of Strings for display in chat.
	 * 
	 * @author Falkreon
	 * @param text
	 *            The color of items in the list
	 * @param token
	 *            The color of commas and the word "and"
	 * @param items
	 *            The list of items to format
	 * @return e.g. { "Foo", "Bar", "Baz" } -> "Foo, Bar, and Baz"
	 */
	public static String formatList(final EnumChatFormatting text, final EnumChatFormatting token,
			final Collection<String> items) {
		return formatList(text, token, items.toArray(new String[items.size()]));
	}
	
	/**
	 * { "Foo", "Bar", "Baz" } -> "Foo, Bar, and Baz"
	 * 
	 * @author Falkreon
	 */
	public static String formatList(final String... list) {
		return formatList(null, null, list);
	}
	
	public static int parseInt(final String src, final int defaultValue) {
		try {
			return Integer.parseInt(src);
		} catch (final NumberFormatException ex) {
			return defaultValue;
		}
	}
	
	public static long parseLong(final String src, final long defaultValue) {
		try {
			return Long.parseLong(src);
		} catch (final NumberFormatException ex) {
			return defaultValue;
		}
	}
	
	public static short parseShort(final String src, final short defaultValue) {
		try {
			return Short.parseShort(src);
		} catch (final NumberFormatException ex) {
			return defaultValue;
		}
	}
	
	public static float parseFloat(final String src, final float defaultValue) {
		try {
			return Float.parseFloat(src);
		} catch (final NumberFormatException ex) {
			return defaultValue;
		}
	}
	
	public static double parseDouble(final String src, final double defaultValue) {
		try {
			return Double.parseDouble(src);
		} catch (final NumberFormatException ex) {
			return defaultValue;
		}
	}
	
	public static BigInteger parseBigInteger(final String src, final BigInteger defaultValue) {
		try {
			return new BigInteger(src);
		} catch (final NumberFormatException ex) {
			return defaultValue;
		}
	}
	
	/**
	 * Join an array of Strings into one String, with a space in between entries, skipping over null entries.
	 * 
	 * @author Falkreon
	 * @param strings
	 *            The Strings to join together
	 * @return The combined String
	 */
	public static String join(final String... strings) {
		return join(strings, " ", true);
	}
	
	/**
	 * Join an array of Strings into one String.
	 * 
	 * @author Falkreon
	 * @param strings
	 *            The array of String to join together
	 * @param separator
	 *            A delimiter to place between the joined Strings
	 * @param ignoreNulls
	 *            If true, skips joining null entries entirely. If false, outputs separators "around" the null entry.
	 * @return The combined String
	 */
	public static String join(final String[] strings, final String separator, final boolean ignoreNulls) {
		final StringBuilder result = new StringBuilder();
		boolean lastWasNull = false;
		for (int i = 0; i < strings.length; i++) {
			if (i != 0) {
				if (!lastWasNull || ignoreNulls) {
					result.append(separator);
				}
			}
			
			if (strings[i] != null) {
				result.append(strings[i]);
				lastWasNull = false;
			} else {
				lastWasNull = true;
			}
		}
		
		return result.toString();
	}
	
	public static Map.Entry<String, String> getKeyValuePair(final String pair, final String delimiter) {
		final int delimiterPosition = pair.indexOf(delimiter, 0);
		final String key = pair.substring(0, delimiterPosition);
		final String value = pair.substring(delimiterPosition + delimiter.length());
		
		return Maps.createImmutableEntry(key, value);
	}
	
	/**
	 * Compares two strings, trimming either to length if necessary, and returns how alike they are from 0 to 1.0.<br/>
	 * The length of the second string is seen as the "master" length, and 1.0 will only be returned if s1 matches s2 perfectly up to it's
	 * length.<br/>
	 * Examples<br/>
	 * 
	 * <pre>
	 * similarity("abcde", "abc") -> 1.0
	 * similarity("abc", "abcde") ->  0.6
	 * similarity("foo", "bar") -> 0.0
	 * similarity("hamburger", "ham") -> 1.0
	 * similarity("ham", "hamburger") -> 0.33333333
	 * similarity("bar", "baz") -> 0.66666667
	 * </pre>
	 * 
	 * @author Aesen Vismea
	 * @param s1
	 *            The first string in the comparison operation
	 * @param s2
	 *            The second, "master" string, in the comparison
	 * @return A double from 0.0 to 1.0 denoting how similar the two strings are
	 */
	public static double similarity(String s1, String s2) {
		final int max = s2.length();
		if (s1.length() > s2.length()) {
			s2 = s2.substring(0, s1.length() - 1);
		}
		if (s2.length() > s1.length()) {
			s1 = s1.substring(0, s2.length() - 1);
		}
		int matched = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) == s2.charAt(i)) {
				matched++;
			}
		}
		return (double) matched / (double) max;
	}
}
