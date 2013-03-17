package com.example.sandbox;

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
		paint.setARGB(255, 226, 139, 0);
	}
	
	public void setXYDestination(float nX, float nY, int speed) {
		//stepsXY = steps;
		
//		float steps = speed / (1000 / 60);
		
		speedX = (nX - x) /  60; //(1000 / 60);
		speedY = (nY - y) /  60; //(1000 / 60);
		
		stepsXY = 60;
	}
	
	public void calculate(int cWidth, int cHeight) {
		if (isActive) {
			if (stepsXY > 0) {
				x = x + speedX;
				y = y + speedY;
				stepsXY -= 1;
			} else {
				//setXYDestination((float)Math.random() * 200, (float)Math.random() * 200, 30);
				//setXYDestination((float)Math.random() * cWidth, (float)Math.random() * cHeight, 30);
				setXYDestination((float)Math.random() * cWidth, (float)Math.random() * 200, 50);
			}
		}
	}
	
	public void draw(Canvas canvas) {
		canvas.drawCircle(x, y, 5, paint);
	}
	
}