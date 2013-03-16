package com.example.sandbox;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Snake {
	
	int sWidth;
	int sHeight;
	
	final static int MIN_CELLS_NUMBER = 15;
	
	final static int CMD_LEFT = 1;
	final static int CMD_RIGHT = 2;
	final static int CMD_UP = 3;
	final static int CMD_DOWN = 4;
	
	int currentCmd = 0;
	int defaultSpeed = 200;
	
	int cpHeight = 200;
	
	int cnHorizontal = MIN_CELLS_NUMBER;
	int cnVertical = 0;
	
	float cellSizePx = 0;
	
	final static int NOT_INITED	= 0;
	final static int INITED 	= 1;
	final static int IS_MOVING 	= 2;
	
	int snakeX = 0;
	int snakeY = 0;
	
	int timePerStep = 1000;
	
	int state = NOT_INITED;
	
	Canvas canvas;
	Paint grayPaint;
	Paint whiteFramePaint;
	Paint orangePaint;
	
	Rect btnLeft;
	Rect btnRight;
	Rect btnUp;
	Rect btnDown;
	
	long lastTimeStamp = 0;

	public Snake() {
		
	}

	public void draw(Canvas tCanvas) {
		canvas = tCanvas;
		
		canvas.drawRGB(0, 0, 0);
		
		drawHelpers();
		//drawDebug();
		
		drawControlPanel();
		drawHead();
	}
	
	private void drawControlPanel() {		
		canvas.drawRect(btnLeft, whiteFramePaint);
		canvas.drawRect(btnRight, whiteFramePaint);
		
		canvas.drawRect(btnUp, whiteFramePaint);
		canvas.drawRect(btnDown, whiteFramePaint);
	}

	private void drawHead() {
		canvas.drawCircle(getCellCenterX(snakeX), getCellCenterY(snakeY), cellSizePx / 2 , orangePaint);
	}

	@SuppressWarnings("unused")
	private void drawDebug() {
		int leftX = 0;
		int topY = 0;
		
		leftX = sWidth / 2;
		topY = 20;
		
		canvas.drawText("Width: " + Integer.toString(sWidth) + " ; Height: "  + Integer.toString(sHeight), leftX, topY, grayPaint);
		topY += 20;
		
		canvas.drawText("CellsX: " + Integer.toString(cnHorizontal) + " ; CellsY: " + Integer.toString(cnVertical), leftX, topY, grayPaint);
	}
	
	private void drawHelpers() {
		for(int x = 0 ; x < cnHorizontal + 1 ; x++) {
			canvas.drawLine(x * cellSizePx, 0 , x * cellSizePx, sHeight, grayPaint);
		}
		
		for(int y = 0 ; y < cnVertical + 1 ; y++) {
			canvas.drawLine(0, y * cellSizePx, sWidth, y * cellSizePx, grayPaint);
		}		
	}

	public void calculate(Canvas tCanvas) {
		canvas = tCanvas;
		
		long timeDiff = System.currentTimeMillis() - lastTimeStamp;
		
		
		
		if (state == NOT_INITED) {
			initialize();
		}
		
		if (timeDiff >= defaultSpeed) {
			if (currentCmd == CMD_LEFT) {
				snakeX -= 1;
			} else if (currentCmd == CMD_RIGHT) {
				snakeX += 1;
			} else if (currentCmd == CMD_UP) {
				snakeY -= 1;
			} else if (currentCmd == CMD_DOWN) {
				snakeY += 1;
			}
			lastTimeStamp += timeDiff;
		}
		
		//Log.v("Snake", Integer.toString(currentCmd)+ " ; diff = " + Long.toString(timeDiff) + " ; curr = " + Long.toString(System.currentTimeMillis()) + " ; last = " + Long.toString(lastTimeStamp));
		
		if (snakeX < 1) {
			snakeX = cnHorizontal;
		} else if (snakeX > cnHorizontal) {
			snakeX = 1;
		}
		
		if (snakeY < 1) {
			snakeY = cnVertical;
		} else if (snakeY > cnVertical) {
			snakeY = 1;
		}		
	}
	
	public void setCommand(int cmd) {
		currentCmd = cmd;
		if (cmd > 0) {
			state = IS_MOVING;
		}
	}

	private void initialize() {
		sWidth = canvas.getWidth();
		sHeight = canvas.getHeight();
		
		if (sHeight > sWidth) {
			cellSizePx = sWidth / cnHorizontal;
			cnVertical = (int) ((sHeight - cpHeight) / cellSizePx);
		} else {
			cnVertical = MIN_CELLS_NUMBER;
			cellSizePx = sHeight / cnVertical;
			cnHorizontal = (int) ((sWidth - cpHeight) / cellSizePx);
		}
		
		snakeX = Math.round(cnHorizontal / 2);
		snakeY = Math.round(cnVertical / 2);
		
		grayPaint = new Paint();
		grayPaint.setARGB(255, 125, 125, 125);
		
		whiteFramePaint = new Paint();
		whiteFramePaint.setARGB(255, 255, 255, 255);
		whiteFramePaint.setStyle(Paint.Style.STROKE);
		
		orangePaint = new Paint();
		orangePaint.setARGB(255, 226, 139, 0);		
		
		state = INITED;
		
		lastTimeStamp = 0;
		
		
		// control panel
		int btnWidth = (int) sWidth / 3;

		btnLeft = new Rect(0, sHeight - cpHeight, btnWidth, sHeight - 1);
		btnRight = new Rect(sWidth - btnWidth, sHeight - cpHeight, sWidth - 1, sHeight - 1);
		
		btnUp = new Rect(btnWidth, sHeight - cpHeight, btnWidth + btnWidth, (int) (sHeight - cpHeight / 2));
		btnDown = new Rect(btnWidth, sHeight - (int) (cpHeight / 2), btnWidth + btnWidth, sHeight - 1);
	}
	
	private float getCellCenterX(int x) {
		return (x - 1) * cellSizePx + (cellSizePx / 2);
	}
	
	private float getCellCenterY(int y) {
		return (y - 1) * cellSizePx + (cellSizePx / 2);
	}
	
	public void processTouch(MotionEvent event) {
		if (btnLeft.contains((int)event.getX(), (int)event.getY())) {
			setCommand(CMD_LEFT);
		} else if (btnRight.contains((int)event.getX(), (int)event.getY())) {
			setCommand(CMD_RIGHT);
		} else if (btnUp.contains((int)event.getX(), (int)event.getY())) {
			setCommand(CMD_UP);
		} else if (btnDown.contains((int)event.getX(), (int)event.getY())) {
			setCommand(CMD_DOWN);
		}
	}
	
}
