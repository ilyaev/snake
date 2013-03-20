package com.example.sandbox;

import android.util.Log;

public class Snake {
	
	final static int CMD_LEFT = 1;
	final static int CMD_RIGHT = 2;
	final static int CMD_UP = 3;
	final static int CMD_DOWN = 4;
	
	final static int RACE_PLAYER = 0;
	final static int RACE_ENEMY1 = 1;
	final static int RACE_ENEMY2 = 2;
	
	int snakeX = 0;
	int snakeY = 0;
	
	int race = RACE_PLAYER;
	
	SnakeBody body;
	SnakeBoard board;
	
	int currentCmd = 0;
	
	public Snake(int sX, int sY, SnakeBoard sBoard) {
		snakeX = sX;
		snakeY = sY;
		
		board = sBoard;		
		body = new SnakeBody(board, this);
	}
	
	public int getNextAICommand() {
		
		double lastLen = 1000;
		int nextCmd = 0;
		
		for(int x = -1 ; x < 2 ; x++) {
			for(int y = -1 ; y < 2 ; y++) {
				
				int cX = snakeX + x;
				int cY = snakeY + y;
				
				if (cX < 1) {
					cX = 1;
				} else if (cX > board.cnHorizontal) {
					cX = board.cnHorizontal;
				}
				
				if (cY < 1) {
					cY = 1;
				} else if (cY > board.cnVertical) {
					cY = board.cnVertical;
				}
				
				if (cX == snakeX && cY == snakeY) {
					continue;
				}
				
				if (x == 0 || y == 0) {
					
					
					double len = Math.sqrt(Math.pow(Math.abs(board.bugs.bugs.get(0).cellX - cX), 2) + Math.pow(Math.abs(board.bugs.bugs.get(0).cellY - cY), 2));
					
					if (len < lastLen) {
						lastLen = len;		
						if (x == -1) {
							nextCmd = CMD_LEFT;
						} else if (x == 1) {
							nextCmd = CMD_RIGHT;
						} else if (y == -1) {
							nextCmd = CMD_UP;
						} else if (y == 1) {
							nextCmd = CMD_DOWN;
						}
					}
				}
			}
		}
		
		Log.v("SNAKE", Double.toString(lastLen) + " - " + Integer.toString(nextCmd));
		
		return nextCmd;

	}

	public void calculate() {
		if (race != RACE_PLAYER) {
			currentCmd = getNextAICommand();
		}
		
		body.calculateByCommand();
		
	}

	public void draw() {
		body.draw(board.canvas);
	}
	
	public void setCommand(int cmd) {
		currentCmd = cmd;
	}

}
