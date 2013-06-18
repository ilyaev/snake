package pbartz.games.snake;

import java.util.ArrayList;
import java.util.Random;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
import android.view.MotionEvent;

public class GameSnakeBoard extends SnakeBoard {
	
	final static int MIN_CELLS_NUMBER = 20;

	float x1Left, x2Left, y1Left, y2Left, y3Left;
	float x1Right, x2Right, y1Right, y2Right, y3Right;
	float x1Up, x2Up, x3Up, y1Up, y2Up;
	float x1Down, x2Down, x3Down, y1Down, y2Down;
	
	long lastSnakeSpawn = 0;
	
	float touchX = -1;
	float touchY = -1;
	
	int btnLeftStateR, btnRightStateR, btnLeftState, btnRightState, btnUpState, btnDownState = -1;
	
	Path bPathLeft, bPathRight, bPathUp, bPathDown, bPathLeftR, bPathRightR;
	
	Rect btnLeft, btnLeftR;
	Rect btnRight, btnRightR;
	Rect btnUp;
	Rect btnDown;
	
	Rect fieldRect, controlRect;
	
	Paint textPaint;
	Paint scorePaint;
	
	Button btnReplay, btnNext, btnMenu, btnControl;
	
	Paint fillPaint;
	
	Rect mBounds;
	
	
	int score = 0;
	int tailLength = 0;
	int killsMade = 0;
	
	int spHeight = 0;
	
	Paint funnyTextPaint;

	private Button btnRate;

	private Button btnResume;

	public GameSnakeBoard(SnakeBoxSurface tSurface) {
	
		surface = tSurface;
		bugs = new SnakeBugList(this);
		snakes = new ArrayList<Snake>();
		
		quote = new Quote();
		
		textPaint = new Paint();
		textPaint.setARGB(255, 255, 255, 255);
		textPaint.setTypeface(tSurface.mFace);
		
		scorePaint = new Paint();
		scorePaint.setARGB(255, 255, 255, 255);		
		
		btnReplay = new Button("AGAIN", tSurface.mFace);
		btnControl = new Button("CONTROL: RELATIVE", tSurface.mFace);
		btnMenu = new Button("MENU", tSurface.mFace);	
		btnNext = new Button("NEXT", tSurface.mFace);
		
		btnRate = new Button("RATE APP", tSurface.mFace);
		
		btnResume = new Button("RESUME", tSurface.mFace);
		
		fillPaint = new Paint();
		fillPaint.setARGB(170, 0,0,0);
		
		mBounds = new Rect();
		
		walls = new BlockList(this);
	}
	
	public int getActiveRivalsCount() {
		int result = 0;
		for(int i = 0 ; i < snakes.size() ; i++) {
			if (snakes.get(i).active == 1 && snakes.get(i).race != Snake.RACE_PLAYER) {
				result += 1;
			}
		}
		return result;
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
		if (tCanvas != null) {
			try {
				canvas = tCanvas;		
				canvas.drawRGB(0, 0, 0);
				
				
				
				if (gameOver != 1) {
					drawControlPanel();
					//drawScorePanel();
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
					isPaused = false;
					drawGameOver();
				} else {
					if (canvas != null) {
						canvas.drawRect(0, 0, sWidth - 1, sHeight - cpHeight, whiteFramePaint);
						drawPause();
					}
				}
				
			} finally {
				
			}
		}
	}
	
