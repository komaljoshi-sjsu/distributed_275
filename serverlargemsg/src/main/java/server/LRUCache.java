package server;


import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LRUCache {
	static class Node {
		String key;
		JSONArray value;
		Node next;
		Node prev;
		Node(String key, JSONArray value) {
			this.key = key;
			this.value = value;
		}
	}
	
	static Node  head, tail;
	static int capacity;
	static int currentSize;
	static Map<String, Node> cache = new HashMap<>();
	static JSONArray emptyarr = new JSONArray();
	
	public LRUCache(int capacity, int currentSize) {
		this.capacity = capacity;
		this.currentSize = currentSize;
		cache = new HashMap<>();
		head = new Node("Head",null);
		tail = new Node("Tail",null);
		head.next = tail;
		tail.prev = head;
	}
	
	public static synchronized void add(Node node) {
		currentSize++;
		Node prevHead = head.next;
		node.prev = head;
		node.next = prevHead;
		prevHead.prev = node;
		head.next = node;
		cache.put(node.key,node);
	}
	
	public static synchronized void remove(Node node) {
        Node pre = node.prev;
        Node nxt = node.next;
        pre.next = nxt;
        if(nxt!=null) {
            nxt.prev = pre;
        }
        cache.remove(node.key);
        currentSize--;
    }
	
	public static void put(String key, JSONArray value) {
        //System.out.println("Putting key "+key+" val "+value);
        Node node = new Node(key,value);
        
        if(cache.containsKey(key)) {
            remove(cache.get(key));
        }  else if(currentSize==capacity) {
            
            remove(tail.prev);
        }
        add(node);
        
    }
	
	public static JSONArray get(String key) {
        if(cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            add(node);
            return node.value;
        }
        return emptyarr;
    }
	
	

}
