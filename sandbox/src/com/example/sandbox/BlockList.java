package com.example.sandbox;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

public class BlockList {

	List<Block> items;
	int width = 0;
	int height = 0;
	
	SnakeBoard  board;

	
	public BlockList(SnakeBoard tBoard) {
		items = new ArrayList<Block>();
		board = tBoard;
		
		width = board.cnHorizontal;
		height = board.cnVertical;				
	}
	
	public void addBlock(int x, int y, int bType) {
		Block block = new Block(x, y, bType);
		items.add(block);
	}
	
	public void draw(Canvas canvas) {
		if (items.size() > 0) {
			for(int i = 0 ; i < items.size() ; i++) {
				items.get(i).draw(canvas, board);
			}
		}
	}
	
}
