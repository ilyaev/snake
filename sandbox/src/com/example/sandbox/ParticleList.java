package com.example.sandbox;

import java.util.ArrayList;
import java.util.List;

public class ParticleList {
	
	List<Particle> items;
	
	public ParticleList() {
		items = new ArrayList<Particle>();
	}
	
	public void add(Particle particle) {
		items.add(particle);
	}
	
	public void add(float cX, float cY) {
		items.add(new Particle(cX, cY));
	}
	
}
