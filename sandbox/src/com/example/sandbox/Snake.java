package com.example.sandbox;

import java.util.ArrayList;
import java.util.List;

public class Snake {
	
	final static int CMD_LEFT = 1;
	final static int CMD_RIGHT = 2;
	final static int CMD_UP = 3;
	final static int CMD_DOWN = 4;
	
	final static int RACE_PLAYER = 0;
	final static int RACE_ENEMY1 = 1;
	final static int RACE_ENEMY2 = 2;
	final static int RACE_ENEMY3 = 3;
	
	List<Node> path;
	
	int snakeX = 0;
	int snakeY = 0;
	
	int race = RACE_PLAYER;
	
	SnakeBody body;
	SnakeBoard board;
	
	int currentCmd = 0;
	
	public Snake(int sX, int sY, SnakeBoard sBoard) {
		snakeX = sX;
		snakeY = sY;
		
		board = sBoard;		
		body = new SnakeBody(board, this);
		
		path = new ArrayList<Node>();
	}
	
	public boolean calculatePath() {
		path.clear();
		// create map
		Node[][] map = new Node[board.cnHorizontal+1][board.cnVertical+1];
		
		for(int x = 0 ; x < board.cnHorizontal ; x++) {
			for(int y = 0 ; y < board.cnVertical ; y++) {
				map[x+1][y+1] = new Node(x+1, y+1);
			}
		}
		
		SnakeBug targetBug = board.bugs.getBugByRace(race);
		
		if (targetBug == null) {
			return false;
		}
		
		int bugX = targetBug.cellX;
		int bugY = targetBug.cellY;
		
		List<Node> closed = new ArrayList<Node>();
		SortedNodeList open = new SortedNodeList();
		
		map[snakeX][snakeY].cost = 0;
		map[snakeX][snakeY].depth = 0;
		
		closed.clear();
		open.clear();
		open.add(map[snakeX][snakeY]);
		map[bugX][bugY].parent = null;
		
		while(open.size() != 0) {
			Node current = open.first();
			if (current == map[bugX][bugY]) {
				break;
			}
			
			open.remove(current);
			closed.add(current);
			
			for (int x = -1 ; x < 2 ; x++) {
				for(int y = -1 ; y < 2 ; y++) {
					
					
					if (x == 0 && y == 0) {
						continue;
					}
					
					if ((x != 0) && (y != 0)) {
						continue;
					}
					
					int xp = x + current.x;
					int yp = y + current.y;
					
					boolean flag = true;
					if (xp < 1 || xp > board.cnHorizontal || yp < 1 || yp > board.cnVertical) {
						flag = false;
					}					
					
					if (flag && !board.oMap[xp][yp]) { //@Todo: is valid location
						
						float nextStepCost = current.cost + 10;
						Node neighbour = map[xp][yp];
						
						if (nextStepCost < neighbour.cost) {
							if (open.contains(neighbour)) {
								open.remove(neighbour);
							}
							
							if (closed.contains(neighbour)) {
								closed.remove(neighbour);
							}
						}
						
						if (!open.contains(neighbour) && !closed.contains(neighbour)) {
							neighbour.cost = nextStepCost;
							neighbour.heuristic = getDistance(snakeX, snakeY, bugX, bugY) * 10;
							neighbour.parent = current;
							open.add(neighbour);
						}
						
					}
					
				}
			}			
		}
		
		Node target = map[bugX][bugY];
		
		if (target.parent == null) {
			return false;
		}
		
			
		
		List<Node> tmpList = new ArrayList<Node>();
		
		while(target != map[snakeX][snakeY]) {
			tmpList.add(new Node(target.x, target.y));
			target = target.parent;
		}
		
		if (tmpList.size() > 0) {
			for(int i = tmpList.size() - 1 ; i>=0 ; i--) {
				path.add(new Node(tmpList.get(i).x, tmpList.get(i).y));
			}
		}
		
		return true;
	
	}

	private float getDistance(int x1, int y1, int x2, int y2) {
		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		
		return (float)(dx + dy);
	}

	public void calculate() {
		if (race != RACE_PLAYER) {
			if (path.size() > 0) {
				
				Node next = path.get(0);
				
				if (board.oMap[next.x][next.y]) {
					calculatePath();
					calculate();
				} else {
				
					int nextX = next.x;
					int nextY = next.y;					 
					
					if (nextX > 0 && nextY > 0) {
						int dX = nextX - snakeX;
						int dY = nextY - snakeY;
						
						if (dX == -1 && dY == 0) {
							currentCmd = CMD_LEFT;
						} else 
						
						if (dX == 1 && dY == 0) {
							currentCmd = CMD_RIGHT;
						} else
						
						if (dX == 0 && dY == -1) {
							currentCmd = CMD_UP;
						} else
						
						if (dX == 0 && dY == 1) {
							currentCmd = CMD_DOWN;
						}
						
					}
					path.remove(0);
					body.calculateByCommand();
				}
			} else {
				if (calculatePath()) {
					calculate();
				} else {
					currentCmd = 0;
				}
			}
		} else {
			body.calculateByCommand();
		}		
	}

	public void draw() {
		body.draw(board.canvas);
	}
	
	public void setCommand(int cmd) {
		currentCmd = cmd;
	}

}
