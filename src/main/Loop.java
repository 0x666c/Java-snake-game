package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Loop {
	
	private static volatile boolean isPaused = false, isRunning = true;
	
	
	public static volatile double DELTA_TIME = 0;
	private static Runnable gameRunnable = new Runnable() {
		public void run() {
			//isPaused = false;
			if(!stoppedByStopButton) {Snake.reset();System.out.println("Snake reset BEFORE stop");}
			isRunning = true;
			
			double timePerTick = 1000000000 / 60;
			long now;
			DELTA_TIME = 0;
			long last = System.nanoTime();
			
			while(isRunning)
			{
				now = System.nanoTime();
				DELTA_TIME += (now - last) / timePerTick;
				last = now;
				
				if(DELTA_TIME >= 0)
				{
					if(!isPaused)
					{
						tick();
						render();
					} else
						render();
					DELTA_TIME--;
				}
			}
			if(stoppedByStopButton) {Snake.reset();System.out.println("Snake reset AFTER stop");}
			Grid.reset();
			Keyboard.reset();
			render();
			render();
		}
	};
	private static Thread gameThread;
	
	
	private static void tick()
	{
		Grid.tick();
	}
	
	private static void render()
	{
		if(Main.canvas.getBufferStrategy() == null) {Main.canvas.createBufferStrategy(3); return;}
		
		BufferStrategy b = Main.canvas.getBufferStrategy();
		Graphics g = b.getDrawGraphics();
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, Main.canvas.getWidth(), Main.canvas.getHeight());

		Grid.render(g);
		
		b.show();
		g.dispose();
	}
	
	public static boolean isPaused() {
		return isPaused;
	}
	
	public synchronized static void play()
	{
		if(gameThread == null)
		{
			gameThread = new Thread(gameRunnable);
			//System.err.println("STARTED " + gameThread.getState());
			gameThread.start();
			return;
		}
		else if(!gameThread.isAlive())
		{
			gameThread = new Thread(gameRunnable);
			//System.err.println("STARTED " + gameThread.getState());
			gameThread.start();
		}
	}
	
	public synchronized static void pause()
	{
		//System.err.println("PAUSED " + gameThread.getState());
		if(gameThread != null) {
			isPaused ^= true;
		}
	}
	
	private static boolean stoppedByStopButton = false;
	public synchronized static void stop(boolean stopButton) {
		//System.err.println("STOPPED " + gameThread.getState());
		if (gameThread != null) {
			stoppedByStopButton = stopButton;
			isRunning = false;
			/*try {
				gameThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
	}

}
