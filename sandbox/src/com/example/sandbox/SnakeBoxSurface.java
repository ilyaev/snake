package com.example.sandbox;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SnakeBoxSurface extends SurfaceView implements Runnable {

	SurfaceHolder sHolder;
	Thread sThread = null;
	boolean isRunning = false;
	SnakeBoard board = null;
	
	int sWidth = 0;
	int sHeight = 0;
	
	private final static int 	MAX_FPS = 60;
    private final static int	MAX_FRAME_SKIPS = 5;
    private final static int	FRAME_PERIOD = 1000 / MAX_FPS;
	
	public SnakeBoxSurface(Context context) {
		super(context);
		sHolder = getHolder();
		board = new SnakeBoard();
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
			
			Canvas canvas = null;
			
			
			
			try {
				canvas = sHolder.lockCanvas();
				
				beginTime = System.currentTimeMillis();
				framesSkipped = 0;
				
				if (sWidth == 0) {
					sWidth = canvas.getWidth();
					sHeight = canvas.getHeight();
				}
				
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

}
