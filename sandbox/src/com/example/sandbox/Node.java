package com.example.sandbox;

public class Node implements Comparable<Node> {
	int x;
	int y;
	float cost;
	float heuristic;
	Node parent;
	int depth;
	
	public Node(int sX, int sY) {
		x = sX;
		y = sY;
	}
	
	public int setParent(Node nParent) {
		depth = nParent.depth + 1;
		parent = nParent;
		
		return depth;
	}


	@Override
	public int compareTo(Node another) {
		float f = heuristic + cost;
		float otherF = another.heuristic + another.cost;
		
		if (f < otherF) {
			return -1;
		} else if (f > otherF) {
			return 1;
		}
		
		return 0;
		
	}
	
	
	
	
	
}
