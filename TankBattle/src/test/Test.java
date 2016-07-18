package test;

import tankbattle.core.Entity;
import tankbattle.core.TankBattle;
import tankbattle.core.position.Direction;
import tankbattle.core.time.TimeListener;

public class Test {

	public static void main(String[] args) {

	}

	public static void moveExample() {
		Entity e = new Entity();
		e.setSpeed(10);
		e.setTowards(Direction.NORTH);
		long start = System.currentTimeMillis();
		TankBattle.getGame().getEntityGroup().add(e);
		TankBattle.getGame().getTimer().addListener(new TimeListener(1000, ev -> {
			System.out.println(e.position() + "   " + (System.currentTimeMillis() - start));
		}));
	}

	static {
		new CodeCount(CodeCount.SIMPLE, "./src").codeLines();
	}

}
