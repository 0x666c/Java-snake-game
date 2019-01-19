package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Grid {
	
	public static final int GRID_W = 27,
							GRID_H = 22,
							
							CELL_SIZE = 25,
							
							LEFT_INDENT = 0,
							TOP_INDENT = 0;
	
	private static ArrayList<Fruit> fruits = new ArrayList<>();
	
	private static Fruit current_fruit;
	
	public static void addFruit(int xCell, int yCell)
	{
		//fruits.add(new Fruit(xCell, yCell));
		current_fruit = new Fruit(xCell-1, yCell-1);
	}
	
	//public static void consumeFruit(int xCell, int yCell)
	public static void consumeFruit()
	{
		/*int ind = 999;
		for(Fruit f : fruits)
			if(xCell == f.X_CELL && yCell == f.Y_CELL) ind = fruits.indexOf(f);
		
		fruits.remove(ind);*/
		
		current_fruit = null;
	}
	
	public static boolean isThereAFruit(int xCell, int yCell)
	{
		/*for(Fruit f : fruits)
			if(xCell == f.X_CELL && yCell == f.Y_CELL) return true;
		return false;*/
		if(current_fruit == null) return false;
		
		if(xCell == current_fruit.X_CELL && yCell == current_fruit.Y_CELL) return true;
		return false;
	}	
	
	
	public static void reset()
	{
		current_fruit = null;
		fruits = new ArrayList<>();
	}
	
	
	
	public static void tick()
	{
		Snake.tick();
		if(current_fruit == null) addFruit(Snake.random.nextInt(GRID_W), Snake.random.nextInt(GRID_H));
	}
	
	public static void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		for(int w = LEFT_INDENT; w < GRID_W; w++)
			for(int h = TOP_INDENT; h < GRID_H; h++)
			{
				g.drawRect(w * (CELL_SIZE), h * (CELL_SIZE), CELL_SIZE, CELL_SIZE);
			}
		
		Snake.render(g);
		
		if(current_fruit != null) current_fruit.render(g);
	}
}
