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
//			canvas.drawCircle(board.getCellCenterX(piece.cellX), board.getCellCenterY(piece.cellY), board.cellSizePx / 2, (snake.race == Snake.RACE_PLAYER ? board.greenPaint : board.darkGreenPaint)) ;
			canvas.drawRect(board.getCellCenterX(piece.cellX) - board.cellSizePx / 2 + 1, board.getCellCenterY(piece.cellY) - board.cellSizePx / 2 + 1, board.getCellCenterX(piece.cellX) + board.cellSizePx / 2 - 1, board.getCellCenterY(piece.cellY) + board.cellSizePx / 2 - 1, (snake.race == Snake.RACE_PLAYER ? board.greenPaint : board.darkGreenPaint));
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
		
		if (snake.snakeX < 1) {
			snake.snakeX = board.cnHorizontal;
		} else if (snake.snakeX > board.cnHorizontal) {
			snake.snakeX = 1;
		}
		
		if (snake.snakeY < 1) {
			snake.snakeY= board.cnVertical;
		} else if (snake.snakeY > board.cnVertical) {
			snake.snakeY = 1;
		}
		
		items.get(0).cellX = snake.snakeX;
		items.get(0).cellY = snake.snakeY;
		
	}
	
	
	
}
