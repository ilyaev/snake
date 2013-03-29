package com.example.sandbox;

public class SnakePiece {
	
	final static int TYPE_HEAD = 0;
	final static int TYPE_BODY = 1;
	
	int cellX;
	int cellY;
	
	float x;
	float y;
	
	float dX = 0;
	float dY = 0;
	
	float targetX;
	float targetY;
	
	SnakeBoard board;
	
	
	int pType = TYPE_BODY;
	int delay = 1;
	int iteration = 0;
	int maxIterations = 0;
	
	public SnakePiece(int cX, int cY, SnakeBoard cBoard) {
		board = cBoard;
		cellX = cX;
		cellY = cY;
		x = board.getCellCenterX(cellX);
		y = board.getCellCenterY(cellY);
	}
	
	public void setTarget(int cX, int cY) {
		targetX = board.getCellCenterX(cX);
		targetY = board.getCellCenterY(cY);
		
		maxIterations = 12;
		iteration = 0;
		
		dX = (targetX - x) / maxIterations;
		dY = (targetY - y) / maxIterations;
	}

	public void calculate() {		
		if (iteration < maxIterations) {
			x += dX;
			y += dY;
		}
		iteration += 1;		
	}	
	
	
}
