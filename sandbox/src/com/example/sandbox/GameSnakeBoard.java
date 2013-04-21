package com.example.sandbox;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.MotionEvent;

public class GameSnakeBoard extends SnakeBoard {
	
	final static int MIN_CELLS_NUMBER = 20;

	float x1Left, x2Left, y1Left, y2Left, y3Left;
	float x1Right, x2Right, y1Right, y2Right, y3Right;
	float x1Up, x2Up, x3Up, y1Up, y2Up;
	float x1Down, x2Down, x3Down, y1Down, y2Down;
	
	long lastSnakeSpawn = 0;
	
	int btnLeftState, btnRightState, btnUpState, btnDownState = -1;
	
	Path bPathLeft, bPathRight, bPathUp, bPathDown;
	
	Rect btnLeft;
	Rect btnRight;
	Rect btnUp;
	Rect btnDown;
	
	Paint textPaint;
	Paint scorePaint;
	
	Button btnReplay;
	Button btnMenu;
	
	Paint fillPaint;
	
	Rect mBounds;
	
	
	int score = 0;
	int tailLength = 0;
	int killsMade = 0;
	
	int spHeight = 30;
	
	Paint funnyTextPaint;

	public GameSnakeBoard(SnakeBoxSurface tSurface) {
		surface = tSurface;
		bugs = new SnakeBugList(this);
		snakes = new ArrayList<Snake>();
		
		quote = new Quote();
		
		textPaint = new Paint();
		textPaint.setARGB(255, 255, 255, 255);
		textPaint.setTextSize(spHeight - 2);
		
		scorePaint = new Paint();
		scorePaint.setARGB(255, 255, 255, 255);
		scorePaint.setTextSize(80);
		
		btnReplay = new Button("AGAIN");
		btnMenu = new Button("MENU");	
		
		fillPaint = new Paint();
		fillPaint.setARGB(170, 0,0,0);
		
		mBounds = new Rect();
		
		walls = new BlockList(this);
	}
	
	public void drawMultilineText(String str, int x, int y, Paint paint, Canvas canvas) {
	    int      lineHeight = 0;
	    int      yoffset    = 0;
	    String[] lines      = str.split("\n");

	    // set height of each line (height of text + 20%)
	    paint.getTextBounds("Ig", 0, 2, mBounds);
	    lineHeight = (int) ((float) mBounds.height() * 1.2);
	    // draw each line
	    for (int i = 0; i < lines.length; ++i) {
	        canvas.drawText(lines[i], x, y + yoffset, paint);
	        yoffset = yoffset + lineHeight;
	    }
	}

	public void draw(Canvas tCanvas) {
		canvas = tCanvas;		
		canvas.drawRGB(0, 0, 0);
		
		if (gameOver != 1) {
			drawControlPanel();
			drawScorePanel();
		}		
		
		
		for(int i = 0 ; i < snakes.size() ; i++) {
			snakes.get(i).draw();
		}
		
		if (booms != null) {
			for(int i = 0 ; i < booms.size() ; i++) {
				booms.get(i).draw(tCanvas);
			}
		}
		
		bugs.draw(canvas);
		
		walls.draw(canvas);
		
		if (gameOver == 1) {
			drawGameOver();
		}
	}
	
	private void drawGameOver() {
		if (System.currentTimeMillis() - gameOverCountdown > 1000) {		
			int pWidth = (int)(sWidth - 20);
			int pHeight = 280;
			
			Rect panel = new Rect((sWidth - pWidth) / 2, (sHeight - pHeight - 100) / 2, (sWidth - pWidth) / 2 + pWidth, (sHeight - pHeight  - 100) / 2 + pHeight);
			canvas.drawRect(panel, fillPaint);
	
			Rect bounds = new Rect();
			textPaint.getTextBounds("Your Score:", 0, 9, bounds);
			canvas.drawText("Your Score:", panel.left + (panel.width() - bounds.width()) / 2, panel.top + 30, textPaint);
			
			String scoreStr = Integer.toString(getScore());
			
			Rect scoreBounds = new Rect();
			scorePaint.getTextBounds(scoreStr, 0, scoreStr.length(), scoreBounds);
			
			canvas.drawText(scoreStr, panel.left + (panel.width() - scoreBounds.width()) / 2, panel.top + 30 + scorePaint.getTextSize() + 5, scorePaint);
			
			if (System.currentTimeMillis() - gameOverCountdown > 2000) {
				drawMultilineText(funnyText, (int)(panel.left + 2), (int)(panel.top + 30 + scorePaint.getTextSize() + 50 + 5), funnyTextPaint, canvas);
			}		
			
			if (System.currentTimeMillis() - gameOverCountdown > 3000) {
				btnReplay.draw(canvas);
				btnMenu.draw(canvas);
			}
		}
	}
	
