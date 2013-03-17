package com.example.sandbox;

public class SnakePiece {
	
	final static int TYPE_HEAD = 0;
	final static int TYPE_BODY = 1;
	
	int cellX;
	int cellY;
	
	float x;
	float y;
	
	int pType = TYPE_BODY;
	int delay = 1;
	
	public SnakePiece(int cX, int cY) {
		cellX = cX;
		cellY = cY;
	}	
	
	
}
