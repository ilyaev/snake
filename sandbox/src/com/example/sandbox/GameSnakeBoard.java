package com.example.sandbox;

import java.util.ArrayList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class GameSnakeBoard extends SnakeBoard {
	
	final static int MIN_CELLS_NUMBER = 20;

	SnakeBoxSurface surface;
	
	Rect btnLeft;
	Rect btnRight;
	Rect btnUp;
	Rect btnDown;	

	public GameSnakeBoard(SnakeBoxSurface tSurface) {
		surface = tSurface;
		bugs = new SnakeBugList(this);
		snakes = new ArrayList<Snake>();
	}

	public void draw(Canvas tCanvas) {
		canvas = tCanvas;		
		canvas.drawRGB(0, 0, 0);
		
		drawControlPanel();
		
		for(int i = 0 ; i < snakes.size() ; i++) {
			snakes.get(i).draw();
		}
		
		bugs.draw(canvas);
	}
	
	private void drawControlPanel() {		
		canvas.drawRect(btnLeft, whiteFramePaint);
		canvas.drawRect(btnRight, whiteFramePaint);
		
		canvas.drawRect(btnUp, whiteFramePaint);
		canvas.drawRect(btnDown, whiteFramePaint);
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
	
	@SuppressWarnings("unused")
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
			
			for(int i = 0 ; i < snakes.size() ; i++) {
				snakes.get(i).calculate();
				rebuildObstMap();
			}			
			
			lastTimeStamp += timeDiff;
			
			bugs.processIntercection();
		}
		
		for(int i = 0 ; i < snakes.size() ; i++) {
			snakes.get(i).body.calculate();
		}
		
	}

	private void initialize() {
		sWidth = canvas.getWidth();
		sHeight = canvas.getHeight();
		
		cnHorizontal = MIN_CELLS_NUMBER;
		
		if (sHeight > sWidth) {
			cellSizePx = sWidth / cnHorizontal;
			cnVertical = (int) ((sHeight - cpHeight) / cellSizePx);
		} else {
			cnVertical = MIN_CELLS_NUMBER;
			cellSizePx = sHeight / cnVertical;
			cnHorizontal = (int) ((sWidth - cpHeight) / cellSizePx);
		}
		
		grayPaint = new Paint();
		grayPaint.setARGB(255, 125, 125, 125);
		
		whiteFramePaint = new Paint();
		whiteFramePaint.setARGB(255, 255, 255, 255);
		whiteFramePaint.setStyle(Paint.Style.STROKE);
		
		orangePaint = new Paint();
		orangePaint.setARGB(255, 226, 139, 0);
		
		greenPaint = new Paint();
		greenPaint.setARGB(255, 0, 255, 0);
		
		darkGreenPaint = new Paint();
		darkGreenPaint.setARGB(255, 0, 125, 0);
		
		state = INITED;
		
		lastTimeStamp = 0;		
		
		// control panel
		int btnWidth = (int) sWidth / 3;

		btnLeft = new Rect(0, sHeight - cpHeight, btnWidth, sHeight - 1);
		btnRight = new Rect(sWidth - btnWidth, sHeight - cpHeight, sWidth - 1, sHeight - 1);
		
		btnUp = new Rect(btnWidth, sHeight - cpHeight, btnWidth + btnWidth, (int) (sHeight - cpHeight / 2));
		btnDown = new Rect(btnWidth, sHeight - (int) (cpHeight / 2), btnWidth + btnWidth, sHeight - 1);
		
		oMap = new boolean[cnHorizontal + 1][cnVertical + 1];
		
		
		switch (gameMode) {
			case GAMEMODE_SOLO:
				startGameSolo();
				break;
			case GAMEMODE_BATTLE:
				startGameBattle();
				break;
			case GAMEMODE_SURVIVAL:
				break;
			default:
				break;
				
		}
		
		rebuildObstMap();	
	}	
	
	private void startGameSolo() {
		bugs.spawnBug(Snake.RACE_PLAYER);
		snakes.add(new Snake(Math.round(cnHorizontal / 2), Math.round(cnVertical / 2), this));
	}
	
	private void startGameBattle() {
		bugs.spawnBug(Snake.RACE_PLAYER);
		bugs.spawnBug(Snake.RACE_ENEMY1);
		bugs.spawnBug(Snake.RACE_ENEMY2);
		bugs.spawnBug(Snake.RACE_ENEMY3);
		
		snakes.add(new Snake(Math.round(cnHorizontal / 2), Math.round(cnVertical / 2), this));
		
		Snake foeSnake = new Snake(10,10, this);
		foeSnake.race = Snake.RACE_ENEMY1;
		
		snakes.add(foeSnake);
		
		Snake foeSnake2 = new Snake(15,15, this);
		foeSnake2.race = Snake.RACE_ENEMY2;
		
		snakes.add(foeSnake2);
		
		Snake foeSnake3 = new Snake(5,5, this);
		foeSnake3.race = Snake.RACE_ENEMY3;
		
		snakes.add(foeSnake3);
	}

	public void processTouch(MotionEvent event) {
		if (btnLeft.contains((int)event.getX(), (int)event.getY())) {
			snakes.get(0).setCommand(Snake.CMD_LEFT);
		} else if (btnRight.contains((int)event.getX(), (int)event.getY())) {
			snakes.get(0).setCommand(Snake.CMD_RIGHT);
		} else if (btnUp.contains((int)event.getX(), (int)event.getY())) {
			snakes.get(0).setCommand(Snake.CMD_UP);
		} else if (btnDown.contains((int)event.getX(), (int)event.getY())) {
			snakes.get(0).setCommand(Snake.CMD_DOWN);
		}
	}
	
}
