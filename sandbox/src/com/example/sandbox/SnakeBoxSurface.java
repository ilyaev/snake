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
	
	private int sleepTime = 15;
	
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
			
			Canvas canvas = null;
			
			
			
			try {
				canvas = sHolder.lockCanvas();
				
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
				
				//sleep
				try {
					sThread.sleep(sleepTime);
				} catch (InterruptedException e) {
					
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
