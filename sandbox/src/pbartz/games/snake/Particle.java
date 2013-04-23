package pbartz.games.snake;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Particle {

	public float x;
	public float y;
	public float speedX;
	public float speedY;
	public boolean isActive = true;
	public int stepsXY;
	public Paint paint;
	
	public Particle (float cX, float cY) {
		x = cX;
		y = cY;
		stepsXY = 0;
		isActive = true;
		paint = new Paint();
		paint.setARGB(255, 255, 0, 0);
	}
	
	public void setXYDestination(float nX, float nY, int speed) {
	
		speedX = (nX - x) /  speed;
		speedY = (nY - y) /  speed;
		
		stepsXY = speed;
	}
	
	public void calculate(int cWidth, int cHeight) {
		if (isActive) {
			if (stepsXY > 0) {
				x = x + speedX * 2;
				y = y + speedY * 2;
				stepsXY -= 1;
			} else {
				isActive = false;
			}
		}
	}
	
	public void draw(Canvas canvas) {
		canvas.drawCircle(x, y, 3, paint);
	}
	
}