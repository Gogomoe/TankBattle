package test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tankbattle.core.TankBattle;
import tankbattle.core.entity.Entity;
import tankbattle.core.entity.EntityGroupEvent;
import tankbattle.core.event.Listener;
import tankbattle.core.paint.EntityPaintEvent;
import tankbattle.core.position.Vector;
import tankbattle.core.time.TimeListener;
import tankbattle.core.time.TimerGroup;
import tankbattle.core.view.EntityNode;
import tankbattle.core.view.View;

public class GameTest extends Application {

	@Override
	public void start(Stage primaryStage) {

		Group root = new Group();
		Scene scene = new Scene(root, 600, 480);

		View v = new View(600, 480);
		
		root.getChildren().add(v.getCanvas());
		// v.getCanvas().setTranslateX(300);
		// v.getCanvas().setTranslateY(240);

		TankBattle game = new TankBattle();
		TankBattle.setGame(game);
		game.init();

		TimerGroup ti = new TimerGroup(5);
		ti.createThread();
		TankBattle.getGame().getTimer().putTimer("view", ti);
		ti.start();

		TankBattle.getGame().getProcess().addListener(Listener.EXECUTE, EntityPaintEvent.class, e -> {
			EntityNode node = e.getNode();
			node.setWidth(50).setHeight(50);
			node.setVector(new Vector(-25, -25));

			node.setImage(new BufferedImage(50, 50, BufferedImage.TYPE_4BYTE_ABGR));
			Graphics2D g = node.getImage().createGraphics();
			g.setColor(Color.blue);
			g.fillRect(0, 0, 50, 50);
			g.dispose();
			e.setExecuted(true);
		});

		TankBattle.getGame().getTimer().getTimer("view")
				.addListener(new TimeListener(1000 / TankBattle.getGame().getFPS(), e -> {
					v.paint();
				}));
		TankBattle.getGame().getTimer().addListener(new TimeListener(10000, e -> {
			System.gc();
		}));

		Entity entity = new Entity();
		TankBattle.getGame().getProcess().send(new EntityGroupEvent(entity, EntityGroupEvent.ADD_ENTITY));

		TankBattle.getGame().getTimer().start();
		primaryStage.setScene(scene);
		primaryStage.show();

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
