package main;

public class SnakeSegment {
	
	public int x, y;
	public int Prevx, Prevy;
	
	public SnakeSegment(int x, int y) {
		this.x = x;
		this.y = y;
		Prevx = x;
		Prevy = y;
	}
	
	public void move(int x, int y) {
		Prevx = this.x;
		Prevy = this.y;
		
		this.x = x;
		this.y = y;
	}
}
