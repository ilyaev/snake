package pbartz.games.snake;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawBoxSurface extends SurfaceView implements Runnable {

	SurfaceHolder sHolder;
	Thread sThread = null;
	boolean isRunning = false;
	public List<Particle> dItems;
    
    private final static int 	MAX_FPS = 60;
    private final static int	MAX_FRAME_SKIPS = 5;
    private final static int	FRAME_PERIOD = 1000 / MAX_FPS;	

	public DrawBoxSurface(Context context) {
		super(context);
		sHolder = getHolder();
		dItems = new ArrayList<Particle>();
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		
		long beginTime;		// the time when the cycle begun
		long timeDiff;		// the time it took for the cycle to execute
		int sleepTime;		// ms to sleep (<0 if we're behind)
		int framesSkipped;	// number of frames being skipped 

		sleepTime = 0;
		
		while (isRunning) {
			if (!sHolder.getSurface().isValid()) {
				continue;
			}

			Canvas canvas = null;
			
			try {			
				canvas = sHolder.lockCanvas();
				
				beginTime = System.currentTimeMillis();
				framesSkipped = 0;	
				
				// update data
				updateGameState(canvas.getWidth(), canvas.getHeight());
				
				// draw data				
				synchronized (sHolder) {
					drawGameState(canvas);
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
					updateGameState(canvas.getWidth(), canvas.getHeight());
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
	
	private void drawGameState(Canvas canvas) {
		canvas.drawRGB(0, 0, 0);
		if (dItems.size() > 0) {
			for(int i = 0 ; i < dItems.size() ; i++) {
				Particle p = dItems.get(i);
				if (p != null) {
					p.draw(canvas);
				}
			}
		}		
	}

	public void updateGameState(int maxX, int maxY) {
		if (dItems.size() > 0) {
			for(int i = 0 ; i < dItems.size() ; i++) {
				Particle p = dItems.get(i);
				if (p != null) {
					p.calculate(maxX, maxY);
				}
			}
		}
	}
	
	public void addParticle(float x, float y) {
		
		if (dItems.size() > 100) {
			dItems.remove(0);
		}
		
		dItems.add(new Particle(x,y));
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
