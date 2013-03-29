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
	SnakeBoard board;
	
	Paint redPaint;
	
	public SnakeBugList(SnakeBoard eSnake) {
		bugs = new ArrayList<SnakeBug>();
		board = eSnake;
		redPaint = new Paint();
		redPaint.setColor(Color.RED);
	}
	
	public void spawnBug(int race) {
		Random r = new Random();
		int bX = r.nextInt(board.cnHorizontal - 1) + 1;
		int bY = r.nextInt(board.cnVertical - 1) + 1;
		
		if (board.oMap[bX][bY]) {
			spawnBug(race);
		} else {		
			SnakeBug bug = new SnakeBug(bX, bY);
			bug.setRace(race);			
			bugs.add(bug);
		}
	}
	
	public void draw(Canvas canvas) {
		Iterator<SnakeBug> iterator = bugs.iterator();
		
		while(iterator.hasNext()) {
			SnakeBug bug = iterator.next();
			canvas.drawCircle(board.getCellCenterX(bug.cellX), board.getCellCenterY(bug.cellY), board.cellSizePx / 2, redPaint);
		}
	}

	public void processIntercection() {
		Iterator<SnakeBug> iterator = bugs.iterator();
		
		while(iterator.hasNext()) {
			SnakeBug bug = iterator.next();
			for(int i = 0 ; i < board.snakes.size() ; i++) {
				if (bug.cellX == board.snakes.get(i).snakeX && bug.cellY == board.snakes.get(i).snakeY && bug.active == 1) {
					spawnBug(bug.race);
					bug.active = 0;
					bugs.remove(bug);
					board.snakes.get(i).body.grow();
					board.snakes.get(i).path.clear();
				}
			}
		}
		
	}
	
}
