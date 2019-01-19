package main;

import java.awt.Color;
import java.awt.Graphics;

public class Fruit {
	
	public final int X_CELL, Y_CELL;
	
	public Fruit(int xCell, int yCell) {
		X_CELL = xCell;
		Y_CELL = yCell;
		
		System.out.println("Fruit spawned at cell x = "+xCell + ", y = "+yCell);
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect(X_CELL * Grid.CELL_SIZE + 1, Y_CELL * Grid.CELL_SIZE + 1, Grid.CELL_SIZE - 1, Grid.CELL_SIZE - 1);
	}
}
