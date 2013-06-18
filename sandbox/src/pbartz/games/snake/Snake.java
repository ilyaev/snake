package pbartz.games.snake;

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
	int live = 0;
	
	int active = 1;
	
	int pathRecalculated = 0;
	public int score = 0;
	int kills = 0;
	
	public Snake(int sX, int sY, SnakeBoard sBoard) {
		snakeX = sX;
		snakeY = sY;
		
		board = sBoard;		
		body = new SnakeBody(board, this);
		
		path = new ArrayList<Node>();
	}
	
	public boolean calculatePath() {
		pathRecalculated = 1;
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
			return calculateBestFreeMove();
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
						
						float nextStepCost = current.cost;
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
							neighbour.heuristic = getDistance(xp, yp, bugX, bugY) ;
							neighbour.parent = current;
							open.add(neighbour);
						}
						
					}
					
				}
			}			
		}
		
		Node target = map[bugX][bugY];
		
		if (target.parent == null) {
			return calculateBestFreeMove();
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
		
		//return (float) Math.sqrt((double)(Math.pow(x2-x1, 2) + Math.pow(y2 - y1, 2)));
		
		return (float)(dx + dy);
	}

	public void calculate() {
		if (active == 1 && live == 1) {
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
							
							currentCmd = getCommandByShift(dX, dY);
							
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
				if (currentCmd == 0) {
					deactivateSnake();
				}
			} else {
				body.calculateByCommand();
			}
		}
	}
	
	private boolean calculateBestFreeMove() {
		for (int x = -1 ; x < 2 ; x++) {
			for(int y = -1 ; y < 2 ; y++) {
				
				
				if (x == 0 && y == 0) {
					continue;
				}
				
				if ((x != 0) && (y != 0)) {
					continue;
				}
				
				int xp = x + snakeX;
				int yp = y + snakeY;
				
				if (xp > 0 && yp > 0 && xp <= board.cnHorizontal && yp <= board.cnVertical && !board.oMap[xp][yp]) {
					path.add(new Node(xp, yp));
					return true;
				}
				
			}
		}
		
		return false;
	}

	private int getCommandByShift(int dX, int dY) {
		if (dX == -1 && dY == 0) {
			return CMD_LEFT;
		} else 
		
		if (dX == 1 && dY == 0) {
			return CMD_RIGHT;
		} else
		
		if (dX == 0 && dY == -1) {
			return CMD_UP;
		} else
		
		if (dX == 0 && dY == 1) {
			return CMD_DOWN;
		}
		return 0;
	}

	public void draw() {
		body.draw(board.canvas);
	}
	
	public void setCommand(int cmd) {
		int tX = snakeX;
		int tY = snakeY;
		
		switch (cmd) {		
			case CMD_LEFT:
				tX -= 1;
				break;
			case CMD_RIGHT:
				tX += 1;
				break;
			case CMD_UP:
				tY -= 1;
				break;
			case CMD_DOWN:
				tY += 1;
				break;		
		}
		
		if (tX < 1 || tY < 1 || tX > board.cnHorizontal || tY > board.cnVertical) {
			// skip
		} else {
			if ((board.oMap[tX][tY]) || (body.items.get(1).cellX == tX && body.items.get(1).cellY == tY)) {
				// skip
			} else {
				currentCmd = cmd;
			}
		}
	}

	public void deactivateSnake() {		
		
		if (active == 1 && race != RACE_PLAYER) {
			if (board.snakes.get(0).active == 1) {
				board.snakes.get(0).score += 100;
				board.snakes.get(0).kills += 1;		
				board.walls.unlockFirstLockedSlot();
			}
		}
		
		active = 0;		
		currentCmd = 0;
		
		for(int i = 0 ; i < body.items.size(); i++) {		
			body.items.get(i).maxIterations = 60;
			body.items.get(i).setTarget((int)(Math.random() * board.cnHorizontal + 1), (int)(Math.random() * board.cnVertical + 1));
			body.items.get(i).doShrink = 1;
		}
		
		if (race == RACE_PLAYER) {
			board.gameOverCountdown = System.currentTimeMillis();
			//board.funnyText = "\"" + board.quote.nextQuote() + "\"\n\n" + board.quote.nextAuthor();
			board.gameOver = 1;
			
			if (GameScore.updateScore(board.gameMode, board.gameLevel, board.getScore())) {
				// high score!
				board.funnyText = "High Score!";
			} else {
				board.funnyText = "";
			}
		}
		
		SnakeBug bug = board.bugs.getBugByRace(race); 
		
		if (bug != null) {
			bug.active = 0;
			board.bugs.bugs.remove(bug);
		}
		
	}

}
