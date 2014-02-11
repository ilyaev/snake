package pbartz.games.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class Slider {

	String menuItems[] = {"Classic", "Escape", "Survival"};
	String menuDescr[] = {"Grow longest tail you \ncan. No rivals.", "Entrap rivals and \ncollect keys to unlock \nnext level.", "Last snake standing.","Compete with you friend\nor random person."};
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
	
	Paint paint, textPaint, framePaint, smTextPaint;
	int iteration = 0;
	int maxIterations = 5;
	float dX;
	int currentItem = 0;
	int staticOffset = 0;
	private int textOffset = 0;
	boolean active = false;
	private Rect mBounds;
	private int actualItem = 0;
	private int transparency = 0;
	private long lastTimeStamp;
	
	public Slider(Typeface mFace, int sWidth, int sHeight) {
		screenW = sWidth;
		screenH = sHeight;
		
		itemHeight = screenH / 4;
		topY = (int)(screenH / 2.666);
		
		lastTimeStamp = System.currentTimeMillis();
		
		paint = new Paint();
		paint.setARGB(200, 50, 50, 50);
		
		textPaint = new Paint();
		textPaint.setTextSize((int)(screenH / 14.54));
		textPaint.setColor(Color.WHITE);		
		textPaint.setTypeface(mFace);
		
		smTextPaint = new Paint();
		smTextPaint.setTextSize((int)(screenH / 28));
		smTextPaint.setARGB(0, 255,255,255);		
		smTextPaint.setTypeface(mFace);
		
		textOffset = (int) ((itemHeight - (screenH / 14.54)) / 2);
		
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
		
		offsetX = (int)(screenH / 2.6666) * -1;
		dX = (offsetX - 0) / maxIterations;		
		
		mBounds = new Rect();
	}
	
	public void setOffset(int nOffset) {
		if (active) {
			offsetX = staticOffset + nOffset;
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
		
			actualItem   = maxItem;
		}
	}
	
	public void calculate() {
		if (active ) {
			if (dX != 0 && iteration < maxIterations) {
				offsetX -= dX;
				iteration += 1;
			}
			
			long timeDiff = System.currentTimeMillis() - lastTimeStamp;
			
			if (timeDiff > 3000 & transparency < 255) {
				transparency += 5;
				if (transparency > 255) {
					transparency = 255;
				}
				smTextPaint.setAlpha(transparency);
			}
		}
	}
	
	public void render(Canvas canvas) {
		if (active) {
			for(int i = 0 ; i < rectItems.length ; i++) {
				rectItems[i].left = (int) (originalRects[i].left - offsetX);
				rectItems[i].right = (int) (originalRects[i].right - offsetX);
				
				canvas.drawRect(rectItems[i], paint);
				canvas.drawRect(rectItems[i], framePaint);			
				canvas.drawText(menuItems[i], rectItems[i].left + textOX[i], rectItems[i].top + textOY[i] ,  textPaint);
			}
			
			drawMultilineText(menuDescr[actualItem], 5, topY + itemHeight + 50, smTextPaint, canvas);
		}
	}
	
	public void drawMultilineText(String str, int x, int y, Paint paint, Canvas canvas) {
	    int      lineHeight = 0;
	    int      yoffset    = 0;
	    String[] lines      = str.split("\n");

	    paint.getTextBounds("Ig", 0, 2, mBounds);
	    lineHeight = (int) ((float) mBounds.height() * 1.6);

	    for (int i = 0; i < lines.length; ++i) {
	        canvas.drawText(lines[i], x, y + yoffset, paint);
	        yoffset = yoffset + lineHeight;
	    }
	}

	public int touchEnd() {
		int result = -1;
		if (!active) {
			return result;
		}
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