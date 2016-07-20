package tankbattle.core;

/**
 * 表示可扩展
 *
 * @author Gogo
 */
public interface Extrable {

	public <E> E put(String key, E obj);

	public Object getObj(String key);

	public <T> T getObj(String key, Class<T> T);

	public boolean contains(String key);

	public int getInt(String key);

	public double getDouble(String key);

	public double getDoubleSafe(String key);

	public boolean getBool(String key);

	public String getString(String key);

}
