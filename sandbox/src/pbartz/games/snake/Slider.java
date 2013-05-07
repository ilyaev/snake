package pbartz.games.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class Slider {

	String menuItems[] = {"Classic", "Escape", "Survival"};
	Rect rectItems[];
	Rect originalRects[];
	int textOX[];
	int textOY[];
	
	int itemWidth = 0;
	int itemHeight = 200;
	
	int screenW, screenH = 0;
	int topY = 300;
	int leftX = 0;
	float offsetX = 0;
	
	Paint paint, textPaint, framePaint;
	private int iteration = 0;
	private int maxIterations = 5;
	private float dX;
	int currentItem = 0;
	int staticOffset = 0;
	private int textOffset = 0;
	boolean active = false;
	
	public Slider(Typeface mFace) {
		screenW = 480;
		screenH = 800;
		
		paint = new Paint();
		paint.setARGB(200, 0, 0, 0);
		
		textPaint = new Paint();
		textPaint.setTextSize(55);
		textPaint.setColor(Color.WHITE);		
		textPaint.setTypeface(mFace);
		
		textOffset = (int) ((itemHeight - 55) / 2);
		
		framePaint = new Paint();
		framePaint.setColor(Color.WHITE);
		framePaint.setStyle(Paint.Style.STROKE);
		
		itemWidth = (int) (screenW / 1.5);
		leftX = (int) (screenW - itemWidth) / 2;
		
		rectItems = new Rect[menuItems.length];
		originalRects = new Rect[menuItems.length];
		
		textOX = new int[menuItems.length];
		textOY = new int[menuItems.length];
		
		for(int i = 0 ; i < menuItems.length ; i++) {
			int lX = leftX + (i*itemWidth) + (i*(leftX / 2));
			int tY = topY;
			rectItems[i] = new Rect(lX, tY, lX + itemWidth, tY + itemHeight);
			originalRects[i] = new Rect(lX, tY, lX + itemWidth, tY + itemHeight);
			
			Rect bounds = new Rect();
			textPaint.getTextBounds(menuItems[i], 0, menuItems[i].length(), bounds);
			textOX[i] = (itemWidth - bounds.width()) / 2;
			textOY[i] = (itemHeight - (bounds.height() / 5)) / 2;			
		}
		
		offsetX = -300;
		dX = (offsetX - 0) / maxIterations;		
	}
	
	public void setOffset(int nOffset) {
		offsetX = staticOffset + nOffset;
	}
	
	public void calculate() {
		if (active ) {
			if (dX != 0 && iteration < maxIterations) {
				offsetX -= dX;
				iteration += 1;
			}
		}
	}
	
	public void animate() {
		
			
		
	}
	
	public void render(Canvas canvas) {
		for(int i = 0 ; i < rectItems.length ; i++) {
			rectItems[i].left = (int) (originalRects[i].left - offsetX);
			rectItems[i].right = (int) (originalRects[i].right - offsetX);
			
			canvas.drawRect(rectItems[i], paint);
			canvas.drawRect(rectItems[i], framePaint);			
			canvas.drawText(menuItems[i], rectItems[i].left + textOX[i], rectItems[i].top + textOY[i] ,  textPaint);
		}
	}

	public int touchEnd() {
		int result = -1;
		
		int maxItem = 0;
		int maxW = 0;
		
		for(int i = 0 ; i < rectItems.length ; i++) {
			int left = Math.max(0, rectItems[i].left);
			int right = Math.min(screenW, rectItems[i].right);
			
			if ((right - left) > maxW) {
				maxW = right - left;
				maxItem = i;
			}
		}
		
		if (currentItem == maxItem && Math.abs(offsetX - staticOffset) < 5) {
			result = currentItem;
		}
		
		currentItem  = maxItem;
		
		
		int offsetTo = originalRects[maxItem].left - originalRects[0].left;
		
		staticOffset = offsetTo;
		
		iteration = 0;
		dX = (offsetX - offsetTo) / maxIterations;	
		
		return result;
	}
	
}