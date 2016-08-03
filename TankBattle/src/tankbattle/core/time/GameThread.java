package tankbattle.core.time;

import tankbattle.core.TankBattle;

/**
 * 只属于一个Game对象的线程,直接在Thread使用TankBattle.getGame()会出现错误，应使用GameThread
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
