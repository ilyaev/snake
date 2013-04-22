package com.example.sandbox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.graphics.Canvas;

public class SnakeBody {
	
	List<SnakePiece>  items;
	SnakeBoard board;
	Snake snake;
	
	int lastIndex = 0;
	
	public SnakeBody(SnakeBoard tSnakeBoard, Snake tSnake) {
		board = tSnakeBoard;
		snake = tSnake;
		
		items = new ArrayList<SnakePiece>();
		SnakePiece head = new SnakePiece(snake.snakeX, snake.snakeY, board);
		head.pType = SnakePiece.TYPE_HEAD;
		head.delay = 0;
		items.add(head);
	}
	
	public void grow(int bugType) {
		SnakePiece lastPart = items.get(lastIndex);

		SnakePiece part = new SnakePiece(lastPart.cellX, lastPart.cellY, board);
		items.add(part);
		lastIndex += 1;
		
		if (bugType == SnakeBug.BUG_TRIPPLE || bugType == SnakeBug.BUG_CHERRY) {
			SnakePiece part2 = new SnakePiece(lastPart.cellX, lastPart.cellY, board);
			part2.delay = 2;
			items.add(part2);
			lastIndex += 1;
		}
			
		if (bugType == SnakeBug.BUG_TRIPPLE) {
			SnakePiece part3 = new SnakePiece(lastPart.cellX, lastPart.cellY, board);
			part3.delay = 3;
			items.add(part3);
			lastIndex += 1;
		}
	}
	
	public void addTail(int cX, int cY) {
		SnakePiece part = new SnakePiece(cX, cY, board);
		items.add(part);
		lastIndex += 1;
	}
	
	public void draw(Canvas canvas) {
		Iterator<SnakePiece> iterator = items.iterator();
		
		float halfSize = board.cellSizePx / 2; 
		
		while (iterator.hasNext()) {
			SnakePiece piece = iterator.next();
			piece.draw(canvas, (snake.race == Snake.RACE_PLAYER ? board.greenPaint : board.darkGreenPaint), halfSize);
		}
	}
	
	public void calculate() {
		Iterator<SnakePiece> iterator = items.iterator();
		
		while(iterator.hasNext()) {
			SnakePiece piece = iterator.next();
			piece.calculate();
		}
	}
	
	public void calculateByCommand() {		
		
		// move tail
		int lastX = snake.snakeX;
		int lastY = snake.snakeY;
		
		if (items.size() > 1) {
			for(int i = 1 ; i < items.size() ; i++) {
				int tmpX = items.get(i).cellX;
				int tmpY = items.get(i).cellY;
				
				if (items.get(i).delay == 0) {
					items.get(i).cellX = lastX;
					items.get(i).cellY = lastY;		
					items.get(i).setTarget(lastX,  lastY);
				} else {
					items.get(i).delay -= 1;
				}
				
				lastX = tmpX;
				lastY = tmpY;
			}
		}
		
		// move head
		if (snake.currentCmd == Snake.CMD_LEFT) {
			snake.snakeX -= 1;
		} else if (snake.currentCmd == Snake.CMD_RIGHT) {
			snake.snakeX += 1;
		} else if (snake.currentCmd == Snake.CMD_UP) {
			snake.snakeY -= 1;
		} else if (snake.currentCmd == Snake.CMD_DOWN) {
			snake.snakeY += 1;
		}
		
		if (snake.snakeX < 1 || snake.snakeX > board.cnHorizontal || snake.snakeY < 1 || snake.snakeY > board.cnVertical) {
			snake.deactivateSnake();
		}
		
		if (snake.active == 1) {
			items.get(0).cellX = snake.snakeX;
			items.get(0).cellY = snake.snakeY;
			
			items.get(0).setTarget(snake.snakeX, snake.snakeY);
			
			if (snake.currentCmd != 0 && board.oMap[snake.snakeX][snake.snakeY]) {
				snake.deactivateSnake();
			}
		}
		
	}

	public void shrink(int bugType) {
		if (items.size() > 2) {
			SnakePiece piece = items.get(items.size()  -  1);
			board.makeBoom(piece.x, piece.y, null);
			items.get(items.size()  -  1).active = 0;
			items.remove(items.size() - 1);
			lastIndex -= 1;			
		}
		
		if (items.size() > 2 && (bugType == SnakeBug.BUG_TRIPPLE || bugType == SnakeBug.BUG_CHERRY)) {
			SnakePiece piece = items.get(items.size()  -  1);
			board.makeBoom(piece.x, piece.y, null);
			items.get(items.size()  -  1).active = 0;
			items.remove(items.size() - 1);
			lastIndex -= 1;			
		}
		
		if (items.size() > 2 && bugType == SnakeBug.BUG_TRIPPLE) {
			SnakePiece piece = items.get(items.size()  -  1);
			board.makeBoom(piece.x, piece.y, null);
			items.get(items.size()  -  1).active = 0;
			items.remove(items.size() - 1);
			lastIndex -= 1;			
		}
	}
	
	
	
}
