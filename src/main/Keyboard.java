package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	public static volatile int X_DIR = 1, Y_DIR = 0;

	
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			if(Y_DIR == 1) break;
			X_DIR = 0;
			Y_DIR = -1;
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			if(Y_DIR == -1) break;
			X_DIR = 0;
			Y_DIR = 1;
			break;
			
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			if(X_DIR == 1) break;
			Y_DIR = 0;
			X_DIR = -1;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			if(X_DIR == -1) break;
			Y_DIR = 0;
			X_DIR = 1;
			break;
		}
		
		//System.out.println("XDIR - " + X_DIR + ", YDIR - " + Y_DIR);
	}
	
	private static Keyboard i;
	public static Keyboard instance()
	{
		if(i == null) i = new Keyboard();
		return i;
	}
	
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	
	
	public static void reset()
	{
		X_DIR = 1;
		Y_DIR = 0;
	}
	
}