	private int getScore() {
		return (snakes.get(0).body.items.size() - 1) * 10 + snakes.get(0).score;
	}

	private void drawScorePanel() {
		canvas.drawText("Score: " + Integer.toString(getScore()), 0, spHeight, textPaint);
		canvas.drawText("Tail: " + Integer.toString(snakes.get(0).body.items.size() - 1), 150, spHeight, textPaint);
		canvas.drawText("Kills: " + Integer.toString(snakes.get(0).kills), 270, spHeight, textPaint);
	}
	
	private void drawControlPanel() {		
		canvas.drawRect(btnLeft, whiteFramePaint);
		canvas.drawRect(btnRight, whiteFramePaint);
		
		canvas.drawRect(btnUp, whiteFramePaint);
		canvas.drawRect(btnDown, whiteFramePaint);	
		
		
		if (btnLeftState == MotionEvent.ACTION_DOWN || btnLeftState == MotionEvent.ACTION_MOVE) {
			canvas.drawPath(bPathLeft, buttonPressedPaint);
		} else {
			canvas.drawPath(bPathLeft, whiteFramePaint);
		}
		
		if (btnRightState == MotionEvent.ACTION_DOWN || btnRightState == MotionEvent.ACTION_MOVE) {
			canvas.drawPath(bPathRight, buttonPressedPaint);
		} else {
			canvas.drawPath(bPathRight, whiteFramePaint);
		}
		
		if (btnUpState == MotionEvent.ACTION_DOWN || btnUpState == MotionEvent.ACTION_MOVE) {
			canvas.drawPath(bPathUp, buttonPressedPaint);
		} else {
			canvas.drawPath(bPathUp, whiteFramePaint);
		}
		
		if (btnDownState == MotionEvent.ACTION_DOWN || btnDownState == MotionEvent.ACTION_MOVE) {
			canvas.drawPath(bPathDown, buttonPressedPaint);
		} else {
			canvas.drawPath(bPathDown, whiteFramePaint);
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
		
		bugs.calculate();
		
		if (booms != null) {
			for(int i = 0 ; i < booms.size() ; i++) {
				booms.get(i).calculate();
			}
		}
		
		if (freezeStartTime != 0) {
			if (System.currentTimeMillis() - freezeStartTime  > 5000) {
				unFreezeSnakes();
			}
		}
		
		if (gameMode == GAMEMODE_SURVIVAL &&  System.currentTimeMillis() - lastSnakeSpawn > 10000 && snakes.size() < 10) {
			Random r = new Random();
			int y = r.nextInt(cnVertical - 1) + 1;
			if (!oMap[1][y]) {
				
				purgeSnakes();
				
				Snake nSnake = new Snake(1, y, this);
				nSnake.race = r.nextInt(1000) + 10;
				nSnake.live = 1;
				bugs.spawnBug(nSnake.race);
				nSnake.body.grow(SnakeBug.BUG_TRIPPLE);
				snakes.add(nSnake);
				lastSnakeSpawn = System.currentTimeMillis();
			}
		}
		
	}

	public void purgeSnakes() {

	}

	private void initialize() {
		sWidth = canvas.getWidth();
		sHeight = canvas.getHeight();
		
		btnReplay.setPosition(1, sHeight - 105);
		btnReplay.setSize(sWidth / 2 - 5, 100);		
		btnReplay.setFontSize(40);
		
		btnMenu.setPosition(sWidth / 2 + 5, sHeight - 105);
		btnMenu.setSize(sWidth / 2 - 10, 100);
		btnMenu.setFontSize(40);
		
		funnyTextPaint = new Paint();
		funnyTextPaint = new Paint();
		funnyTextPaint.setARGB(255, 255, 255, 255);
		funnyTextPaint.setTextSize(25);		
		
		
		snakes.clear();
		bugs.bugs.clear();
		walls.items.clear();
		
		gameOver = 0;
		
		cnHorizontal = MIN_CELLS_NUMBER;
		
		if (sHeight > sWidth) {
			cellSizePx = sWidth / cnHorizontal;
			cnVertical = (int) ((sHeight - cpHeight - spHeight) / cellSizePx);
		} else {
			cnVertical = MIN_CELLS_NUMBER;
			cellSizePx = sHeight / cnVertical;
			cnHorizontal = (int) ((sWidth - cpHeight - spHeight) / cellSizePx);
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
		
		buttonPressedPaint = new Paint();
		buttonPressedPaint.setARGB(255, 220,220,220);
		
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
		
		initControlButtons();
		
		
		switch (gameMode) {
			case GAMEMODE_SOLO:
				startGameSolo();
				break;
			case GAMEMODE_BATTLE:
				startGameBattle();
				break;
			case GAMEMODE_SURVIVAL:
				startGameSurvival();
				break;
			default:
				break;
				
		}
		
		rebuildObstMap();	
	}	
	
	private void initControlButtons() {
		x1Left = btnLeft.width() / 5;
		x2Left = (btnLeft.width() / 5) * 4;
		
		y1Left = btnLeft.height() / 6;
		y2Left = (btnLeft.height() / 6) * 5;
		y3Left = btnLeft.height() / 2;
		
		bPathLeft = new Path();
		bPathLeft.moveTo(btnLeft.left + x1Left, btnLeft.top + y3Left);
		bPathLeft.lineTo(btnLeft.left + x2Left,  btnLeft.top + y1Left);
		bPathLeft.lineTo(btnLeft.left + x2Left,  btnLeft.top + y2Left);
		bPathLeft.lineTo(btnLeft.left + x1Left,  btnLeft.top + y3Left);
		
		x1Right = (btnRight.width() / 5) * 4;
		x2Right = btnRight.width() / 5;
		
		y2Right = btnRight.height() / 6;
		y1Right = (btnRight.height() / 6) * 5;
		y3Right = btnRight.height() / 2;
		
		bPathRight = new Path();
		bPathRight.moveTo(btnRight.left + x1Right, btnRight.top + y3Right);
		bPathRight.lineTo(btnRight.left + x2Right,  btnRight.top + y1Right);
		bPathRight.lineTo(btnRight.left + x2Right,  btnRight.top + y2Right);
		bPathRight.lineTo(btnRight.left + x1Right,  btnRight.top + y3Right);
		
		x1Up = btnUp.width() / 8;
		x2Up = (btnUp.width() / 8) * 7;
		
		y1Up = (btnUp.height() / 4) * 3;
		y2Up = btnUp.height() / 4;
		x3Up = btnUp.width() / 2;
		
		bPathUp = new Path();
		bPathUp.moveTo(btnUp.left + x1Up, btnUp.top + y1Up);
		bPathUp.lineTo(btnUp.left + x3Up,  btnUp.top + y2Up);
		bPathUp.lineTo(btnUp.left + x2Up,  btnUp.top + y1Up);
		bPathUp.lineTo(btnUp.left + x1Up,  btnUp.top + y1Up);
		
		
		x1Down = btnDown.width() / 8;
		x2Down = (btnDown.width() / 8) * 7;
		
		y2Down = (btnDown.height() / 4) * 3;
		y1Down = btnDown.height() / 4;
		x3Down = btnDown.width() / 2;
		
		bPathDown = new Path();
		bPathDown.moveTo(btnDown.left + x1Down, btnDown.top + y1Down);
		bPathDown.lineTo(btnDown.left + x3Down,  btnDown.top + y2Down);
		bPathDown.lineTo(btnDown.left + x2Down,  btnDown.top + y1Down);
		bPathDown.lineTo(btnDown.left + x1Down,  btnDown.top + y1Down);		
		
		
		btnLeftState = -1;
		btnRightState = -1;
		btnUpState = -1;
		btnDownState = -1;
	}

	private void startGameSolo() {
		bugs.spawnBug(Snake.RACE_PLAYER);
		snakes.add(new Snake(Math.round(cnHorizontal / 2), Math.round(cnVertical / 2), this));
		snakes.get(0).body.grow(SnakeBug.BUG_TRIPPLE);
		snakes.get(0).live = 1;		
	}
	
	private void startGameBattle() {
		bugs.spawnBug(Snake.RACE_PLAYER);
		bugs.spawnBug(Snake.RACE_ENEMY1);
		bugs.spawnBug(Snake.RACE_ENEMY2);
		bugs.spawnBug(Snake.RACE_ENEMY3);
		
		snakes.add(new Snake(Math.round(cnHorizontal / 2), Math.round(cnVertical / 2), this));
		snakes.get(0).body.grow(SnakeBug.BUG_TRIPPLE);
		snakes.get(0).currentCmd = Snake.CMD_UP;
		snakes.get(0).live = 1;
		
		Snake foeSnake = new Snake(5,10, this);
		foeSnake.race = Snake.RACE_ENEMY1;
		foeSnake.live = 1;
		
		snakes.add(foeSnake);
		snakes.get(1).body.grow(SnakeBug.BUG_TRIPPLE);
		
		Snake foeSnake2 = new Snake(15,15, this);
		foeSnake2.race = Snake.RACE_ENEMY2;
		foeSnake2.live = 1;
		
		snakes.add(foeSnake2);
		snakes.get(2).body.grow(SnakeBug.BUG_TRIPPLE);
		
		Snake foeSnake3 = new Snake(5,5, this);
		foeSnake3.race = Snake.RACE_ENEMY3;
		foeSnake3.live = 1;
		
		snakes.add(foeSnake3);
		snakes.get(3).body.grow(SnakeBug.BUG_TRIPPLE);
		
		int yMid = (int)(cnVertical / 2);
		
		walls.addBlock(cnHorizontal, yMid, Block.BLOCK_KEYHOLE);
		walls.addBlock(cnHorizontal, yMid - 1, Block.BLOCK_KEYHOLE);
		walls.addBlock(cnHorizontal, yMid + 1, Block.BLOCK_KEYHOLE);
		walls.addBlock(cnHorizontal, yMid - 2, Block.BLOCK_WALL);
		walls.addBlock(cnHorizontal, yMid + 2, Block.BLOCK_WALL);		
	}
	
	private void startGameSurvival() {
		
		lastSnakeSpawn = System.currentTimeMillis();
		
		bugs.spawnBug(Snake.RACE_PLAYER);
		bugs.spawnBug(Snake.RACE_ENEMY1);
		
		snakes.add(new Snake(Math.round(cnHorizontal / 2), Math.round(cnVertical / 2), this));
		snakes.get(0).body.grow(SnakeBug.BUG_TRIPPLE);
		snakes.get(0).currentCmd = Snake.CMD_UP;
		snakes.get(0).live = 1;
		
		Snake foeSnake = new Snake(3,10, this);
		foeSnake.race = Snake.RACE_ENEMY1;
		foeSnake.live = 1;
		
		snakes.add(foeSnake);
		snakes.get(1).body.grow(SnakeBug.BUG_TRIPPLE);
	}

	public void processTouch(MotionEvent event) {
		if (gameOver == 1 && event.getAction() == MotionEvent.ACTION_UP && System.currentTimeMillis() - gameOverCountdown > 2000) {
			if (btnReplay.rect.contains((int)event.getX(), (int)event.getY())) {
				state = SnakeBoard.NOT_INITED;
			} else if (btnMenu.rect.contains((int)event.getX(), (int)event.getY())) {
				surface.setBoard(surface.startBoard);
			};			
		} else {
			
			btnRightState = -1;
			btnUpState = -1;
			btnDownState = -1;
			btnLeftState = -1;
			
			if (btnLeft.contains((int)event.getX(), (int)event.getY()) && snakes.get(0).currentCmd != Snake.CMD_RIGHT) {
				btnLeftState = event.getAction();
				snakes.get(0).setCommand(Snake.CMD_LEFT);
			} else if (btnRight.contains((int)event.getX(), (int)event.getY()) && snakes.get(0).currentCmd != Snake.CMD_LEFT) {
				btnRightState = event.getAction();
				snakes.get(0).setCommand(Snake.CMD_RIGHT);
			} else if (btnUp.contains((int)event.getX(), (int)event.getY()) && snakes.get(0).currentCmd != Snake.CMD_DOWN) {
				btnUpState = event.getAction();
				snakes.get(0).setCommand(Snake.CMD_UP);
			} else if (btnDown.contains((int)event.getX(), (int)event.getY()) && snakes.get(0).currentCmd != Snake.CMD_UP) {
				btnDownState = event.getAction();
				snakes.get(0).setCommand(Snake.CMD_DOWN);
			}
		}
	}
	
	public float getCellCenterX(int x) {
		return (x - 1) * cellSizePx + (cellSizePx / 2);
	}
	
	public float getCellCenterY(int y) {
		return spHeight + (y - 1) * cellSizePx + (cellSizePx / 2);
	}
	
}
