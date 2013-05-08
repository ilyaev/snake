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
	
	long lastShowTimeStamp = 0;

	private int currentShow = -1;

	public static final int MIN_CELLS_NUMBER = 25;
	
	Slider slider;
	
	LevelSlider levelSlider;

	private float lastTX;
	
	List<SnakePiece> cubes;
	

	public StartSnakeBoard(SnakeBoxSurface tSurface) {
		
		grayPaint = new Paint();
		grayPaint.setARGB(255, 200, 200, 200);
		
		slider = new Slider(tSurface.mFace);
		levelSlider = new LevelSlider(this);
		
		cubes = new ArrayList<SnakePiece>();
		
		surface = tSurface;		
		
		bugs = new SnakeBugList(this);
		snakes = new ArrayList<Snake>();
		
		snakeQ = new ArrayList<Integer>();
		
		lastShowTimeStamp = System.currentTimeMillis();
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

		if (slider.active) {
			slider.render(canvas);
		}
		
		//levelSlider.draw(canvas);
		
		
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
			slider.active = true;
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
		snake = new Snake(7, 2, this);
		snake.body.addTail(7, 3);
		snake.body.addTail(7,4);
		snake.body.addTail(7,5);
		snake.body.addTail(7,6);
		snake.race = 2;
		snakes.add(snake);
		
		// "N" - 2
		snake = new Snake(9, 3, this);
		snake.body.addTail(9, 4);
		snake.body.addTail(8, 4);
		snake.body.addTail(8, 5);
		snake.race = 3;
		snakes.add(snake);
		
		// "N" - 3
		snake = new Snake(10, 6, this);
		snake.body.addTail(10, 5);
		snake.body.addTail(10, 4);
		snake.body.addTail(10, 3);
		snake.body.addTail(10, 2);
		snake.race = 4;
		snakes.add(snake);	
		
		// "A" - 1
		snake = new Snake(16, 6, this);
		snake.body.addTail(16, 5);
		snake.body.addTail(16, 4);
		snake.body.addTail(16, 3);
		snake.body.addTail(16, 2);
		snake.body.addTail(15, 2);
		snake.body.addTail(14, 2);
		snake.body.addTail(13, 2);
		snake.body.addTail(13, 3);
		snake.body.addTail(13, 4);
		snake.body.addTail(13, 5);
		snake.body.addTail(13, 6);
		snake.race = 5;
		snakes.add(snake);
		
		// "A" - 2
		snake = new Snake(14, 5, this);
		snake.body.addTail(15, 5);
		snake.body.addTail(15, 4);
		snake.body.addTail(14, 4);
		snake.race = 6;
		snakes.add(snake);
		
		// "K" - 1
		snake = new Snake(21, 2, this);
		snake.body.addTail(21, 3);
		snake.body.addTail(20, 3);
		snake.body.addTail(19, 3);
		snake.body.addTail(18, 3);
		snake.body.addTail(18, 2);
		snake.race = 7;
		snakes.add(snake);
		
		// "K" - 2
		snake = new Snake(18, 6, this);
		snake.body.addTail(18, 5);
		snake.body.addTail(18, 4);
		snake.body.addTail(19, 4);
		snake.body.addTail(20, 4);
		snake.body.addTail(20, 5);
		snake.body.addTail(20, 6);
		snake.body.addTail(21, 6);
		snake.race = 8;
		snakes.add(snake);
		
		// "E" - 1
		snake = new Snake(25, 6, this);
		snake.body.addTail(24, 6);
		snake.body.addTail(23, 6);
		snake.body.addTail(23, 5);
		snake.body.addTail(23, 4);
		snake.body.addTail(23, 3);
		snake.body.addTail(23, 2);
		snake.body.addTail(24, 2);
		snake.body.addTail(25, 2);
		snake.race = 9;
		snakes.add(snake);
		
		// "E" - 2
		snake = new Snake(24, 4, this);
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
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			lastTX = event.getX();
		}
		
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			slider.setOffset((int) (lastTX - event.getX()));
			levelSlider.setOffset((int) (lastTX - event.getX()));
		}
		
		
		if (event.getAction() == MotionEvent.ACTION_UP) {
			int action = slider.touchEnd();
			levelSlider.touchEnd();
			
			switch (action) {
				case StartSnakeBoard.ACTION_SOLO:
					surface.gameBoard.gameMode = SnakeBoard.GAMEMODE_SOLO;
					surface.gameBoard.state = SnakeBoard.NOT_INITED;
					surface.setBoard(surface.gameBoard);
					break;
				case StartSnakeBoard.ACTION_BATTLE:
					surface.gameBoard.gameMode = SnakeBoard.GAMEMODE_BATTLE;
					surface.gameBoard.state = SnakeBoard.NOT_INITED;
					surface.setBoard(surface.gameBoard);
					break;
				case StartSnakeBoard.ACTION_SURVIVAL:
					surface.gameBoard.gameMode = SnakeBoard.GAMEMODE_SURVIVAL;
					surface.gameBoard.state = SnakeBoard.NOT_INITED;
					surface.setBoard(surface.gameBoard);
					break;	
				default:
					break;
			}
		}
	}
	
}
