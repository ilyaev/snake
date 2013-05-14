package pbartz.games.snake;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class LevelSlider {
	
	SnakeBoard board;
	List<MapPreview> levels;
	private int itemWidth;
	private int leftX;
	private int itemHeight;
	private int topY;
	int offsetX;
	int staticOffset = 0;
	int currentItem = 0;
	int iteration = 0;
	int maxIterations = 10;
	int dX = 0;
	
	int transFrom, transTo;
	
	List<Block> transBlocks;
	
	public int toItem = 0;
	
	Paint blockPaint;
	private Paint textPaint;
	private int actualItem = 0;
	private int offsetTo = 0;
	public boolean active = false;
	
	public LevelSlider(SnakeBoard tBoard, Typeface mFace) {
		
		active = false;
		
		board = tBoard;
		levels = new ArrayList<MapPreview>();
		
		transBlocks = new ArrayList<Block>();
		
		itemWidth = 400;
		itemHeight = 600;
		leftX = 40;
		topY = 140;
		
		int between = 20;
		int index = 0;
		
		for(int i = 1 ; i <= SnakeLevels.levels.length ; i++) {
			MapPreview level = new MapPreview(i, board, mFace);
			level.setSize(leftX + (index*itemWidth) + (between * index), topY, itemWidth, itemHeight);
			levels.add(level);
			index += 1;
		}
		
		blockPaint = new Paint();
		blockPaint.setARGB(255, 255, 255, 255);
		
		textPaint = new Paint();
		textPaint.setTextSize(30);
		textPaint.setTypeface(mFace);
		textPaint.setColor(Color.WHITE);	
		
		transFrom = 0;
		transTo = 1;
		
		switchTransition(transFrom, transTo);
	}
	
	public void refreshPreviews() {
		for(int i = 0 ; i < levels.size() ; i++) {
			if (levels.get(i).level == GameScore.lastUnlockedLevel + 2) {
				levels.get(i).initialize();
			}
		}
	}
	
	public void calculate() {
		if (active) {
			if (dX != 0 && iteration < maxIterations) {
				offsetX -= dX;
				iteration += 1;
				if (iteration == maxIterations) {
					offsetX = offsetTo;
					setOffset(0);
				}
			}
		}
	}
	
	public int touchEnd() {
		if (!active) return -1;
		
		int result = -1;
		
		int maxItem = 0;
		int maxW = 0;
		
		for(int i = 0 ; i < levels.size() ; i++) {
			int left = Math.max(0, levels.get(i).getLeft());
			int right = Math.min(480, levels.get(i).getRight());
			
			if ((right - left) > maxW) {
				maxW = right - left;
				maxItem = i;
			}
		}
		
		if (currentItem == maxItem && Math.abs(offsetX - staticOffset) < 5) {
			if (!GameScore.isLevelLocked(levels.get(currentItem).level)) {
				result = levels.get(currentItem).level;
			}
		}

		currentItem  = maxItem;
		
		
		offsetTo  = levels.get(maxItem).getLeft() - levels.get(0).getLeft();
		
		staticOffset = offsetTo;
		
		iteration = 0;
		dX = (offsetX - offsetTo) / maxIterations;			
		return result;
	}
	
	public void switchTransition(int fromLevel, int toLevel) {
		transBlocks.clear();
		MapPreview originLevel = levels.get(fromLevel);
		MapPreview nextLevel = levels.get(toLevel);
		
		for(int i = 0 ; i < originLevel.blocks.items.size() ; i++) {
			Block origin = originLevel.blocks.items.get(i);
			
			
			Block block = new Block(origin.x, origin.y, 0);
			block.rx = originLevel.leftX + ((origin.x-1)*originLevel.cWidth);
			block.ry = originLevel.topY + ((origin.y-1)*originLevel.cHeight);
			
			block.tx = 0;
			block.ty = 0;
			
			block.width = (int)nextLevel.cWidth;
			block.height = (int)nextLevel.cHeight;
			
			if (i >= nextLevel.blocks.items.size()) {
				block.iteration = 0;
				block.maxIterations = 0;
			} else {
				block.iteration = 0;
				block.maxIterations = originLevel.width + 20;
			}
			
			transBlocks.add(block);
		}
		
		for(int i = 0 ; i < nextLevel.blocks.items.size() ; i++) {
			Block origin = nextLevel.blocks.items.get(i);
			if (i < originLevel.blocks.items.size()) {
				
				Block block = transBlocks.get(i);
				
				block.tx = nextLevel.leftX + ((origin.x-1)*nextLevel.cWidth);
				block.ty = nextLevel.topY + ((origin.y-1)*nextLevel.cHeight);
				block.width = (int)nextLevel.cWidth;
				block.height = (int)nextLevel.cHeight;
				
				transBlocks.set(i, block);
			} else {
				Block block = new Block(origin.x, origin.y, 0);
				block.rx = nextLevel.leftX + ((origin.x-1)*nextLevel.cWidth);
				block.ry = nextLevel.topY + ((origin.y-1)*nextLevel.cHeight);
				
				block.tx = 0;
				block.ty = 0;
				
				block.width = (int)nextLevel.cWidth;
				block.height = (int)nextLevel.cHeight;
				
				block.iteration = 0;
				block.maxIterations = 0;//originLevel.width + 20;
				
				transBlocks.add(block);
			}
		}
		
		
		
	}
	
	public void draw(Canvas canvas) {
		if (active) {
			
			for(int i = 0 ; i < levels.size() ; i++) {
				levels.get(i).draw(canvas, offsetX);			
			}
			
			for(int i = 0 ; i < transBlocks.size() ; i++) {
				Block block = transBlocks.get(i);
				
				int perc = (offsetX - staticOffset);
	
				if (perc < 0) {
					perc = 420 + perc;
				}
				
				float x = block.rx;
				float y = block.ry;
				if (block.maxIterations > 0) {		
					x = block.rx + (((block.tx - block.rx) / block.maxIterations) * perc);
					y = block.ry + (((block.ty - block.ry) / block.maxIterations) * perc);
				}
				
				canvas.drawRect(x - offsetX  + 1, y + 1, x - offsetX + block.width - 1, y + block.height - 1, blockPaint);
			}
			
			for(int i = 0 ; i < levels.size() ; i++) {
				levels.get(i).drawMap(canvas, offsetX);			
			}
			
			
		}
	}

	public void setOffset(int nOffset) {
		if (active) {
			offsetX = staticOffset + nOffset;
			
			int maxItem = 0;
			int maxW = 0;
			
			for(int i = 0 ; i < levels.size() ; i++) {
				int left = Math.max(0, levels.get(i).getLeft());
				int right = Math.min(480, levels.get(i).getRight());
				
				if ((right - left) > maxW) {
					maxW = right - left;
					maxItem = i;
				}
			}
			
			actualItem  = maxItem;
			
			int tF = currentItem - 1;
			int tT = currentItem;
			
			if ((offsetX - staticOffset) >= 0) {
				tF = currentItem;
				tT = currentItem + 1;
			}
			
			if (tF < 0) tF = 0;
			if (tT > levels.size() - 1) tT = levels.size() - 1;
			
			if (tF != transFrom) {
				transFrom = tF;
				transTo = tT;
				switchTransition(transFrom, transTo);
			}
		}		
	}
	
}