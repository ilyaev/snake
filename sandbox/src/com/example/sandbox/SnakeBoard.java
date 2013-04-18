package com.example.sandbox;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class SnakeBoard {
	
	SnakeBoxSurface surface;
	
	final static int GAMEMODE_SOLO = 1;
	final static int GAMEMODE_BATTLE = 2;
	final static int GAMEMODE_SURVIVAL = 3;

	int gameMode = 1;

	public int sWidth;
	public int sHeight;
	
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
	
	public Canvas canvas;
	
	long lastTimeStamp = 0;

	public long gameOverCountdown = 0;
	
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
	}	
	
	public void makeBoom(float x, float y) {
		if (booms == null) {
			booms = new ArrayList<ParticleList>();
		}
		
		ParticleList particles = new ParticleList();
		
		for(int i = 0 ; i < 30 ; i++) {			
			Particle particle = new Particle(x, y);
			particle.setXYDestination((float)Math.random() * sWidth, (float)Math.random() * sHeight, 30);
			particles.add(particle);
		}		
		
		booms.add(particles);
	}
}