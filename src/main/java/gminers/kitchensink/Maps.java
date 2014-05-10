package gminers.kitchensink;


import java.util.HashMap;
import java.util.Map;


public final class Maps {
	/**
	 * Convert two arrays to a map. The map is not backed by the arrays. <br/>
	 * Duplicate keys are replaced with the last occurrence of the duplicate key, and it's value.
	 * 
	 * @param keys
	 *            Keys
	 * @param values
	 *            Values
	 * @return A map constructed from the keys and values.
	 */
	public static <K, V> Map<K, V> asMap(final K[] keys, final V[] values) {
		if (keys.length != values.length) {
			throw new IllegalArgumentException("Keys and values are differing lengths");
		}
		final HashMap<K, V> map = new HashMap<K, V>();
		for (int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
		return map;
	}
	
	/**
	 * Build a map of like keys and values, paired in twos, as such that "key" would be a key, and "value" would be a value:<br/>
	 * <code>{"key", "value", "key", "value", "key", "value", "key", "value"}</code>
	 */
	public static <T> Map<T, T> asMap(final T... pairs) {
		if (pairs.length % 2 != 0) {
			throw new IllegalArgumentException("Key/value pair is incomplete");
		}
		final HashMap<T, T> map = new HashMap<T, T>();
		for (int i = 0; i < pairs.length - 1; i += 2) {
			map.put(pairs[i], pairs[i + 1]);
		}
		return map;
	}
	
	/**
	 * Build a map of unlike keys and values, paired in twos, as such that "key" would be a key, and 5 would be a value:<br/>
	 * <code>{"key", 5, "key", 5, "key", 5, "key", 5}</code>
	 */
	@SuppressWarnings({
		"unchecked"
	})
	public static <K, V> Map<K, V> asUnlikeMap(final Class<K> keyClass, final Class<V> valueClass,
			final Object... pairs) {
		if (pairs.length % 2 != 0) {
			throw new IllegalArgumentException("Key/value pair is incomplete");
		}
		final HashMap<K, V> map = new HashMap<K, V>();
		K key = null;
		for (int i = 0; i < pairs.length - 1; i++) {
			if (key == null) {
				key = (K) pairs[i];
			} else {
				map.put(key, (V) pairs[i]);
				key = null;
			}
		}
		return map;
	}
	
	public static <K, V> Map.Entry<K, V> createEntry(final K key, final V value) {
		final K transitional_k = key;
		final V transitional_v = value;
		
		return new Map.Entry<K, V>() {
			K	k	= transitional_k;
			V	v	= transitional_v;
			
			@Override
			public K getKey() {
				return k;
			}
			
			@Override
			public V getValue() {
				return v;
			}
			
			@Override
			public V setValue(final V v) {
				final V result = v;
				this.v = v;
				return result;
			}
			
		};
	}
	
	public static <K, V> Map.Entry<K, V> createImmutableEntry(final K key, final V value) {
		return new Map.Entry<K, V>() {
			@Override
			public K getKey() {
				return key;
			}
			
			@Override
			public V getValue() {
				return value;
			}
			
			@Override
			public V setValue(final V v) {
				throw new UnsupportedOperationException("This Map.Entry is immutable.");
			}
			
		};
	}
}
