package pbartz.games.snake;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class SnakeBoard {
	
	SnakeBoxSurface surface;
	
	int level = 1;
	
	final static int BOARDTYPE_DEMO = 1;
	final static int BOARDTYPE_GAME = 2;
	
	public int boardType = BOARDTYPE_GAME;
	
	
	final static int GAMEMODE_SOLO = 1;
	final static int GAMEMODE_BATTLE = 2;
	final static int GAMEMODE_SURVIVAL = 3;
	
	BlockList walls;

	int gameMode = -1;

	public int sWidth;
	public int sHeight;
	
	boolean roundWon = false;
	
	Quote quote;
	String funnyText;
	
	int gameOver = 0;
	
	final static int NOT_INITED	= 0;
	final static int INITED 	= 1;
	final static int IS_MOVING 	= 2;
	
	int defaultSpeed = 200;
	
	int cpHeight = 190;
	
	int cnHorizontal = 20;
	int cnVertical = 0;
	
	float cellSizePx = 0;

	int state = NOT_INITED;
	
	Paint grayPaint;
	
	Paint whiteFramePaint;
	Paint orangePaint;
	Paint greenPaint;
	Paint buttonPressedPaint;
	Paint darkGreenPaint;
	
	SnakeBugList bugs = null;
	List<Snake> snakes;
	
	List<ParticleList> booms = null;
	
	boolean oMap[][];
	
	public Canvas canvas = null;
	
	long lastTimeStamp = 0;

	public long gameOverCountdown = 0;

	long freezeStartTime = 0;

	public int gameLevel = 0;
	
	public void draw(Canvas tCanvas) {
		canvas = tCanvas;
	}
	
	public void calculate(Canvas tCanvas) {
		canvas = tCanvas;
	}
	
	public void processTouch(MotionEvent event) {
		
	}
	
	public float getCellCenterX(int x) {
		return (x - 1) * cellSizePx + (cellSizePx / 2);
	}
	
	public float getCellCenterY(int y) {
		return (y - 1) * cellSizePx + (cellSizePx / 2);
	}
	
	public Snake getSnakeByRace(int race) {
		for(int i = 0 ; i < snakes.size() ; i++) {
			if (snakes.get(i).race == race) {
				return snakes.get(i);
			}
		}
		return null;
	}
	
	public void rebuildObstMap() {
		for(int x = 1 ; x <= cnHorizontal ; x++) {
			for(int y = 1 ; y <= cnVertical ; y++) {
				oMap[x][y] = false;
			}
		}
		
		for(int i = 0 ; i < snakes.size() ; i++) {
			if (snakes.get(i).active == 1) {
				for (int j = 0 ; j < snakes.get(i).body.items.size() ; j++) {
					oMap[snakes.get(i).body.items.get(j).cellX][snakes.get(i).body.items.get(j).cellY] = true;			
				}
			}
		}
		
		if (walls != null && walls.items.size() > 0) {
			for(int i = 0 ; i < walls.items.size() ; i++) {
				if (walls.items.get(i).type != Block.BLOCK_EXIT) {
					oMap[walls.items.get(i).x][walls.items.get(i).y] = true;
				}
			}
		}
	}	
	
	public void makeBoom(float x, float y, Paint bPaint) {
		if (booms == null) {
			booms = new ArrayList<ParticleList>();
		}
		
		ParticleList particles = new ParticleList();
		
		for(int i = 0 ; i < 30 ; i++) {			
			Particle particle = new Particle(x, y);
			if (bPaint != null) {
				particle.paint = bPaint;
			}				
			particle.setXYDestination((float)Math.random() * sWidth, (float)Math.random() * sHeight, 30);
			particles.add(particle);
		}		
		
		booms.add(particles);
	}

	public void freezeSnakes(int race) {
		freezeStartTime = System.currentTimeMillis();
		for(int i = 0 ; i < snakes.size() ; i++) {
			if (snakes.get(i).race != race) {
				snakes.get(i).live = 0;
			}
		}
	}
	
	public void unFreezeSnakes() {
		freezeStartTime = 0;
		for(int i = 0 ; i < snakes.size() ; i++) {
			snakes.get(i).live = 1;
		}
	}
	
	public void purgeSnakes() {
		List<Snake> toRemove = new ArrayList<Snake>();
		for(int i = 0 ; i < snakes.size() ; i++) {
			if (snakes.get(i).active == 0 && snakes.get(i).race != Snake.RACE_PLAYER) {
				toRemove.add(snakes.get(i));
			}
		}
		
		if (toRemove.size() > 0) {
			for(int i = 0 ; i < toRemove.size() ; i++) {
				snakes.remove(toRemove.get(i));
			}
		}
	}

	public void unlockKeyHole(int cellX, int cellY) {
		Block block = walls.unlockFirstLockedSlot();		
		if (block != null) {
			
		}
	}

	public void blowUpLocks() {
		for(int i = 0 ; i < walls.items.size() ; i++) {
			if (walls.items.get(i).type == Block.BLOCK_KEYHOLE) {
				walls.currLocks -= 1;
				walls.totalLocks -= 1;
				walls.items.get(i).type = Block.BLOCK_EXIT;
				
				makeBoom(getCellCenterX(walls.items.get(i).x), getCellCenterY(walls.items.get(i).y), walls.items.get(i).starPaint);
				
			}
		}
		
		rebuildObstMap();		
	}

	public boolean backAction() {
		return false;
	}

	public int getScore() {
		return -1;
	}

	public void refresh() {
		// TODO Auto-generated method stub		
	}
	
	public void reselect() {
		// TODO Auto-generated method stub		
	}
	
	
}