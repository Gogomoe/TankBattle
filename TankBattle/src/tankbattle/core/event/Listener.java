package tankbattle.core.event;

/**
 * @author Gogo
 */
@FunctionalInterface
public interface Listener<T extends Event> {

	final public static int EARLY = -10000;
	final public static int NORMAL = 0;
	final public static int EXECUTE = 10000;
	final public static int AFTER_EXECUTE = 20000;

	public void listen(T event);

	default void init(EventProcess process) {

	}
}
