package tankbattle.core.event;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * 用于控制事件，包括发送事件、添加事件监听的事件控制器<br>
 * {@link #addListener}可以将一个监听器绑定在本事件进程上<br>
 * 然后可以使用{@link #send(Event)}发送事件<br>
 * 
 * @author Gogo
 *
 */
public class EventProcess {

	private Set<ListenerItem<? extends Event>> listeners = Collections
			.synchronizedSet(new HashSet<ListenerItem<? extends Event>>());
	private Lock lock = new ReentrantLock();

	/**
	 * 发送一个事件<br>
	 * 
	 * @param e
	 * @return
	 */
	public <T extends Event> T send(T e) {
		lock.lock();
		listeners(e.getClass()).forEach(i -> {
			@SuppressWarnings("unchecked")
			Listener<T> l = (Listener<T>) i.getListener();
			l.listen(e);
		});
		lock.unlock();
		return e;
	}

	/**
	 * 向指定的 ListenerItem集合中发送一个事件
	 * 
	 * @param e
	 * @param listeners
	 * @return
	 */
	public <T extends Event> T send(T e, List<ListenerItem<T>> listeners) {
		lock.lock();
		listeners.forEach(i -> {
			Listener<T> l = (Listener<T>) i.getListener();
			l.listen(e);
		});
		lock.unlock();
		return e;
	}

	public <T extends Event> List<ListenerItem<? extends Event>> listeners(Class<T> listened) {
		if (listened == null) {
			return null;
		}
		lock.lock();
		List<ListenerItem<? extends Event>> l = listeners.stream()
				.filter(e -> e.getListened().isAssignableFrom(listened)).sorted((a, b) -> {
					int x = a.getPriority() - b.getPriority();
					return x == 0 ? a.getName().compareTo(b.getName()) : x;
				}).collect(Collectors.toList());
		lock.unlock();
		return l;
	}

	public List<ListenerItem<? extends Event>> listeners() {
		lock.lock();
		List<ListenerItem<? extends Event>> l = listeners.stream().sorted((a, b) -> {
			int x = a.getPriority() - b.getPriority();
			return x == 0 ? a.getName().compareTo(b.getName()) : x;
		}).collect(Collectors.toList());
		lock.unlock();
		return l;
	}

	/**
	 * 添加一个具有默认优先级和名称的事件监听器<br>
	 * 
	 * @see #addListener(int, Class, Listener)
	 * @see #addListener(String, Class, Listener)
	 * @see #addListener(String, int, Class, Listener)
	 * 
	 * @param clazz
	 *            需要监听的事件类型
	 * @param listener
	 *            监听器
	 * @return
	 */
	public <T extends Event> String addListener(Class<T> clazz, Listener<T> listener) {
		return addListener(listener.getClass().getName(), Listener.NORMAL, clazz, listener);
	}

	/**
	 * 添加一个具有默认名称的事件监听器<br>
	 * 
	 * @see #addListener(Class, Listener)
	 * @see #addListener(int, Class, Listener)
	 * @see #addListener(String, int, Class, Listener)
	 * 
	 * @param priority
	 *            优先级
	 * @param clazz
	 *            需要监听的事件类型
	 * @param listener
	 *            监听器
	 * @return
	 */
	public <T extends Event> String addListener(int priority, Class<T> clazz, Listener<T> listener) {
		return addListener(listener.getClass().getName(), priority, clazz, listener);
	}

	/**
	 * 添加一个具有默认优先级的事件监听器<br>
	 * 
	 * @see #addListener(Class, Listener)
	 * @see #addListener(String, Class, Listener)
	 * @see #addListener(String, int, Class, Listener)
	 * 
	 * @param name
	 *            名称
	 * @param clazz
	 *            需要监听的事件类型
	 * @param listener
	 *            监听器
	 * @return
	 */
	public <T extends Event> String addListener(String name, Class<T> clazz, Listener<T> listener) {
		return addListener(name, Listener.NORMAL, clazz, listener);
	}

	/**
	 * 添加一个指定名称、优先级的事件监听器<br>
	 * 
	 * @see #addListener(Class, Listener)
	 * @see #addListener(int, Class, Listener)
	 * @see #addListener(String, Class, Listener)
	 * 
	 * @param name
	 *            名称
	 * @param priority
	 *            优先级
	 * @param clazz
	 *            需要监听的事件类型
	 * @param listener
	 *            监听器
	 * @return
	 */
	public <T extends Event> String addListener(String name, int priority, Class<T> clazz, Listener<T> listener) {
		if (name == null || clazz == null || listener == null) {
			throw new NullPointerException("addListener传入null");
		}
		lock.lock();
		ListenerItem<T> item = new ListenerItem<>(name, priority, clazz, listener);
		listeners.add(item);
		lock.unlock();
		listener.init(this, item);
		return name;
	}

	/**
	 * 通过名称得到一个事件监听器
	 * 
	 * @param name
	 *            名称
	 * @return
	 */
	public ListenerItem<? extends Event> getListener(String name) {
		if (name == null) {
			return null;
		}
		lock.lock();
		Optional<ListenerItem<? extends Event>> o = listeners.stream().filter(e -> e.getName().equals(name))
				.findFirst();
		lock.unlock();

		return o.isPresent() ? o.get() : null;
	}

	public ListenerItem<? extends Event> getListener(Listener<? extends Event> listener) {
		if (listener == null) {
			return null;
		}
		lock.lock();
		Optional<ListenerItem<? extends Event>> o = listeners.stream().filter(e -> e.getListener().equals(listener))
				.findFirst();
		lock.unlock();
		return o.isPresent() ? o.get() : null;
	}

	/**
	 * 通过事件名称移除该事件
	 * 
	 * @param name
	 *            名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ListenerItem<? extends Event> removeListener(String name) {
		@SuppressWarnings("rawtypes")
		ListenerItem l = getListener(name);
		lock.lock();
		listeners.remove(l);
		lock.unlock();
		l.getListener().destory(this, l);
		return l;
	}

	/**
	 * 移除事件监听器
	 * 
	 * @param listener
	 *            需要被移除的事件监听器
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ListenerItem<? extends Event> removeListener(Listener<? extends Event> listener) {
		@SuppressWarnings("rawtypes")
		ListenerItem l = getListener(listener);
		lock.lock();
		listeners.remove(l);
		lock.unlock();
		l.getListener().destory(this, l);
		return l;
	}

}
