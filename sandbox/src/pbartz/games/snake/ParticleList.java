package pbartz.games.snake;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

public class ParticleList {
	
	int active = 1;
	
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
	
	public void calculate() {
		if (active == 1) {
			for(int i = 0 ; i < items.size() ; i++) {
				if (items.get(i).isActive) {
					items.get(i).calculate(1,1);
				} else {
					active = 0;
					break;
				}
			}
		}
	}
	
	public void draw(Canvas tCanvas) {
		if (active == 1) {
			for(int i = 0 ; i < items.size() ; i++) {
				items.get(i).draw(tCanvas);
			}
		}
	}
	
}
