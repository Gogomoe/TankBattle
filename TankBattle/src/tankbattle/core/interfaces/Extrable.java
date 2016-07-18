package tankbattle.core.interfaces;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 表示可扩展
 *
 * @author Gogo
 */
public interface Extrable {

	public Map<String, Object> extras = Collections.synchronizedMap(new HashMap<String, Object>());

	@SuppressWarnings("unchecked")
	default public <E> E put(String key, E obj) {
		return (E) extras.put(key, obj);
	}

	default public Object getObj(String key) {
		return extras.get(key);
	}

	@SuppressWarnings("unchecked")
	default public <T> T getObj(String key, Class<T> T) {
		return (T) extras.get(key);
	}

	default public boolean contains(String key) {
		return extras.containsKey(key);
	}

	default public int getInt(String key) {
		return ((Integer) extras.get(key)).intValue();
	}

	default public double getDouble(String key) {
		return ((Double) extras.get(key)).doubleValue();
	}

	default public double getDoubleSafe(String key) {
		return ((Double) extras.get(key)).doubleValue();
	}

	default public boolean getBool(String key) {
		return ((Boolean) extras.get(key)).booleanValue();
	}

	default public String getString(String key) {
		return (String) extras.get(key);
	}

}
