package tankbattle.core.entity;

import java.util.Set;
import java.util.stream.Collectors;

import tankbattle.core.others.Group;
import tankbattle.core.position.Point;
import tankbattle.core.shape.VectorShape;

/**
 * 实体组，用于管理实体<br>
 * 通过实体组可以获取全部实体、某个位置上的实体<br>
 * 
 * @author Gogo
 *
 */
public class EntityGroup extends Group<Entity> {

	/**
	 * 获取在该图形表示区域以内的实体
	 * 
	 * @param vshape
	 * @return
	 */
	public Set<Entity> getContain(VectorShape vshape) {
		if (vshape == null) {
			return null;
		}
		lock.lock();
		Set<Entity> s = set.stream().filter(f -> f.vshape().contains(vshape)).collect(Collectors.toSet());
		lock.unlock();
		return s;
	}

	/**
	 * 获取与该图形表示区域接触的实体
	 * 
	 * @param vshape
	 * @return
	 */
	public Set<Entity> getContact(VectorShape vshape) {
		if (vshape == null) {
			return null;
		}
		lock.lock();
		Set<Entity> s = set.stream().filter(f -> f.vshape().contacts(vshape)).collect(Collectors.toSet());
		lock.unlock();
		return s;
	}

	/**
	 * 获取某个位置上的实体
	 * 
	 * @param p
	 * @return
	 */
	public Set<Entity> getPos(Point p) {
		if (p == null) {
			return null;
		}
		lock.lock();
		Set<Entity> s = set.stream().filter(f -> f.vshape().contains(p)).collect(Collectors.toSet());
		lock.unlock();
		return s;
	}

	/**
	 * 获取某个位置上的实体
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Set<Entity> getPos(double x, double y) {
		return getPos(new Point(x, y));
	}

}
