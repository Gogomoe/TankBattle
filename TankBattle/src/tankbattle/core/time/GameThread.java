package tankbattle.core.time;

import tankbattle.core.TankBattle;

/**
 * 只属于一个Game对象的线程，应尽量使用GameThread，避免一个线程同时涉及多个游戏对象<br>
 * 
 * @author Gogo
 *
 */
public class GameThread extends Thread {

	public GameThread() {
		super();
		TankBattle.setGame(this, TankBattle.getGame());
	}

	public GameThread(Runnable arg0, String arg1) {
		super(arg0, arg1);
		TankBattle.setGame(this, TankBattle.getGame());
	}

	public GameThread(Runnable arg0) {
		super(arg0);
		TankBattle.setGame(this, TankBattle.getGame());
	}

	public GameThread(String arg0) {
		super(arg0);
		TankBattle.setGame(this, TankBattle.getGame());
	}

	public GameThread(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		TankBattle.setGame(this, TankBattle.getGame());
	}

	public GameThread(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		TankBattle.setGame(this, TankBattle.getGame());
	}

	public GameThread(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		TankBattle.setGame(this, TankBattle.getGame());
	}

	public GameThread(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		TankBattle.setGame(this, TankBattle.getGame());
	}

}
