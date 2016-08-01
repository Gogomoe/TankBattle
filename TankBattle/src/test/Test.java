package test;

import tankbattle.core.TankBattle;
import tankbattle.core.battle.bullet.BulletDamageEvent;
import tankbattle.core.bullet.Bullet;
import tankbattle.core.control.Player;
import tankbattle.core.control.Team;
import tankbattle.core.entity.Entity;
import tankbattle.core.entity.EntityGroupEvent;
import tankbattle.core.event.Listener;
import tankbattle.core.move.contact.BulletContactEvent;
import tankbattle.core.move.contact.ContactEntityEvent;
import tankbattle.core.move.contact.LeaveEntityEvent;
import tankbattle.core.position.Direction;
import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;
import tankbattle.core.shape.Circle;
import tankbattle.core.shape.VCircle;
import tankbattle.core.tank.Tank;
import tankbattle.core.time.TimeListener;
import tankbattle.core.view.View;

public class Test {

	public static void main(String[] args) {
		paintTest();
	}

	public static void paintTest() {
		Entity e1 = new Entity();
		Entity e2 = new Entity();
		// 然后应该添加绘图事件
		// 游戏开始时应对全部实体进行一次绘图，产生EntityNode中的属性
		// Entity绘图监听最后应将postion和vector生成对映的图上相对坐标point
		View v = new View(500, 500);
		e1.setLayer(100);
		e2.setLayer(50);
		e1.getNode(v).setWidth(10);
		e1.getNode(v).setHeight(10);
		e2.getNode(v).setWidth(10);
		e2.getNode(v).setHeight(10);
		e1.getNode(v).setVector(new Vector());
		e2.getNode(v).setVector(new Vector());
		TankBattle.getGame().getProcess().send(new EntityGroupEvent(e1, EntityGroupEvent.ADD_ENTITY));
		TankBattle.getGame().getProcess().send(new EntityGroupEvent(e2, EntityGroupEvent.ADD_ENTITY));
		v.paint();
	}

	public static void contactTest() {
		Entity e = new Entity();
		e.setSpeed(10);
		e.setTowards(Direction.SOUTH);
		e.setMoving(true);

		Entity e2 = new Entity();
		e2.setLayer(-1);
		e2.setPosition(new Point(0, 100));

		TankBattle.getGame().getProcess().send(new EntityGroupEvent(e, EntityGroupEvent.ADD_ENTITY));
		TankBattle.getGame().getProcess().send(new EntityGroupEvent(e2, EntityGroupEvent.ADD_ENTITY));

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
		Tank t1 = new Tank();
		Tank t2 = new Tank();
		t1.setPlayer(new Player("a", new Team("A")));
		t2.setPlayer(new Player("b", new Team("B")));
		TankBattle.getGame().getProcess().send(new EntityGroupEvent(t1, EntityGroupEvent.ADD_ENTITY));
		TankBattle.getGame().getProcess().send(new EntityGroupEvent(t2, EntityGroupEvent.ADD_ENTITY));
		TankBattle.getGame().getTeamGroup().add(t1.team());
		TankBattle.getGame().getTeamGroup().add(t2.team());

		t2.setPosition(new Point(0, 150));
		t1.attack();

		TankBattle.getGame().getTimer().addListener(new TimeListener(250, e -> {
			TankBattle.getGame().getEntityGroup().getAll().stream().filter(m -> m instanceof Bullet).forEach(d -> {
				System.out.println(d.position());
			});
		}));
		TankBattle.getGame().getProcess().addListener(Listener.AFTER_EXECUTE, BulletDamageEvent.class, f -> {
			System.out.println("HP:" + t2.getHP());
		});
		TankBattle.getGame().getProcess().addListener(BulletContactEvent.class, l -> {
			System.out.println("contact");
		});
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
