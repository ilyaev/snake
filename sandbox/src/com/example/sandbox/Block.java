package com.example.sandbox;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Block {
	
	static final int BLOCK_WALL = 1;
	static final int BLOCK_KEYHOLE = 2;
	
	int x,y, type;
	
	Paint paint, blackPaint;
	private ArrayList<Point> starE;
	private Path starPath;

	public Block(int tX, int tY, int bType) {
		x = tX;
		y = tY;
		type = bType;
		
		paint = new Paint();
		paint.setARGB(255, 255, 255, 255);
		
		blackPaint = new Paint();
		blackPaint.setARGB(255, 0, 0, 0);
		
	}
	
	public void draw(Canvas canvas, SnakeBoard board) {
		
		if (type == BLOCK_KEYHOLE && starPath == null) {
			starE = new ArrayList<Point>();
			
			starPath = new Path();
			
			double angle = 2*(Math.PI/5);
			double shift = -(Math.PI / 5);
			
			float cellSize = board.cellSizePx;
			float centerX = board.getCellCenterX(x);
			float centerY = board.getCellCenterY(y);
			
			for(int i = 0 ; i < 6 ; i++) {
				starE.add(new Point((int)((cellSize / 1.8) * Math.cos(shift + angle*i)), (int)((cellSize / 1.8) * Math.sin(shift + angle*i))));
			}
			
			starPath.moveTo(centerX + starE.get(0).x, centerY + starE.get(0).y);
			starPath.lineTo(centerX + starE.get(1).x, centerY + starE.get(1).y);
			starPath.lineTo(centerX + starE.get(2).x, centerY + starE.get(2).y);
			starPath.lineTo(centerX + starE.get(3).x, centerY + starE.get(3).y);
			starPath.lineTo(centerX + starE.get(4).x, centerY + starE.get(4).y);
			starPath.lineTo(centerX + starE.get(0).x, centerY + starE.get(0).y);
		}
		
		
		float halfSize = board.cellSizePx / 2;
		
		canvas.drawRect(board.getCellCenterX(x) - halfSize , board.getCellCenterY(y) - halfSize, board.getCellCenterX(x) + halfSize, board.getCellCenterY(y) + halfSize, paint);

		switch (type) {
		case BLOCK_WALL:			
			break;
		case BLOCK_KEYHOLE:
			canvas.drawPath(starPath, blackPaint);
			//canvas.drawCircle(board.getCellCenterX(x), board.getCellCenterY(y), (float) (board.cellSizePx / 4.5), paint);
			break;
		}
		
		
	}

}
