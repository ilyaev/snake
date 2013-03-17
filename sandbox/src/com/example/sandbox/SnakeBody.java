package com.example.sandbox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.graphics.Canvas;

public class SnakeBody {
	
	List<SnakePiece>  items;
	Snake snake;
	
	int lastIndex = 0;
	
	public SnakeBody(Snake tSnake) {
		snake = tSnake;
		items = new ArrayList<SnakePiece>();
		SnakePiece head = new SnakePiece(snake.snakeX, snake.snakeY);
		head.pType = SnakePiece.TYPE_HEAD;
		head.delay = 0;
		items.add(head);
	}
	
	public void grow() {
		SnakePiece lastPart = items.get(lastIndex);
		SnakePiece part = new SnakePiece(lastPart.cellX, lastPart.cellY);
		
		items.add(part);
		lastIndex += 1;
	}
	
	public void draw(Canvas canvas) {
		Iterator<SnakePiece> iterator = items.iterator();
		while (iterator.hasNext()) {
			SnakePiece piece = iterator.next();
			canvas.drawCircle(snake.getCellCenterX(piece.cellX), snake.getCellCenterY(piece.cellY), snake.cellSizePx / 2, snake.orangePaint);			
		}
	}
	
	public void calculate() {
		if (snake.currentCmd == Snake.CMD_LEFT) {
			items.get(0).cellX -= 1;
		} else if (snake.currentCmd == Snake.CMD_RIGHT) {
			items.get(0).cellX += 1;
		} else if (snake.currentCmd == Snake.CMD_UP) {
			items.get(0).cellY -= 1;
		} else if (snake.currentCmd == Snake.CMD_DOWN) {
			items.get(0).cellY += 1;
		}
		
		if (items.get(0).cellX < 1) {
			items.get(0).cellX = snake.cnHorizontal;
		} else if (items.get(0).cellX > snake.cnHorizontal) {
			items.get(0).cellX = 1;
		}
		
		if (items.get(0).cellY < 1) {
			items.get(0).cellY= snake.cnVertical;
		} else if (items.get(0).cellY > snake.cnVertical) {
			items.get(0).cellY = 1;
		}
		
		snake.snakeX = items.get(0).cellX;
		snake.snakeY = items.get(0).cellY;
		
		SnakePiece last = items.get(0);
		int lastX = last.cellX;
		int lastY = last.cellY;
		
		if (items.size() > 1) {
			for(int i = 1 ; i < items.size() ; i++) {
				int tmpX = items.get(i).cellX;
				int tmpY = items.get(i).cellY;
				
				if (items.get(i).delay == 0) {
					items.get(i).cellX = lastX;
					items.get(i).cellY = lastY;									
				} else {
					items.get(i).delay -= 1;
				}
				
				lastX = tmpX;
				lastY = tmpY;
			}
		}		
	}
	
	
	
}
