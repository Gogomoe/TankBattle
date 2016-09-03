package tankbattle.core.input.base;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javafx.scene.input.KeyCode;
import tankbattle.core.entity.Entity;
import tankbattle.core.input.KeyEvent;
import tankbattle.core.input.KeyManager;
import tankbattle.core.input.KeyOperation;
import tankbattle.core.position.Direction;

/**
 * 用于处理实体移动的类<br>
 * 当某个键被按下时，实体会向指定方向移动<br>
 * 默认值为 小键盘的 上下左右 对应北南西东<br>
 * 使用前需要通过{@link #setEntity(Entity)}来设定移动的实体，也可以把该值改为null设为不移动任何实体<br>
 * 可以调用{@link #bindKey(Direction, KeyCode)}方法改变绑定<br>
 * 
 * @author Gogo
 *
 */
public class EntityKeyManager {

	private KeyManager keys;

	private Entity entity;

	private List<Direction> keyCache = new ArrayList<>();

	private final KeyBind NORTH = new KeyBind(Direction.NORTH, new KeyOperation("向北移动", new MoveKey(Direction.NORTH)));
	private final KeyBind SOUTH = new KeyBind(Direction.SOUTH, new KeyOperation("向北移动", new MoveKey(Direction.SOUTH)));
	private final KeyBind EAST = new KeyBind(Direction.EAST, new KeyOperation("向北移动", new MoveKey(Direction.EAST)));
	private final KeyBind WEST = new KeyBind(Direction.WEST, new KeyOperation("向北移动", new MoveKey(Direction.WEST)));

	/**
	 * 用于记录某方向对应的按键和操作的类<br>
	 * 通常direction和operation不应该被更改<br>
	 * keyCode可以被更改<br>
	 * 
	 * @author Gogo
	 *
	 */
	private final class KeyBind {

		private Direction direction;
		private KeyCode keyCode;
		private KeyOperation operation;

		public KeyBind(Direction direction, KeyOperation operation) {
			this.direction = direction;
			this.operation = operation;
		}
	}

	/**
	 * 默认的按键移动操作<br>
	 * 
	 * @author Gogo
	 *
	 */
	private final class MoveKey implements Consumer<KeyEvent> {

		private Direction dir;

		public MoveKey(Direction dir) {
			this.dir = dir;
		}

		@Override
		public void accept(KeyEvent t) {
			if (entity == null) {
				return;
			}
			if (t.getKeyType() == KeyEvent.KEY_PRESSED) {
				if (!keyCache.contains(dir)) {
					// 如果这个键还没被按下
					entity.setTowards(dir);
					keyCache.add(dir);
					entity.setMoving(true);
				}
			} else if (t.getKeyType() == KeyEvent.KEY_RELEASED) {
				keyCache.remove(dir);
				if (keyCache.size() == 0) {
					// 全部方向键均松开
					entity.setMoving(false);
				} else {
					// 把方向设为上一个按下的键
					entity.setTowards(keyCache.get(keyCache.size() - 1));
				}
			}
		}

	}

	public EntityKeyManager(KeyManager keys) {
		this.keys = keys;
		bindKey(Direction.NORTH, KeyCode.UP);
		bindKey(Direction.SOUTH, KeyCode.DOWN);
		bindKey(Direction.WEST, KeyCode.LEFT);
		bindKey(Direction.EAST, KeyCode.RIGHT);
	}

	public Entity getEntity() {
		return entity;
	}

	/**
	 * 设置需要移动的实体<br>
	 * 可以设为null以不移动任何实体<br>
	 * 
	 * @param entity
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	/**
	 * 绑定朝某个方向移动的功能到某个键<br>
	 * 
	 * @param dir
	 *            移动的方向
	 * @param keyCode
	 *            要绑定的键
	 */
	public void bindKey(Direction dir, KeyCode keyCode) {
		if (keyCode == null) {
			throw new RuntimeException("keyCode 为Null");
		}
		KeyBind keyBind = null;
		if (dir == NORTH.direction) {
			keyBind = NORTH;
		} else if (dir == SOUTH.direction) {
			keyBind = SOUTH;
		} else if (dir == EAST.direction) {
			keyBind = EAST;
		} else if (dir == WEST.direction) {
			keyBind = WEST;
		}
		if (keyBind == null) {
			throw new RuntimeException("Direction 错误,无法找到对应的移动操作");
		}
		// 移除以前的绑定
		keys.getAll(keyBind.keyCode).remove(keyBind.operation);
		keyBind.keyCode = keyCode;
		keys.add(keyCode, keyBind.operation);

	}

}
