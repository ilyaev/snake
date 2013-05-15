package pbartz.games.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class MapPreview {
	
	int level = 1;
	
	SnakeBoard board;
	BlockList blocks, locked;
	Paint paint, textPaint, framePaint, scorePaint;
	
	float cWidth, cHeight;
	
	int leftX, topY, width, height;
	
	int currOffset = 0;

	private Paint fillPaint;
	
	int plus50 = 50;
	
	public MapPreview(int tLevel, SnakeBoard tBoard, Typeface mFace) {
		level = tLevel;
		board = tBoard;
		
		plus50 = (int)(board.sHeight / 16);
		
		scorePaint = new Paint();
		scorePaint.setTextSize((int)(board.sHeight / 23));
		scorePaint.setTypeface(mFace);
		scorePaint.setColor(Color.WHITE);
		
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
		
		if (leftX - offsetX > board.sWidth || leftX - offsetX + width < 0) {
			// skip
		} else {		
			tCanvas.drawRect(leftX - offsetX, topY, leftX - offsetX + width, topY + height, fillPaint);
			tCanvas.drawRect(leftX - offsetX, topY, leftX - offsetX + width, topY + height, framePaint);

			tCanvas.drawText(Integer.toString(level), leftX - offsetX + 5, topY+ plus50, textPaint);
			if (GameScore.isLevelLocked(level)) {
				tCanvas.drawText("Locked", leftX - offsetX + 5, topY + height + plus50, scorePaint);
			} else {
				tCanvas.drawText("High Score: " + Integer.toString(GameScore.getScore(board.gameMode, level)), leftX - offsetX + 5, topY + height + plus50, scorePaint);
			}
			
		}
	}
	
	public int getLeft() {
		return leftX - currOffset;
	}
	
	public int getRight() {
		return leftX - currOffset + width;
	}

	public void initialize() {
		
		// set blocks
		String[] parts = SnakeLevels.getLevel(level).split("\\|");
		String[] sWalls = parts[1].split(":");
		
		blocks = new BlockList(board);
		locked = new BlockList(board);
		
		for (int i = 0 ; i < sWalls.length ; i++) {
			String[] xyt = sWalls[i].split(",");
			blocks.addBlock(Integer.parseInt(xyt[0]), Integer.parseInt(xyt[1]),  Integer.parseInt(xyt[2]));
		}	
		
		// set lock
		parts = SnakeLevels.lock_level.split("\\|");
		sWalls = parts[1].split(":");
		
		for (int i = 0 ; i < sWalls.length ; i++) {
			String[] xyt = sWalls[i].split(",");
			locked.addBlock(Integer.parseInt(xyt[0]), Integer.parseInt(xyt[1]),  Integer.parseInt(xyt[2]));
		}
		
		// set colors
		textPaint = new Paint();
		textPaint.setTextSize((int)(board.sHeight / 15));
		textPaint.setColor(Color.WHITE);		

		framePaint = new Paint();
		framePaint.setColor(Color.WHITE);
		framePaint.setStyle(Paint.Style.STROKE);
		
		paint = new Paint();
		paint.setARGB(100, 255, 0, 0);
		
		fillPaint = new Paint();
		fillPaint.setARGB(220, 50, 50, 50);
	}

	public void drawMap(Canvas canvas, int offsetX) {
		if (GameScore.isLevelLocked(level)) {
			
			for(int i = 0 ; i < locked.items.size() ; i++) {
				Block block = locked.items.get(i);
				
				float leftx = (block.x-1) * cWidth;
				float topy = (block.y-1) * cHeight;
				
				leftx += leftX - offsetX;		
				topy += topY;
				
				canvas.drawRect(leftx + 1, topy + 1, leftx + cWidth - 1, topy + cHeight - 1, paint);
			}
		}		
	}
	
}
