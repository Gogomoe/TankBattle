package tankbattle.core.others;

/**
 * 基本本类的类均可储存用户自定义属性<br>
 * <br>
 * 可以使用{@link #put(String, Object)}方法储存属性 <br>
 * 使用{@link #getObj(String)}、{@link #getInt(String)}、{@link #getString(String)}等方法储存属性<br>
 * <br>
 * 如果想要一个新的类实现本接口，最好的方法如下<br>
 * class A implements Extrable {<br>
 * private Extra extra = new Extra();<br>
 * public Extrable extra() {<br>
 * return extra;<br>
 * }<br>
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
