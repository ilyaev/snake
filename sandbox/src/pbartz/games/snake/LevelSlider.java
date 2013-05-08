package pbartz.games.snake;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

public class LevelSlider {
	
	SnakeBoard board;
	List<MapPreview> levels;
	private int itemWidth;
	private int leftX;
	private int itemHeight;
	private int topY;
	private int offsetX;
	private int staticOffset = 0;
	private int currentItem = 0;
	private int iteration = 0;
	private int maxIterations = 10;
	private int dX = 0;
	
	public int toItem = 0;
	
	public LevelSlider(SnakeBoard tBoard) {
		board = tBoard;
		levels = new ArrayList<MapPreview>();
		
		itemWidth = 400;
		itemHeight = 600;
		leftX = 40;
		topY = 100;
		
		int between = 20;
		int index = 0;
		for(int i = 2 ; i <= SnakeLevels.levels.length ; i++) {
			MapPreview level = new MapPreview(i, board);
			level.setSize(leftX + (index*itemWidth) + (between * index), topY, itemWidth, itemHeight);
			levels.add(level);
			index += 1;
		}
	}
	
	public void calculate() {
		if (dX != 0 && iteration < maxIterations) {
			offsetX -= dX;
			iteration += 1;
		}
	}
	
	public int touchEnd() {
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
			result = currentItem;
		}
		
		currentItem  = maxItem;
		
		
		int offsetTo = levels.get(maxItem).getLeft() - levels.get(0).getLeft();
		
		staticOffset = offsetTo;
		
		iteration = 0;
		dX = (offsetX - offsetTo) / maxIterations;	
		
		return result;
	}
	
	public void draw(Canvas canvas) {
		for(int i = 0 ; i < levels.size() ; i++) {
			levels.get(i).draw(canvas, offsetX);
		}
	}

	public void setOffset(int nOffset) {
		offsetX = staticOffset + nOffset;
	}
	
}