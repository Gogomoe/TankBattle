package test;

import tankbattle.core.Bullet;
import tankbattle.core.Entity;
import tankbattle.core.Tank;
import tankbattle.core.TankBattle;
import tankbattle.core.move.contact.ContactEntityEvent;
import tankbattle.core.move.contact.LeaveEntityEvent;
import tankbattle.core.position.Direction;
import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;
import tankbattle.core.shape.Circle;
import tankbattle.core.shape.VCircle;
import tankbattle.core.time.TimeListener;

public class Test {

	public static void main(String[] args) {
		contactTest();
	}

	public static void contactTest() {
		Entity e = new Entity();
		e.setSpeed(10);
		e.setTowards(Direction.SOUTH);
		e.setMoving(true);

		Entity e2 = new Entity();
		e2.setLayer(-1);
		e2.setPosition(new Point(0, 100));

		TankBattle.getGame().getEntityGroup().add(e);
		TankBattle.getGame().getEntityGroup().add(e2);

		long start = System.currentTimeMillis();

		TankBattle.getGame().getTimer().addListener(new TimeListener(1000, ev -> {
			System.out.println(e.position() + "   " + (System.currentTimeMillis() - start));
		}));

		TankBattle.getGame().getProcess().addListener(ContactEntityEvent.class, l -> {
			System.out.println("contact");
		});
		TankBattle.getGame().getProcess().addListener(LeaveEntityEvent.class, l -> {
			System.out.println("leave");
		});
	}

	public static void shapeTest() {
		VCircle c1 = new VCircle(new Circle(5), new Vector(5, 5));
		VCircle c2 = new VCircle(new Circle(5), new Vector(-5, 5));
		System.out.println(c1.contacts(c2));
	}

	public static void attackTest() {
		Tank t = new Tank();
		t.setDEF(5);
		t.attack();
		TankBattle.getGame().getEntityGroup().getAll().forEach(e -> {
			Bullet b = (Bullet) e;
			b.damage(t);
		});
		System.out.println(t.getHP());
		System.exit(0);
	}

	public static void moveTest() {
		Entity e = new Entity();
		e.setSpeed(10);
		e.setMoving(true);
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
