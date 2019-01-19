package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Snake {
	
	private static final double SPEED = 300;
	
	private static int x = 0, y = 0, cellx = 0, celly = 0;
	private static int Px = x, Py = y;
	private static double timer = 0;
	
	public static final Random random = new Random();
	
	private static Color snakeColor = Color.GREEN;
	
	private static ArrayList<SnakeSegment> segments = new ArrayList<>();
	
	public static void tick()
	{
		//System.out.println("Posx = "+x + ", Posy = "+y);
		
		timer += SPEED;
		if(timer >= 1000d) {Px = x;
							Py = y;
							x += Grid.CELL_SIZE * Keyboard.X_DIR;
							y += Grid.CELL_SIZE * Keyboard.Y_DIR;
							cellx = x/Grid.CELL_SIZE;
							celly = y/Grid.CELL_SIZE;
							timer = 0;
		}
		
		boolean firstPass = true;
		for(int i = 0; i < segments.size(); i++)
		{
			if(firstPass)
			{
				firstPass = false;
				segments.get(i).move(Px, Py);
			} else
				segments.get(i).move(segments.get(i-1).Prevx, segments.get(i-1).Prevy);
		}
		
		if(cellx > Grid.GRID_W || celly > Grid.GRID_H ||
				   cellx < 0 || celly < 0)
						die();
		
		if(Grid.isThereAFruit(cellx, celly)) /* TODO: Increase length */ {
			Grid.consumeFruit();
			
			if(segments.isEmpty())
				segments.add(new SnakeSegment(x - Grid.CELL_SIZE*Keyboard.X_DIR, y - Grid.CELL_SIZE*Keyboard.Y_DIR));
			else
				segments.add(new SnakeSegment(segments.get(segments.size()-1).x + Grid.CELL_SIZE,
						segments.get(segments.size()-1).y + Grid.CELL_SIZE));
			
			System.out.println("Added a new snake segment! X = "+(segments.get(segments.size()-1).x + Grid.CELL_SIZE) +
					", Y = "+(segments.get(segments.size()-1).y + Grid.CELL_SIZE) + ", Total = "+segments.size());
		}
	}
	
	public static void render(Graphics g)
	{
		g.setColor(snakeColor);
		
		g.fillRect((x+1), (y+1), Grid.CELL_SIZE-1, Grid.CELL_SIZE-1);
		
		for (SnakeSegment snakeSegment : segments) {
			g.fillRect((snakeSegment.x+1), (snakeSegment.y+1), Grid.CELL_SIZE-1, Grid.CELL_SIZE-1);
		}
	}
	
	
	
	public static void die()
	{
		//System.out.println("X size = "+(Grid.GRID_W * Grid.CELL_SIZE) + ", Y size = "+(Grid.GRID_H * Grid.CELL_SIZE));
		if(x < 0) {x = 0; System.out.println("Fixing negative X");}
		if(y < 0) {y = 0; System.out.println("Fixing negative Y");}
		if(x > Grid.GRID_W * Grid.CELL_SIZE) {x = (Grid.GRID_W * Grid.CELL_SIZE) - Grid.CELL_SIZE; System.out.println("Fixing oob X");}
		if(y > Grid.GRID_H * Grid.CELL_SIZE) {y = (Grid.GRID_H * Grid.CELL_SIZE) - Grid.CELL_SIZE; System.out.println("Fixing oob Y");}
		
		snakeColor = Color.ORANGE;
		Loop.stop(false);
		System.out.println("You are dead!");
	}
	
	
	public static void reset()
	{
		segments = new ArrayList<>();
		x = 0;
		y = 0;
		cellx = 0;
		celly = 0;
		timer = 0;
		snakeColor = Color.GREEN;
	}
}
