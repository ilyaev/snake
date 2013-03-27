package com.example.sandbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortedNodeList {
	
	List<Node> list = new ArrayList<Node>();
	
	public Node first() {
		return list.get(0);
	}
	
	public void clear() {
		list.clear();
	}
	
	public void add(Node o) {
		list.add(o);
		Collections.sort(list);
	}
	
	public void remove(Node o) {
		list.remove(o);
	}
	
	public int size() {
		return list.size();
	}
	
	public boolean contains(Node o) {
		return list.contains(o);
	}
}