package pbartz.games.snake;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SnakeBoxSurface extends SurfaceView implements Runnable {

	SurfaceHolder sHolder;
	Thread sThread = null;
	boolean isRunning = false;
	SnakeBoard board = null;
	
	SnakeBoard gameBoard = null;
	SnakeBoard startBoard = null;
	
	Context sContext = null;
	public Typeface mFace;
	public volatile Canvas canvas = null;
	
	private final static int 	MAX_FPS = 60;
    private final static int	MAX_FRAME_SKIPS = 5;
    private final static int	FRAME_PERIOD = 1000 / MAX_FPS;
	
	public SnakeBoxSurface(Context context) {
		super(context);
		mFace = Typeface.createFromAsset(getContext().getAssets(),"fonts/Biotype.ttf");
		sContext = context;
		sHolder = getHolder();
		
		gameBoard = new GameSnakeBoard(this);
		startBoard = new StartSnakeBoard(this);
		
		loadState();
		
		
		setBoard(startBoard);
	}

	public void saveState() {
		SharedPreferences prefs = sContext.getSharedPreferences("pbartz.games.snake", 0);
		SharedPreferences.Editor editor = prefs.edit();
		editor.commit();
	}
	
	public void loadState() {
		SharedPreferences prefs = sContext.getSharedPreferences("pbartz.games.snake", 0);
		GameScore.initialize(prefs);
		setControlType(GameScore.getControlType());
	}
	
	public void setBoard(SnakeBoard nextBoard) {
		synchronized (this) {
			board = nextBoard;
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while (isRunning) {
			if (!sHolder.getSurface().isValid()) {
				continue;
			}
			
			long beginTime;		// the time when the cycle begun
			long timeDiff;		// the time it took for the cycle to execute
			int sleepTime;		// ms to sleep (<0 if we're behind)
			int framesSkipped;	// number of frames being skipped 

			sleepTime = 0;			
			
			try {
				canvas  = sHolder.lockCanvas();
				
				beginTime = System.currentTimeMillis();
				framesSkipped = 0;
				
				// calculate
				board.calculate(canvas);				
				
				//draw
				synchronized (sHolder) {
					board.draw(canvas);
				}		
				
				// sleep stuff
				timeDiff = System.currentTimeMillis() - beginTime;
				sleepTime = (int)(FRAME_PERIOD - timeDiff);
				
				if (sleepTime > 0) {
					try {
						sThread.sleep(sleepTime);
					} catch (InterruptedException e) {}
				}

				while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
					board.calculate(canvas);
					sleepTime += FRAME_PERIOD;
					framesSkipped++;
				}
				
			} finally {
				if (canvas != null) {
					sHolder.unlockCanvasAndPost(canvas);
				}
			}			
			
		}		
	}
	
	public void pause() {
		isRunning = false;
		board.isPaused = true;	
		while (true) {
			try {
				sThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}

		sThread = null;
	}

	public void resume() {
		isRunning = true;
		sThread = new Thread(this);
		sThread.start();
	}

	public void rotateControlType() {
		int cType = gameBoard.controlType;
		cType += 1;
		if (cType > 3) {
			cType = 1;
		}
		setControlType(cType);		
		GameScore.updateControlType(cType);
	}

	public void setControlType(int cType) {
		gameBoard.controlType = cType;
		String txt = "D-PAD";
		if (cType == 2) {
			txt = "RELATIVE";
		}
		if (cType == 3) {
			txt = "SWIPE";
		}		
		startBoard.btnControl.setText("CONTROL : " + txt);		
	}

}
