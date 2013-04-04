package com.example.sandbox;

public class SnakeBug {

	int cellX;
	int cellY;
	
	int eatenBy = 0;
	
	int race;
	int active = 1;
	
	public SnakeBug(int cX, int cY) {
		cellX = cX;
		cellY = cY;
	}
	
	public void setRace(int bRace) {
		race = bRace;
	}

}
