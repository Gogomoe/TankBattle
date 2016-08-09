package test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import tankbattle.core.TankBattle;
import tankbattle.core.battle.attack.Assailable;
import tankbattle.core.battle.tank.BulletFactory;
import tankbattle.core.bullet.Bullet;
import tankbattle.core.entity.EntityGroupEvent;
import tankbattle.core.event.Listener;
import tankbattle.core.input.KeyEvent;
import tankbattle.core.input.KeyOperateListener;
import tankbattle.core.input.KeyOperation;
import tankbattle.core.input.KeyPressedEvent;
import tankbattle.core.input.KeyReleasedEvent;
import tankbattle.core.input.KeyTypedEvent;
import tankbattle.core.others.ImageUtils;
import tankbattle.core.paint.EntityPaintEvent;
import tankbattle.core.paint.entity.EntityMovePaintListener;
import tankbattle.core.paint.entity.MaterialLostListener;
import tankbattle.core.position.Direction;
import tankbattle.core.tank.Tank;
import tankbattle.core.time.TimeListener;
import tankbattle.core.time.TimerGroup;
import tankbattle.core.view.View;

public class GameTest extends Application {

	@Override
	public void start(Stage primaryStage) {

		Group root = new Group();
		Scene scene = new Scene(root, 600, 480);

		View v = new View(600, 480);

		root.getChildren().add(v.getCanvas());

		TankBattle game = new TankBattle();
		TankBattle.setGame(game);
		game.init();

		TimerGroup ti = new TimerGroup(5);
		ti.createThread();
		TankBattle.getGame().getTimer().putTimer("view", ti);

		class Xiaoyv extends Bullet {

			public Xiaoyv(Assailable attacker, int ATK) {
				super(attacker, ATK);
			}

			public void staticInit() {
				try {
					BufferedImage img = ImageUtils.cutImage(
							ImageIO.read(getClass().getResource("../tankbattle/images/bullets/etama.png")), 0, 48, 16,
							16);
					EntityMovePaintListener e = new EntityMovePaintListener(Xiaoyv.class);
					e.setEast(img).setNorth(img).setWest(img).setSouth(img);
					TankBattle.getGame().getProcess().addListener(EntityPaintEvent.class, e);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		class MaoYv extends Tank {

			@Override
			public void staticInit() {
				super.staticInit();
				try {
					BufferedImage[] is = ImageUtils
							.cutImage(ImageIO.read(getClass().getResource("../tankbattle/images/tanks/毛玉.png")), 4, 4);
					EntityMovePaintListener e = new EntityMovePaintListener(MaoYv.class);
					e.setSouth(is[0], is[1], is[2], is[3]);
					e.setWest(is[4], is[5], is[6], is[7]);
					e.setEast(is[8], is[9], is[10], is[11]);
					e.setNorth(is[12], is[13], is[14], is[15]);
					e.setSheight(1.5);
					TankBattle.getGame().getProcess().addListener(EntityPaintEvent.class, e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void init() {
				super.init();
				BulletFactory bf = () -> {
					Bullet b = new Xiaoyv(this, 20);
					b.setPlayer(this.player());
					b.setTowards(this.towards());
					b.setPosition(this.position());
					Set<Bullet> set = new HashSet<>();
					set.add(b);
					return set;
				};
				put(KEY_BULLET_FACTORY, bf);
			}

		}

		// Entity entity = new Entity();
		Tank entity = new MaoYv();

		TankBattle.getGame().getProcess().send(new EntityGroupEvent(entity, EntityGroupEvent.ADD_ENTITY));

		TankBattle.getGame().getProcess().addListener(MaterialLostListener.LID, 8000, EntityPaintEvent.class,
				new MaterialLostListener());

		TankBattle.getGame().getProcess().addListener(KeyOperateListener.LID, Listener.EXECUTE, KeyEvent.class,
				new KeyOperateListener());

		v.getCanvas().addEventHandler(javafx.scene.input.KeyEvent.ANY, e -> {
			TankBattle.setGame(game);
			if (e.getEventType().equals(javafx.scene.input.KeyEvent.KEY_PRESSED)) {
				TankBattle.getGame().getProcess().send(new KeyPressedEvent(e, v));
			} else if (e.getEventType().equals(javafx.scene.input.KeyEvent.KEY_RELEASED)) {
				TankBattle.getGame().getProcess().send(new KeyReleasedEvent(e, v));
			} else if (e.getEventType().equals(javafx.scene.input.KeyEvent.KEY_TYPED)) {
				TankBattle.getGame().getProcess().send(new KeyTypedEvent(e, v));
			}
			TankBattle.setGame(null);
		});
		v.getKeyManager().add(KeyCode.UP, new KeyOperation("上", f -> {
			entity.setTowards(Direction.NORTH);
			if (f.getKeyType() == KeyEvent.KEY_PRESSED) {
				entity.setMoving(true);
			} else if (f.getKeyType() == KeyEvent.KEY_RELEASED) {
				entity.setMoving(false);
			}
		}));
		v.getKeyManager().add(KeyCode.DOWN, new KeyOperation("下", f -> {
			entity.setTowards(Direction.SOUTH);
			if (f.getKeyType() == KeyEvent.KEY_PRESSED) {
				entity.setMoving(true);
			} else if (f.getKeyType() == KeyEvent.KEY_RELEASED) {
				entity.setMoving(false);
			}
		}));
		v.getKeyManager().add(KeyCode.LEFT, new KeyOperation("左", f -> {
			entity.setTowards(Direction.WEST);
			if (f.getKeyType() == KeyEvent.KEY_PRESSED) {
				entity.setMoving(true);
			} else if (f.getKeyType() == KeyEvent.KEY_RELEASED) {
				entity.setMoving(false);
			}
		}));
		v.getKeyManager().add(KeyCode.RIGHT, new KeyOperation("右", f -> {
			entity.setTowards(Direction.EAST);
			if (f.getKeyType() == KeyEvent.KEY_PRESSED) {
				entity.setMoving(true);
			} else if (f.getKeyType() == KeyEvent.KEY_RELEASED) {
				entity.setMoving(false);
			}
		}));
		v.getKeyManager().add(KeyCode.SPACE, new KeyOperation("发射", f -> {
			if (f.getKeyType() == KeyEvent.KEY_PRESSED) {
				entity.attack();
			}
		}));

		TankBattle.getGame().getTimer().getTimer("view")
				.addListener(new TimeListener(1000 / TankBattle.getGame().getFPS(), e -> {
					v.paint();
				}));
		TankBattle.getGame().getTimer().addListener(new TimeListener(10000, e -> {
			System.gc();
		}));

		TankBattle.getGame().getTimer().start();
		primaryStage.setScene(scene);
		primaryStage.show();
		v.getCanvas().requestFocus();
		primaryStage.setOnCloseRequest(e -> {
			TankBattle.setGame(game);
			TankBattle.getGame().getTimer().stop();
			System.gc();
			TankBattle.setGame(null);
			System.exit(0);
		});

		TankBattle.setGame(null);

	}

	public static void main(String[] args) {
		launch(args);
	}

	static {
		new CodeCount(CodeCount.SIMPLE, "./src").codeLines();
	}

}
