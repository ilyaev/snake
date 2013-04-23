package pbartz.games.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class SnakeBug {

	int cellX;
	int cellY;
	
	int eatenBy = 0;
	
	List<Point> starE;
	List<Point> starI;
	
	Path starPath = null;
 	
	static final int BUG_APPLE = 1;
	static final int BUG_CHERRY = 2;
	static final int BUG_FREEZ = 4;
	static final int BUG_TRIPPLE = 3;
	static final int BUG_MUSHROOM = 6;
	static final int BUG_SCORE = 5;
	
	int type = BUG_APPLE;
	
	int race;
	int active = 1;
	
	float leftX = 0;
	float topY = 0;
	
	float centerX = 0;
	float centerY = 0;
	
	boolean inited = false;
	
	int iteration = 30;
	int maxIterations = 30;
	
	Paint blackPaint, starPaint;
	
	float x1,x2,x3,y1,y2,y3;
	float nx1,nx2,nx3,ny1, ny2, ny3;
	float sx1,sx2,sx3,sy1, sy2, sy3;
	float cellSize = 1;
	
	int transparency = 0;
	Paint whitePaint, redPaint;
	
	public SnakeBug(int cX, int cY) {
		cellX = cX;
		cellY = cY;
		blackPaint = new Paint();
		blackPaint.setColor(Color.BLACK);
		
		starPaint = new Paint();
		starPaint.setARGB(255, 255, 215, 0);
		
		whitePaint = new Paint();
		whitePaint.setARGB(255, 255, 255, 255);
		
		redPaint = new Paint();
		redPaint.setARGB(255, 255, 0, 0);
	}
	
	public void setRace(int bRace) {
		race = bRace;
	}
	
	public void calculate() {
		if (inited) {
			if (transparency < 255) {
				transparency += (int) (255 / 60);
				if (transparency > 255) {
					transparency = 255;
				}
				
			}
			if (type == BUG_TRIPPLE || type == BUG_CHERRY) {
				if (iteration == maxIterations) {
					generateNext();
					iteration = 0;
				}
				x1 += sx1;
				x2 += sx2;
				x3 += sx3;
				y1 += sy1;
				y2 += sy2;
				y3 += sy3;
				iteration += 1;
			} else if (type == BUG_SCORE) {
				if (iteration == maxIterations) {
					iteration = 0;
				}
				buildPath();
				iteration += 1;
			}
		}
	}
	
	private void buildPath() {
		starE = new ArrayList<Point>();
		starI = new ArrayList<Point>();
		
		starPath = new Path();
		
		double angle = 2*(Math.PI/5);
		double shift = (2*(Math.PI/5) / maxIterations) * iteration;	
		
		
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

	private void initStar() {
		buildPath();		
	}

	private void initialize() {
		Random r = new Random();
		x1 = r.nextInt((int)cellSize);
		x2 = r.nextInt((int)cellSize);
		x3 = r.nextInt((int)cellSize);
		y1 = r.nextInt((int)cellSize);
		y2 = r.nextInt((int)cellSize);
		y3 = r.nextInt((int)cellSize);
		
		inited = true;
		
		if (type == BUG_SCORE) {
			initStar();
		}
	}
	
	public void generateNext() {
		Random r = new Random();
		nx1 = r.nextInt((int)cellSize);
		nx2 = r.nextInt((int)cellSize);
		nx3 = r.nextInt((int)cellSize);
		ny1 = r.nextInt((int)cellSize);
		ny2 = r.nextInt((int)cellSize);
		ny3 = r.nextInt((int)cellSize);
		
		sx1 = (nx1 - x1) / maxIterations;
		sx2 = (nx2 - x2) / maxIterations;
		
		if (type == BUG_TRIPPLE) {
			sx3 = (nx3 - x3) / maxIterations;
			sy3 = (ny3 - y3) / maxIterations;
		}
		
		sy1 = (ny1 - y1) / maxIterations;
		sy2 = (ny2 - y2) / maxIterations;
		
	}

	public void draw(Canvas canvas, float cellCenterX, float cellCenterY, float cellSizePx, Paint tPaint) {
		
		Paint paint;
		
		if (transparency < 255) {
			paint = new Paint(tPaint);
			paint.setAlpha(transparency);
			starPaint.setAlpha(transparency);
			whitePaint.setAlpha(transparency);
			redPaint.setAlpha(transparency);
		} else {
			paint = tPaint;
		}
		
		cellSize = cellSizePx;
		centerX = cellCenterX;
		centerY = cellCenterY;
		
		if (!inited) {
			leftX = cellCenterX - cellSizePx / 2;
			topY = cellCenterY - cellSizePx / 2;
			initialize();
		}
		
		switch (type) {
		case BUG_APPLE:
				canvas.drawCircle(cellCenterX, cellCenterY, cellSizePx / 2, paint);
				break;
			case BUG_TRIPPLE:			
				canvas.drawCircle(leftX + x1, topY + y1, cellSizePx / 5, paint);
				canvas.drawCircle(leftX + x2, topY + y2, cellSizePx / 5, paint);
				canvas.drawCircle(leftX + x3, topY + y3, cellSizePx / 5, paint);
				break;
			case BUG_CHERRY:
				canvas.drawCircle(leftX + x1, topY + y1, cellSizePx / 4, paint);
				canvas.drawCircle(leftX + x2, topY + y2, cellSizePx / 4, paint);
				
				canvas.drawLine(leftX + x1, topY + y1, leftX + x3, topY + y3, paint);
				canvas.drawLine(leftX + x2, topY + y2, leftX + x3, topY + y3, paint);
				break;
			case BUG_SCORE:
				if (starPath != null) {
					canvas.drawPath(starPath, starPaint);
					canvas.drawCircle(centerX, centerY, (float) (cellSize / 4.5), blackPaint);
				}
				break;
			case BUG_MUSHROOM:
				canvas.drawCircle(cellCenterX, cellCenterY, cellSizePx / 2, redPaint);
				canvas.drawRect(leftX, topY + cellSizePx / 2, leftX + cellSizePx, topY + cellSizePx, blackPaint);
				canvas.drawRect(cellCenterX - cellSizePx / 4, topY + cellSizePx / 2, cellCenterX + cellSizePx / 4, topY + cellSizePx, whitePaint);
				canvas.drawCircle(leftX + (float)(cellSizePx / 4.5), topY + cellSizePx / 4, 2, whitePaint);
				canvas.drawCircle(leftX + (float)((cellSizePx / 4.5)*2.8), topY + (float)(cellSizePx / 5), 2, whitePaint);
				canvas.drawCircle(leftX + (float)((cellSizePx / 4.5)*3.7), topY + (float)(cellSizePx / 3.5), 2, whitePaint);
				break;
			default:
				break;
		}
		
	}

}
