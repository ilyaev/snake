package com.example.sandbox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class SnakeBugList {
	
	List<SnakeBug> bugs;
	Snake snake;
	
	Paint redPaint;
	
	public SnakeBugList(Snake eSnake) {
		bugs = new ArrayList<SnakeBug>();
		snake = eSnake;
		redPaint = new Paint();
		redPaint.setColor(Color.RED);
	}
	
	public void spawnBug(int race) {
		Random r = new Random();
		int bX = r.nextInt(snake.cnHorizontal - 1) + 1;
		int bY = r.nextInt(snake.cnVertical - 1) + 1;
		
		SnakeBug bug = new SnakeBug(bX, bY);
		bug.setRace(race);
		
		bugs.add(bug);		
	}
	
	public void draw(Canvas canvas) {
		Iterator<SnakeBug> iterator = bugs.iterator();
		
		while(iterator.hasNext()) {
			SnakeBug bug = iterator.next();
			canvas.drawCircle(snake.getCellCenterX(bug.cellX), snake.getCellCenterY(bug.cellY), snake.cellSizePx / 2, redPaint);
		}
	}

	public void processIntercection() {
		Iterator<SnakeBug> iterator = bugs.iterator();
		
		while(iterator.hasNext()) {
			SnakeBug bug = iterator.next();
			if (bug.cellX == snake.snakeX && bug.cellY == snake.snakeY) {
				spawnBug(bug.race);
				bugs.remove(bug);
				snake.growTail();
			}
		}
		
	}
	
}
