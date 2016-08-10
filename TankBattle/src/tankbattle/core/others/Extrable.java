package tankbattle.core.others;

/**
 * 表示可扩展
 *
 * @author Gogo
 */
public interface Extrable {

	public Extrable extra();

	default public <E> E put(String key, E obj) {
		return extra().put(key, obj);
	}

	default public Object getObj(String key) {
		return extra().getObj(key);
	}

	default public <T> T getObj(String key, Class<T> T) {
		return extra().getObj(key, T);
	}

	default public boolean contains(String key) {
		return extra().contains(key);
	}

	default public int getInt(String key) {
		return extra().getInt(key);
	}

	default public double getDouble(String key) {
		return extra().getDouble(key);
	}

	default public boolean getBool(String key) {
		return extra().getBool(key);
	}

	default public String getString(String key) {
		return extra().getString(key);
	}

	default public Object remove(String key) {
		return extra().remove(key);
	}

	default <T> T remove(String key, Class<T> T) {
		return extra().remove(key, T);
	}

}