	private void drawPause() {
		if (isPaused) {
			btnResume.draw(canvas);
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
				
				Rect hsBounds = new Rect();
				funnyTextPaint.getTextBounds(funnyText, 0, funnyText.length(), hsBounds);
				canvas.drawText(funnyText, panel.left + (panel.width() - hsBounds.width()) / 2, (int)(panel.top + 30 + scorePaint.getTextSize() + 50 + 5), funnyTextPaint);
				//drawMultilineText(funnyText, (int)(panel.left + 2), (int)(panel.top + 30 + scorePaint.getTextSize() + 50 + 5), funnyTextPaint, canvas);
			}		
			
			if (System.currentTimeMillis() - gameOverCountdown > 2000) {
				if (roundWon) {
					btnNext.draw(canvas);
				} else {
					btnReplay.draw(canvas);
				}
				btnMenu.draw(canvas);
			}
			
			if (System.currentTimeMillis() - gameOverCountdown > 5000) {
				btnRate.draw(canvas);
			}
		}
	}
	
	public int getScore() {
		int r = 0;
		try {
			r = (snakes.get(0).body.items.size() - 1) * 10 + snakes.get(0).score;
		} finally {};
		
		return r;
	}

	private void drawScorePanel() {
		String txt = "Score: " + Integer.toString(getScore()) + "     Tail: " + Integer.toString(snakes.get(0).body.items.size() - 1);
		
		if (gameMode == GAMEMODE_BATTLE) {
			txt += "  Level: " + Integer.toString(level) + "/" + Integer.toString(SnakeLevels.levels.length);
		}
		
		canvas.drawText(txt, 0, spHeight, textPaint);
	}
	
	private void drawControlPanel() {		
		if (canvas == null || btnLeft == null || whiteFramePaint == null) {
			// error!
		} else {
			
			if (controlType == CONTROL_TYPE_DPAD) {
			
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
			
			if (controlType == CONTROL_TYPE_RELATIVE) {
				canvas.drawRect(btnLeftR, whiteFramePaint);
				canvas.drawRect(btnRightR, whiteFramePaint);
				
				if (btnLeftStateR == MotionEvent.ACTION_DOWN || btnLeftStateR == MotionEvent.ACTION_MOVE) {
					canvas.drawPath(bPathLeftR, buttonPressedPaint);
				} else {
					canvas.drawPath(bPathLeftR, whiteFramePaint);
				}
				
				if (btnRightStateR == MotionEvent.ACTION_DOWN || btnRightStateR == MotionEvent.ACTION_MOVE) {
					canvas.drawPath(bPathRightR, buttonPressedPaint);
				} else {
					canvas.drawPath(bPathRightR, whiteFramePaint);
				}
			}
			
			if (controlType == CONTROL_TYPE_SWIPE) {
				canvas.drawRect(controlRect, whiteFramePaint);
				if (touchX != -1) {
					canvas.drawCircle(touchX, touchY, sHeight / 16, buttonPressedPaint);
				} else {
					canvas.drawText("SWIPE HERE", 15, controlRect.top + 60, textPaint);
				}
			}
			
		}
	}

	public void calculate(Canvas tCanvas) {
		if (!isPaused) {
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
				
				checkExit();
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
			
			boolean doSpawn = false;
			
			if (gameMode == GAMEMODE_BATTLE && System.currentTimeMillis() - lastSnakeSpawn > 10000) {
				lastSnakeSpawn = System.currentTimeMillis();
				if (getActiveRivalsCount() < 3	&& walls.getUnlockedCount() > getActiveRivalsCount()) {
					doSpawn = true;
				}
			}
			
			if (gameMode == GAMEMODE_SURVIVAL &&  System.currentTimeMillis() - lastSnakeSpawn > 10000 && snakes.size() < 10) {
				doSpawn = true;
			}
			
			if (doSpawn) {
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
			
			walls.calculate();
		}		
	}

	private void checkExit() {
		boolean isExit = false;
		for(int i = 0 ; i < walls.items.size() ; i++) {
			if (walls.items.get(i).type == Block.BLOCK_EXIT && walls.items.get(i).x == snakes.get(0).snakeX && walls.items.get(i).y == snakes.get(0).snakeY) {
				isExit = true;
				break;
			}
		}
	

		if (isExit == true && gameOver == 0) {
			roundWon = true;
			
			if (GameScore.updateScore(gameMode, gameLevel, getScore())) {
				// high score!
				funnyText = " High Score! ";
			} else {
				funnyText = "";
			}
			
			gameLevel += 1;
			level = gameLevel;

			if (level > SnakeLevels.levels.length) {
				// game over real
				level = 1;
			}
			
			gameLevel = level;
			
			if (GameScore.unlockLevel(gameLevel)) {
				// rebuild slider levels
				surface.startBoard.refresh();
			}
			
			GameScore.setSelectedLevel(gameMode, gameLevel);
			
			surface.saveState();
			
			
			snakes.get(0).currentCmd = 0;
			snakes.get(0).live = 0;
			gameOverCountdown = System.currentTimeMillis();
			gameOver = 1;
			for(int i = 0 ; i < snakes.get(0).body.items.size() ; i++) {
				snakes.get(0).body.items.get(i).iteration = 0;
				snakes.get(0).body.items.get(i).maxIterations = 60;
				snakes.get(0).body.items.get(i).doShrink = 1;
			}
		}
	}
	
	private void initialize() {
		isPaused = false;
		sWidth = canvas.getWidth();
		sHeight = canvas.getHeight();
		
		touchX = -1;
		touchY = -1;
		
		roundWon = false;
		
//		sWidth = 320;
//		sHeight = 480;
		
		cpHeight = (int) (sHeight / 4.210);
		spHeight = 0; //(int)(sHeight / 26.66666);
		
		textPaint.setTextSize((int)(sHeight / 26.66666 - 2));
		scorePaint.setTextSize(sHeight / 10);
		
		btnReplay.setPosition(1, sHeight - (int)(sHeight / 7.61));
		btnReplay.setSize(sWidth / 2 - 5, sHeight / 8);		
		btnReplay.setFontSize(sHeight / 20);
		
		btnControl.setPosition(1, sHeight - (int)(sHeight / 7.61));
		btnControl.setSize(sWidth - 1, sHeight / 8);
		btnControl.setFontSize(sHeight / 20);
		
		btnNext.setPosition(1, sHeight - (int)(sHeight / 7.61));
		btnNext.setSize(sWidth / 2 - 5, sHeight / 8);		
		btnNext.setFontSize(sHeight / 20);
		
		btnMenu.setPosition(sWidth / 2 + 5, sHeight - (int)(sHeight / 7.61));
		btnMenu.setSize(sWidth / 2 - 10, sHeight / 8);
		btnMenu.setFontSize(sHeight / 20);
		
		
		btnRate.setPosition((int)(sWidth / 4.8), sHeight - (sHeight / 8)*3);
		btnRate.setSize((int)(sWidth / 1.6 - 5), sHeight / 8);
		btnRate.setFontSize(sHeight / 20);
		
		btnResume.setPosition((int)(sWidth / 4.8), sHeight - (sHeight / 2));
		btnResume.setSize((int)(sWidth / 1.6 - 5), sHeight / 8);
		btnResume.setFontSize(sHeight / 20);
		
		funnyTextPaint = new Paint();
		funnyTextPaint.setARGB(255, 255, 255, 255);
		funnyTextPaint.setTextSize(sHeight / 20);
		funnyTextPaint.setTypeface(surface.mFace);
		
		
		snakes.clear();
		bugs.bugs.clear();
		walls.items.clear();
		walls.reset();
		
		gameOver = 0;
		
		cnHorizontal = MIN_CELLS_NUMBER;
		cellSizePx = sWidth / cnHorizontal;
		cnVertical = 24;
		
//		if (sHeight > sWidth) {
//			cellSizePx = sWidth / cnHorizontal;
//			cnVertical = (int) ((sHeight - cpHeight - spHeight) / cellSizePx);
//		} else {
//			cnVertical = MIN_CELLS_NUMBER;
//			cellSizePx = sHeight / cnVertical;
//			cnHorizontal = (int) ((sWidth - cpHeight - spHeight) / cellSizePx);
//		}
		
		cpHeight = (int)(sHeight - (cnVertical*cellSizePx));
		
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
		
		
		int btnWidthR = (int) sWidth / 2;

		btnLeftR = new Rect(0, sHeight - cpHeight, btnWidthR, sHeight - 1);
		btnRightR = new Rect(sWidth - btnWidthR, sHeight - cpHeight, sWidth - 1, sHeight - 1);
		
		
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
		
		fieldRect = new Rect(0, 0, sWidth - 1, sHeight - cpHeight - ((sHeight - cpHeight) / 100 * 15));
		controlRect = new Rect(0, sHeight - cpHeight, sWidth - 1, sHeight - 1);
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
		
		// relative buttons;
		
		x1Left = btnLeftR.width() / 5;
		x2Left = (btnLeftR.width() / 5) * 4;
		
		y1Left = btnLeftR.height() / 6;
		y2Left = (btnLeftR.height() / 6) * 5;
		y3Left = btnLeftR.height() / 2;
		
		bPathLeftR = new Path();
		bPathLeftR.moveTo(btnLeftR.left + x1Left, btnLeftR.top + y3Left);
		bPathLeftR.lineTo(btnLeftR.left + x2Left,  btnLeftR.top + y1Left);
		bPathLeftR.lineTo(btnLeftR.left + x2Left,  btnLeftR.top + y2Left);
		bPathLeftR.lineTo(btnLeftR.left + x1Left,  btnLeftR.top + y3Left);
		
		x1Right = (btnRightR.width() / 5) * 4;
		x2Right = btnRightR.width() / 5;
		
		y2Right = btnRightR.height() / 6;
		y1Right = (btnRightR.height() / 6) * 5;
		y3Right = btnRightR.height() / 2;
		
		bPathRightR = new Path();
		bPathRightR.moveTo(btnRightR.left + x1Right, btnRightR.top + y3Right);
		bPathRightR.lineTo(btnRightR.left + x2Right,  btnRightR.top + y1Right);
		bPathRightR.lineTo(btnRightR.left + x2Right,  btnRightR.top + y2Right);
		bPathRightR.lineTo(btnRightR.left + x1Right,  btnRightR.top + y3Right);
		
		btnLeftState = -1;
		btnRightState = -1;
		btnLeftStateR = -1;
		btnRightStateR = -1;
		btnUpState = -1;
		btnDownState = -1;
	}

	private void startGameSolo() {
		
		startGameBattle();
		
	}
	
	public void buildLevel(int level) {
		String[] parts = SnakeLevels.getLevel(level).split("\\|");
		String[] sSnakes = parts[0].split(":");
		String[] sWalls = parts[1].split(":");
		
		int curRace = 0;
		
		for (int i = 0 ; i < sWalls.length ; i++) {
			String[] xyt = sWalls[i].split(",");
			walls.addBlock(Integer.parseInt(xyt[0]), Integer.parseInt(xyt[1]),  gameMode == GAMEMODE_BATTLE ? Integer.parseInt(xyt[2]) : 1);
		}
		
		rebuildObstMap();
		
		
		
			for(int i =  0 ; i < sSnakes.length ; i++) {
				String[] xy = sSnakes[i].split(",");
				
				
				
				Snake tSnake = new Snake(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), this);
				tSnake.race = curRace;
				tSnake.live = 1;			
				
				
				tSnake.body.grow(SnakeBug.BUG_TRIPPLE);
				if (curRace == Snake.RACE_PLAYER) {
					tSnake.currentCmd = Snake.CMD_UP;
				}
				
				if (gameMode == GAMEMODE_BATTLE || curRace == Snake.RACE_PLAYER) {
					bugs.spawnBug(curRace);
					snakes.add(tSnake);
				}
				
				if (gameMode == GAMEMODE_SURVIVAL && curRace == 2) {
					bugs.spawnBug(curRace);
					snakes.add(tSnake);
				}
				
				curRace += 1;
			}

		
		walls.placeAllWalls(this);		
		
	}
	
	private void startGameBattle() {
		if (gameLevel > 0) {
			buildLevel(gameLevel);
		} else {
			buildLevel(level);
		}
	}
	
	private void startGameSurvival() {
		startGameBattle();
		lastSnakeSpawn = System.currentTimeMillis();
	}

	public void processTouch(MotionEvent event) {
		if (gameOver == 1 && event.getAction() == MotionEvent.ACTION_UP && System.currentTimeMillis() - gameOverCountdown > 2000) {
			if (btnReplay.rect.contains((int)event.getX(), (int)event.getY())) {
				state = SnakeBoard.NOT_INITED;
			} else if (btnMenu.rect.contains((int)event.getX(), (int)event.getY())) {
				surface.setBoard(surface.startBoard);
				surface.startBoard.reselect();
			} else if (btnRate.rect.contains((int)event.getX(), (int)event.getY())) {
				Uri uri = Uri.parse("market://details?id=" + surface.sContext.getPackageName());
			    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
			    try {
			    	surface.sContext.startActivity(goToMarket);
			    } catch (ActivityNotFoundException e) {
			       
			    }
			}
		} else {
			
			btnRightState = -1;
			btnRightStateR = -1;
			btnUpState = -1;
			btnDownState = -1;
			btnLeftState = -1;
			btnLeftStateR = -1;
			
			if (controlType == CONTROL_TYPE_DPAD) {
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
			
			if (controlType == CONTROL_TYPE_RELATIVE && event.getAction() == MotionEvent.ACTION_DOWN) {
				if (btnLeftR.contains((int)event.getX(), (int)event.getY())) {
					btnLeftStateR = event.getAction();
					
					switch (snakes.get(0).currentCmd)  {
					
					case Snake.CMD_LEFT:
						snakes.get(0).setCommand(Snake.CMD_DOWN);
						break;
					case Snake.CMD_RIGHT:
						snakes.get(0).setCommand(Snake.CMD_UP);
						break;
					case Snake.CMD_UP:
						snakes.get(0).setCommand(Snake.CMD_LEFT);
						break;
					case Snake.CMD_DOWN:
						snakes.get(0).setCommand(Snake.CMD_RIGHT);
						break;						
					
					}
					
					
				} else if (btnRightR.contains((int)event.getX(), (int)event.getY())) {
					btnRightStateR = event.getAction();
					switch (snakes.get(0).currentCmd)  {					
						case Snake.CMD_LEFT:
							snakes.get(0).setCommand(Snake.CMD_UP);
							break;
						case Snake.CMD_RIGHT:
							snakes.get(0).setCommand(Snake.CMD_DOWN);
							break;
						case Snake.CMD_UP:
							snakes.get(0).setCommand(Snake.CMD_RIGHT);
							break;
						case Snake.CMD_DOWN:
							snakes.get(0).setCommand(Snake.CMD_LEFT);
							break;
					}
				}
			}
			
			if (controlType == CONTROL_TYPE_SWIPE) {
				
				if (controlRect.contains((int)event.getX(), (int)event.getY())) {
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						touchX = event.getX();
						touchY = event.getY();
					}
				}

				if (event.getAction() == MotionEvent.ACTION_UP) {
					touchX = -1;
					touchY = -1;
				}
			
				if (event.getAction() == MotionEvent.ACTION_MOVE && touchX != -1) {
					float deltaX = event.getX() - touchX;
					float deltaY = event.getY() - touchY;
					
					if (Math.abs(deltaX) > Math.abs(deltaY) && Math.abs(deltaX) > 10) {
						if (deltaX > 0) {
							snakes.get(0).setCommand(Snake.CMD_RIGHT);
						} else {
							snakes.get(0).setCommand(Snake.CMD_LEFT);
						}
						touchX = event.getX();
						touchY = event.getY();
					} else if (Math.abs(deltaY) > Math.abs(deltaX) && Math.abs(deltaY) > 10) {
						if (deltaY > 0) {
							snakes.get(0).setCommand(Snake.CMD_DOWN);
						} else {
							snakes.get(0).setCommand(Snake.CMD_UP);
						}
						touchX = event.getX();
						touchY = event.getY();
					}
				}
				
			}
			
			if (fieldRect.contains((int)event.getX(), (int)event.getY())) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					isPaused = !isPaused;
				}
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
