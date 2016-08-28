package tankbattle.core.event;

/**
 * 事件监听器，可以在listener中对事件进行处理<br>
 * <br>
 * 监听器的{@link #init}和{@link #destory}方法会在监听器被加入到事件进程或从事件进程中移除时调用<br>
 * 覆盖此方法对事件进行初始化操作<br>
 * 
 * @author Gogo
 */
@FunctionalInterface
public interface Listener<T extends Event> {

	final public static int EARLY = -10000;
	final public static int NORMAL = 0;
	final public static int EXECUTE = 10000;
	final public static int AFTER_EXECUTE = 20000;

	public void listen(T event);

	/**
	 * 当监听器被加入到事件进程时调用<br>
	 * 可在此定义初始化监听器的内容<br>
	 * 
	 * @param process
	 * @param item
	 */
	default void init(EventProcess process, ListenerItem<T> item) {

	}

	/**
	 * 当监听器被移除出事件进程时调用<br>
	 * 可在此定义监听器销毁资源的内容<br>
	 * 
	 * @param process
	 * @param item
	 */
	default void destory(EventProcess process, ListenerItem<T> item) {

	}
}
