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
	Paint darkRedPaint;
	
	public SnakeBugList(SnakeBoard eSnake) {
		bugs = new ArrayList<SnakeBug>();
		board = eSnake;
		redPaint = new Paint();
		redPaint.setColor(Color.RED);
		
		darkRedPaint = new Paint();
		darkRedPaint.setARGB(255, 127, 0, 0);
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
			canvas.drawCircle(board.getCellCenterX(bug.cellX), board.getCellCenterY(bug.cellY), board.cellSizePx / 2, (bug.race == Snake.RACE_PLAYER ? redPaint : darkRedPaint));
		}
	}

	public void processIntercection() {
		Iterator<SnakeBug> iterator = bugs.iterator();
		
		List<SnakeBug> toRemove;
		
		toRemove = new ArrayList<SnakeBug>();
		
		while(iterator.hasNext()) {
			SnakeBug bug = iterator.next();
			for(int i = 0 ; i < board.snakes.size() ; i++) {
				if (bug.cellX == board.snakes.get(i).snakeX && bug.cellY == board.snakes.get(i).snakeY && bug.active == 1) {
					bug.active = 0;
					bug.eatenBy = board.snakes.get(i).race;
					toRemove.add(bug);					
					board.snakes.get(i).path.clear();
				}
			}
		}
		
		if (toRemove.size() > 0) {
			for(int i = 0 ; i < toRemove.size() ; i++) {
				int race = toRemove.get(i).race;
				spawnBug(race);
				
				SnakeBug bug = toRemove.get(i);
				
				bugs.remove(toRemove.get(i));
				
				if (bug.eatenBy == bug.race) {
					board.getSnakeByRace(bug.race).body.grow();						
				} else {
					board.getSnakeByRace(bug.race).body.shrink();
				}
				
			}
		}
		
	}

	public SnakeBug getBugByRace(int race) {
		SnakeBug bug = null;
		if (bugs.size() > 0) {
			for(int i = 0 ; i < bugs.size() ; i++) {
				if (bugs.get(i).race == race) {
					bug = bugs.get(i);
				}
			}
		}
		return bug;
	}
	
}
