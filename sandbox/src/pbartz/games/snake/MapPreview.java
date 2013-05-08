package pbartz.games.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MapPreview {
	
	int level = 1;
	
	SnakeBoard board;
	BlockList blocks;
	Paint paint, textPaint, framePaint;
	
	float cWidth, cHeight;
	
	int leftX, topY, width, height;
	
	int currOffset = 0;
	
	public MapPreview(int tLevel, SnakeBoard tBoard) {
		level = tLevel;
		board = tBoard;
		
		initialize();			
	}
	
	public void setSize(int lX, int tY, int w, int h) {
		leftX = lX;
		topY = tY;
		width = w;
		height = h;
		
		cWidth = width / 20;
		cHeight = cWidth;
		height = (int)(cHeight * 24);		
		
	}
	
	public void draw(Canvas tCanvas, int offsetX) {
		currOffset = offsetX;
		
		if (leftX - offsetX > 480 || leftX - offsetX + width < 0) {
			// skip
		} else {		
			tCanvas.drawRect(leftX - offsetX, topY, leftX - offsetX + width, topY + height, framePaint);
			for(int i = 0 ; i < blocks.items.size() ; i++) {
				Block block = blocks.items.get(i);
				
				float leftx = (block.x-1) * cWidth;
				float topy = (block.y-1) * cHeight;
				
				leftx += leftX - offsetX;		
				topy += topY;
				
				tCanvas.drawRect(leftx + 1, topy + 1, leftx + cWidth - 1, topy + cHeight - 1, paint);
			}
		}
	}
	
	public int getLeft() {
		return leftX - currOffset;
	}
	
	public int getRight() {
		return leftX - currOffset + width;
	}

	private void initialize() {
		
		// set blocks
		String[] parts = SnakeLevels.getLevel(level).split("\\|");
		String[] sWalls = parts[1].split(":");
		
		blocks = new BlockList(board);
		
		for (int i = 0 ; i < sWalls.length ; i++) {
			String[] xyt = sWalls[i].split(",");
			blocks.addBlock(Integer.parseInt(xyt[0]), Integer.parseInt(xyt[1]),  Integer.parseInt(xyt[2]));
		}	
		
		// set colors
		textPaint = new Paint();
		textPaint.setTextSize(55);
		textPaint.setColor(Color.WHITE);		
		
		framePaint = new Paint();
		framePaint.setColor(Color.WHITE);
		framePaint.setStyle(Paint.Style.STROKE);
		
		paint = new Paint();
		paint.setARGB(255, 200, 200, 200);
	}
	
}
