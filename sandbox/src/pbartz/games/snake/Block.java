package pbartz.games.snake;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Block {
	
	static final int BLOCK_WALL = 1;
	static final int BLOCK_KEYHOLE = 2;
	static final int BLOCK_EXIT = 3;
	
	int x,y, type;
	Paint paint, blackPaint;
	private ArrayList<Point> starE;
	private Path starPath;
	boolean isUnlocked = false;
	Paint starPaint;
	
	float rx, ry, tx, ty, sx, sy;
	
	
	int maxIterations = 20;
	int iteration = maxIterations;

	public Block(int tX, int tY, int bType) {
		x = tX;
		y = tY;
		type = bType;
		
		paint = new Paint();
		paint.setARGB(255, 200, 200, 200);
		
		blackPaint = new Paint();
		blackPaint.setARGB(255, 0, 0, 0);
		
		starPaint = new Paint();
		starPaint.setARGB(255, 255, 136, 0);
		
	}
	
	public void calculate() {
		if (iteration != maxIterations) {
			rx += sx;
			ry += sy;
			iteration += 1;
			if (iteration == maxIterations) {
				rx = 0;
				ry = 0;
			}
		}
	}
	
	public void animatePlacement(SnakeBoard board) {
		tx = board.getCellCenterX(x);
		ty = board.getCellCenterY(y);
		
		Random r = new Random();
		
		rx = r.nextInt(board.sWidth);
		ry = r.nextInt(board.sHeight);
		
		sx = (tx - rx) / maxIterations;
		sy = (ty - ry) / maxIterations;
		
		iteration = 0;
	}
	
	public void draw(Canvas canvas, SnakeBoard board) {
		float cellSize = board.cellSizePx;
		float centerX = board.getCellCenterX(x);
		float centerY = board.getCellCenterY(y);
		
		if (type == BLOCK_KEYHOLE && starPath == null) {
			starE = new ArrayList<Point>();
			
			starPath = new Path();
			
			double angle = 2*(Math.PI/5);
			double shift = -(Math.PI / 5);
			
			
			
			for(int i = 0 ; i < 6 ; i++) {
				starE.add(new Point((int)((cellSize / 2) * Math.cos(shift + angle*i)), (int)((cellSize / 2) * Math.sin(shift + angle*i))));
			}
			
			starPath.moveTo(centerX + starE.get(0).x, centerY + starE.get(0).y);
			starPath.lineTo(centerX + starE.get(1).x, centerY + starE.get(1).y);
			starPath.lineTo(centerX + starE.get(2).x, centerY + starE.get(2).y);
			starPath.lineTo(centerX + starE.get(3).x, centerY + starE.get(3).y);
			starPath.lineTo(centerX + starE.get(4).x, centerY + starE.get(4).y);
			starPath.lineTo(centerX + starE.get(0).x, centerY + starE.get(0).y);
		}
		
		float halfSize = board.cellSizePx / 2 - 1;
		
		switch (type) {
		case BLOCK_WALL:
			if (iteration != maxIterations) {
				canvas.drawRect(rx - halfSize , ry - halfSize, rx + halfSize, ry + halfSize, paint);
			} else {
				canvas.drawRect(board.getCellCenterX(x) - halfSize , board.getCellCenterY(y) - halfSize, board.getCellCenterX(x) + halfSize, board.getCellCenterY(y) + halfSize, paint);
			}
			break;
		case BLOCK_KEYHOLE:
			canvas.drawRect(board.getCellCenterX(x) - halfSize , board.getCellCenterY(y) - halfSize, board.getCellCenterX(x) + halfSize, board.getCellCenterY(y) + halfSize, paint);
			if (isUnlocked == true) {
				canvas.drawPath(starPath, starPaint);
			} else {
				canvas.drawPath(starPath, blackPaint);
			}
			break;
		case BLOCK_EXIT:
			canvas.drawLine(centerX - halfSize, centerY, centerX + halfSize - 2, centerY, paint);
			canvas.drawLine(centerX + halfSize - 2, centerY, centerX + halfSize - (halfSize / 2), centerY - (halfSize / 3), paint);
			canvas.drawLine(centerX + halfSize - 2, centerY, centerX + halfSize - (halfSize / 2), centerY + (halfSize / 3), paint);
			break;
		}
		
		
	}

}
