package com.example.sandbox;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Button {
	
	Rect rect = new Rect(0,0,0,0);
	Paint paint;
	Paint fillPaint;
	Paint textPaint;
	String caption;
	int action = -1;
	
	int bW;
	int bH;
	public int fontSize = 0;
	
	public Button(String tCaption) {
		caption = tCaption;
		paint = new Paint();
		paint.setARGB(255, 255, 255, 255);
		paint.setStyle(Paint.Style.STROKE);
		
		textPaint = new Paint();
		textPaint.setARGB(255, 255, 255, 255);
		
		fillPaint = new Paint();
		fillPaint.setARGB(200, 0,0,0);
	}
	
	public void setPosition(int pX, int pY) {
		rect.left = pX;
		rect.top = pY;
	}
	
	public void setSize(int sW, int sH) {
		rect.right = rect.left + sW;
		rect.bottom = rect.top + sH;
		
		bH = sH;
		bW = sW;
		
		textPaint.setTextSize(sH - 10);
	}
	
	public void draw(Canvas dCanvas) {
		Rect bounds = new Rect();
		
		textPaint.getTextBounds(caption, 0, caption.length(), bounds);
		
		float shiftX = 5;
		float shiftY = bH - 10;
		
		shiftX = (rect.width() - bounds.width()) / 2;
		shiftY = bH - (rect.height() - bounds.height()) / 2;
		
		dCanvas.drawRect(rect, paint);
		dCanvas.drawRect(rect.left + 1, rect.top + 1, rect.right - 1, rect.bottom - 1, fillPaint);
		dCanvas.drawText(caption, rect.left + shiftX, rect.top + shiftY, textPaint);		
	}

	public void setAction(int actionStart) {
		action = actionStart;
	}

	public void setFontSize(int fSize) {
		textPaint.setTextSize(fSize);		
	}

}
