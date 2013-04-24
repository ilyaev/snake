package pbartz.games.snake;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.util.Log;

public class BlockList {

	List<Block> items;
	int width = 0;
	int height = 0;
	
	int totalLocks = 0;
	int currLocks = 0;
	
	SnakeBoard  board;

	
	public BlockList(SnakeBoard tBoard) {
		items = new ArrayList<Block>();
		board = tBoard;
		
		totalLocks = 0;
		currLocks = 0;
		
		width = board.cnHorizontal;
		height = board.cnVertical;				
	}
	
	public void reset() {
		totalLocks = 0;
		currLocks = 0;
	}
	
	public void addBlock(int x, int y, int bType) {
		Block block = new Block(x, y, bType);
		items.add(block);
		
		if (block.type == Block.BLOCK_KEYHOLE) {
			totalLocks += 1;
		}
	}
	
	public void draw(Canvas canvas) {
		if (items.size() > 0) {
			for(int i = 0 ; i < items.size() ; i++) {
				items.get(i).draw(canvas, board);
			}
		}
	}
	
	public void calculate() {
		if (items.size() > 0) {
			for(int i = 0 ; i < items.size() ; i++) {
				items.get(i).calculate();
			}
		}
	}
	
	public void placeAllWalls(SnakeBoard board) {
		if (items.size() > 0) {
			for(int i = 0 ; i < items.size() ; i++) {
				items.get(i).animatePlacement(board);
			}
		}
	}
	
	public Block unlockFirstLockedSlot() {		
		Block result = null;
		if (items.size() > 0) {
			for(int i = 0 ; i < items.size() ; i++) {
				if (items.get(i).type == Block.BLOCK_KEYHOLE && items.get(i).isUnlocked == false) {
					items.get(i).isUnlocked = true;
					result = items.get(i);
					currLocks += 1;
					if (currLocks == totalLocks) {
						board.blowUpLocks();
					}
					return result;
				}
			}
		}
		return result;
	}
	
}
