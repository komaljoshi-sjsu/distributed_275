package server;


import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LRUCache {
	class Node {
		String key;
		JSONArray value;
		Node next;
		Node prev;
		Node(String key, JSONArray value) {
			this.key = key;
			this.value = value;
		}
	}
	
	Node head, tail;
	int capacity, currentSize;
	Map<String, Node> cache;
	
	public LRUCache(int capacity, int currentSize) {
		this.capacity = capacity;
		this.currentSize = currentSize;
		cache = new HashMap<>();
		head = new Node("Head",null);
		tail = new Node("Tail",null);
		head.next = tail;
		tail.prev = head;
	}
	
	public void add(Node node) {
		this.currentSize++;
		Node prevHead = head.next;
		node.prev = head;
		node.next = prevHead;
		prevHead.prev = node;
		head.next = node;
		cache.put(node.key,node);
	}
	
	public void remove(Node node) {
        Node pre = node.prev;
        Node nxt = node.next;
        pre.next = nxt;
        if(nxt!=null) {
            nxt.prev = pre;
        }
        cache.remove(node.key);
        this.currentSize--;
    }
	
	public void put(String key, JSONArray value) {
        //System.out.println("Putting key "+key+" val "+value);
        Node node = new Node(key,value);
        
        if(cache.containsKey(key)) {
            remove(cache.get(key));
        }  else if(this.currentSize==capacity) {
            //System.out.println("Size overflow... removing "+tail.prev.key);
            
            remove(tail.prev);
        }
        add(node);
        
    }
	
	

}
