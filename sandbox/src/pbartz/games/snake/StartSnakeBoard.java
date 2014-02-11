package pbartz.games.snake;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class StartSnakeBoard extends SnakeBoard {

	SnakeBoxSurface surface;
	
	List<Integer> snakeQ;
	
	int showSpeed = 700;
	
	final static int ACTION_SOLO = 0;
	final static int ACTION_BATTLE = 1;
	final static int ACTION_SURVIVAL = 2;
	final static int ACTION_NETPLAY = 3;
	
	long lastShowTimeStamp = 0;

	private int currentShow = -1;

	public static final int MIN_CELLS_NUMBER = 24;
	
	Slider slider;
	
	LevelSlider levelSlider;

	private float lastTX;
	
	List<SnakePiece> cubes;

	private int menuAction = -1;

	private int mapAction;
	

	public StartSnakeBoard(SnakeBoxSurface tSurface) {
		boardType = BOARDTYPE_DEMO;
		grayPaint = new Paint();
		grayPaint.setARGB(255, 200, 200, 200);	
		
		cubes = new ArrayList<SnakePiece>();
		
		surface = tSurface;		
		
		bugs = new SnakeBugList(this);
		snakes = new ArrayList<Snake>();
		
		snakeQ = new ArrayList<Integer>();
		
		lastShowTimeStamp = System.currentTimeMillis();
		btnControl = new Button("CONTROL : D-PAD", tSurface.mFace);
		btnBoard = new Button("LEADERBOARD", tSurface.mFace);
	}
	
	public void refresh() {
		levelSlider.refreshPreviews();
	}
	
	public void reselect() {
		levelSlider.selectLevel(GameScore.getSelectedLevel(gameMode));	
	}
	
	public void draw(Canvas tCanvas) {
		canvas = tCanvas;		
		canvas.drawRGB(0, 0, 0);
		
		float hSize = (float)(cellSizePx * 0.5);
		
		for(int i = 0 ; i < cubes.size() ; i++) {
			cubes.get(i).draw(tCanvas, grayPaint, hSize);
		}
		
		for(int i = 0 ; i < snakes.size() ; i++) {
			snakes.get(i).draw();
		}
		
		bugs.draw(canvas);
		
		if (booms != null) {
			for(int i = 0 ; i < booms.size() ; i++) {
				booms.get(i).draw(tCanvas);
			}
		}

		slider.render(canvas);
		
		levelSlider.draw(canvas);
		btnControl.draw(canvas);
		if (!levelSlider.active) {
			btnBoard.draw(canvas);
		}
		
	}
	
	public void calculate(Canvas tCanvas) {
		canvas = tCanvas;
		
		long timeDiff = System.currentTimeMillis() - lastTimeStamp;
		long showTimeDiff = System.currentTimeMillis() - lastShowTimeStamp;

		if (state == NOT_INITED) {
			initialize();
		}
		
		if (timeDiff >= defaultSpeed) {		
			
			
			for(int i = 0 ; i < snakes.size() ; i++) {
				if (snakes.get(i).live == 1) {
					snakes.get(i).calculate();
				}
			}
			
			rebuildObstMap();
			
			lastTimeStamp += timeDiff;			
			bugs.processIntercection();
		}
		
		if (showTimeDiff >= showSpeed) {
			int next = currentShow + 1;
			if (next > 0 && next < snakes.size() + 1) {
				if (getSnakeByRace(next).live != 1) {
					bugs.spawnBug(next);
					getSnakeByRace(next).live = 1;
				}					
			}
			if (currentShow < 20) {
				currentShow = next;
			}
			lastShowTimeStamp += showTimeDiff;
			
			if (showSpeed == 700) {
				slider.active = true;
			}

			if (showSpeed >= 3000) {
				showSpeed += 3000;
			} else {
				showSpeed = 3000;
			}
		}
		
		for(int i = 0 ; i < snakes.size() ; i++) {
			if (snakes.get(i).live == 1) {
				snakes.get(i).body.calculate();
			}
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
		
		slider.calculate();
		levelSlider.calculate();
		
	}
	
	public void initialize() {
		sWidth = canvas.getWidth();
		sHeight = canvas.getHeight();
		
		slider = new Slider(surface.mFace, sWidth, sHeight);
		levelSlider = new LevelSlider(this, surface.mFace, sWidth, sHeight);
		
//		sWidth = 320;
//		sHeight = 480;
		
		walls = new BlockList(this);
		
		//bugs.darkRedPaint = bugs.redPaint;
		
		cnHorizontal = MIN_CELLS_NUMBER;
		cpHeight = 0;
		
		if (sHeight > sWidth) {
			cellSizePx = sWidth / cnHorizontal;
			cnVertical = (int) ((sHeight - cpHeight) / cellSizePx);
		} else {
			cnVertical = MIN_CELLS_NUMBER;
			cellSizePx = sHeight / cnVertical;
			cnHorizontal = (int) ((sWidth - cpHeight) / cellSizePx);
		}
		
		oMap = new boolean[cnHorizontal + 1][cnVertical + 1];
		
//		grayPaint = new Paint();
//		grayPaint.setARGB(255, 125, 125, 125);
		
		whiteFramePaint = new Paint();
		whiteFramePaint.setARGB(255, 255, 255, 255);
		whiteFramePaint.setStyle(Paint.Style.STROKE);
		
		orangePaint = new Paint();
		orangePaint.setARGB(255, 226, 139, 0);
		
		greenPaint = new Paint();
		greenPaint.setARGB(255, 0, 255, 0);
		
		//darkGreenPaint = greenPaint;
		darkGreenPaint = new Paint();
		darkGreenPaint.setARGB(255, 0, 125, 0);
		
		state = INITED;
		
		lastTimeStamp = 0;
		
		
		
		btnControl.setPosition(1, sHeight - (int)(sHeight / 7.61));
		btnControl.setSize(sWidth - 5, sHeight / 10);
		btnControl.setFontSize(sHeight / 25);
		
		btnBoard.setPosition(1, sHeight / 6);
		btnBoard.setSize(sWidth - 5, sHeight / 10);
		btnBoard.setFontSize(sHeight / 25);
		
		addSnakes();
		rebuildObstMap();
		
		state = INITED;
	}
	
	public void addSnakes() {
		// "S"
		Snake snake = new Snake(4, 2, this);
		snake.body.addTail(3, 2);
		snake.body.addTail(2,2);
		snake.body.addTail(2,3);
		snake.body.addTail(2,4);
		snake.body.addTail(3,4);
		snake.body.addTail(4,4);
		snake.body.addTail(4,5);
		snake.body.addTail(4,6);
		snake.body.addTail(3,6);
		snake.body.addTail(2,6);
		snake.race = 1;
		snakes.add(snake);
		
		// "N" - 1
		snake = new Snake(6, 2, this);
		snake.body.addTail(6, 3);
		snake.body.addTail(6,4);
		snake.body.addTail(6,5);
		snake.body.addTail(6,6);
		snake.race = 2;
		snakes.add(snake);
		
		// "N" - 2
		snake = new Snake(8, 5, this);
		snake.body.addTail(8, 4);
		snake.body.addTail(7, 4);
		snake.body.addTail(7, 3);
		snake.race = 3;
		snakes.add(snake);
		
		// "N" - 3
		snake = new Snake(9, 6, this);
		snake.body.addTail(9, 5);
		snake.body.addTail(9, 4);
		snake.body.addTail(9, 3);
		snake.body.addTail(9, 2);
		snake.race = 4;
		snakes.add(snake);	
		
		// "A" - 1
		snake = new Snake(14, 6, this);
		snake.body.addTail(14, 5);
		snake.body.addTail(14, 4);
		snake.body.addTail(14, 3);
		snake.body.addTail(14, 2);
		snake.body.addTail(13, 2);
		snake.body.addTail(12, 2);
		snake.body.addTail(11, 2);
		snake.body.addTail(11, 3);
		snake.body.addTail(11, 4);
		snake.body.addTail(11, 5);
		snake.body.addTail(11, 6);
		snake.race = 5;
		snakes.add(snake);
		
		// "A" - 2
		snake = new Snake(12, 5, this);
		snake.body.addTail(13, 5);
		snake.body.addTail(13, 4);
		snake.body.addTail(12, 4);
		snake.race = 6;
		snakes.add(snake);
		
		// "K" - 1
		snake = new Snake(19, 2, this);
		snake.body.addTail(19, 3);
		snake.body.addTail(18, 3);
		snake.body.addTail(17, 3);
		snake.body.addTail(16, 3);
		snake.body.addTail(16, 2);
		snake.race = 7;
		snakes.add(snake);
		
		// "K" - 2
		snake = new Snake(16, 6, this);
		snake.body.addTail(16, 5);
		snake.body.addTail(16, 4);
		snake.body.addTail(17, 4);
		snake.body.addTail(18, 4);
		snake.body.addTail(18, 5);
		snake.body.addTail(18, 6);
		snake.body.addTail(19, 6);
		snake.race = 8;
		snakes.add(snake);
		
		// "E" - 1
		snake = new Snake(23, 6, this);
		snake.body.addTail(22, 6);
		snake.body.addTail(21, 6);
		snake.body.addTail(21, 5);
		snake.body.addTail(21, 4);
		snake.body.addTail(21, 3);
		snake.body.addTail(21, 2);
		snake.body.addTail(22, 2);
		snake.body.addTail(23, 2);
		snake.race = 9;
		snakes.add(snake);
		
		// "E" - 2
		snake = new Snake(22, 4, this);
		snake.race = 10;
		snakes.add(snake);		
		
		for(int i = 0 ; i < snakes.size() ; i++) {
			snakeQ.add(snakes.get(i).race);
		}
		
		for(int i = 0 ; i < snakes.size() ; i++) {
			for(int j = 0 ; j < snakes.get(i).body.items.size() ; j++) {
				cubes.add(new SnakePiece(snakes.get(i).body.items.get(j).cellX, snakes.get(i).body.items.get(j).cellY, this));
			}
		}
	}
	

	public void processTouch(MotionEvent event) {
		
		if (btnControl.rect.contains((int)event.getX(), (int)event.getY())) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				surface.rotateControlType();
			}
			return;
		}
		
		if (btnBoard.rect.contains((int)event.getX(), (int)event.getY()) && !levelSlider.active) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				SnakeBox activity = ((SnakeBox) surface.sContext);
				activity.showLeaderBoard();
			}
			return;
		}
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			lastTX = event.getX();
			if (btnControl.rect.contains((int)event.getX(), (int)event.getY())) {
				btnControl.setText("CONTROL : D-PAD");
				return;
			}
		}
		
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			slider.setOffset((int) (lastTX - event.getX()));
			levelSlider.setOffset((int) (lastTX - event.getX()));
		}
		
		
		if (event.getAction() == MotionEvent.ACTION_UP) {
			
			
			
			
			if (menuAction < 0) {
				menuAction = slider.touchEnd();
			}
			
			mapAction = levelSlider.touchEnd();
			
			if (menuAction >= 0) {
				if (levelSlider.active == false) {					
					switch (menuAction) {
						case StartSnakeBoard.ACTION_SOLO:
							gameMode = SnakeBoard.GAMEMODE_SOLO;
							break;
						case StartSnakeBoard.ACTION_BATTLE:
							gameMode = SnakeBoard.GAMEMODE_BATTLE;
							break;
						case StartSnakeBoard.ACTION_SURVIVAL:
							gameMode = SnakeBoard.GAMEMODE_SURVIVAL;
							break;	
						case StartSnakeBoard.ACTION_NETPLAY:
							gameMode = SnakeBoard.GAMEMODE_NETPLAY;
							break;
					}
					levelSlider.active = true;
					levelSlider.selectLevel(GameScore.getSelectedLevel(gameMode));					
					slider.active = false;
				}
			}
			
			if (mapAction >= 0) {
				if (gameMode == SnakeBoard.GAMEMODE_NETPLAY) {
					// init netplay
					SnakeBox activity = ((SnakeBox) surface.sContext);
					surface.gameBoard.gameLevel = mapAction;
					surface.gameBoard.gameMode = gameMode;
					surface.gameBoard.state = SnakeBoard.NOT_INITED;
					activity.startQuickGame();					
				} else {
					surface.gameBoard.gameLevel = mapAction;
					surface.gameBoard.gameMode = gameMode;
					surface.gameBoard.state = SnakeBoard.NOT_INITED;
					surface.setBoard(surface.gameBoard);
				}
			}
		}
	}
	
	public boolean backAction() {
		if (!slider.active) {
			slider.active = true;
			menuAction = -1;
			levelSlider.active = false;
			slider.offsetX -= 180;
			slider.iteration = 0;
			slider.dX = (-180) / slider.maxIterations;	
			return true;
		}
		return false;
	}
	
	public void rebuildObstMap() {
		for(int x = 1 ; x <= cnHorizontal ; x++) {
			for(int y = 1 ; y <= cnVertical ; y++) {
				oMap[x][y] = false;
			}
		}
		
		oMap[13][3] = true;
		oMap[12][3] = true;
		
		for(int i = 0 ; i < snakes.size() ; i++) {
			if (snakes.get(i).active == 1) {
				for (int j = 0 ; j < snakes.get(i).body.items.size() ; j++) {
					oMap[snakes.get(i).body.items.get(j).cellX][snakes.get(i).body.items.get(j).cellY] = true;			
				}
			}
		}
		
		if (walls != null && walls.items.size() > 0) {
			for(int i = 0 ; i < walls.items.size() ; i++) {
				if (walls.items.get(i).type != Block.BLOCK_EXIT) {
					oMap[walls.items.get(i).x][walls.items.get(i).y] = true;
				}
			}
		}
		
		for(int i = 0 ; i < cubes.size() ; i++) {
			oMap[cubes.get(i).cellX][cubes.get(i).cellY] = true;
		}
	}
	
}
