package com.tbc.playarea.collections.lru;

/**
 * @author parthp
 * Class Implements LRUCache.
 * 
 * Following operations are supported:
 * 	1. put: Content of generic type T is added to cache
 * 	2. get: Content is retrieved from the cache.
 * 
 * Cache is implemented as a Doubly Linked List (DLL) implemented as queue.
 * 
 * The implementation follows due to the fact that the most recently 
 * used content is always kept at the front of the list and the least 
 * recently used content is kept at the end of the list ready to be 
 * removed in case of an overflow. 
 * This is ensured by having both get and put operations set the current
 * content at head and put operation deleting the data at the tail in case
 * of an overflow.
 */
public class LRUCache<T> {
	/**
	 * Maximum capacity of the cache
	 */
	private int MAX_CAPACITY;
	
	/**
	 * Current size of the cache
	 */
	private int size;
	
	/**
	 * DLL nodes representing the front and end of list
	 * respectively
	 */
	private LRUNode<T> head;
	private LRUNode<T> tail;
	
	/**
	 * Constructor initializing an empty DLL with maximum capacity
	 * @param mAX_CAPACITY maximum size of the DLL which implements the cache
	 */
	public LRUCache(int mAX_CAPACITY) {
		super();
		MAX_CAPACITY = mAX_CAPACITY;
		size = 0;
		head = null;
		tail = null;
	}
	
	/**
	 * retrieves the node in the DLL which matches the content queried upon
	 * @param content
	 * @return DLL node matching content null if no matching content is found
	 */
	public LRUNode<T> getNode(T content) {
		LRUNode<T> node = head;
		while(node.next != null) {
			if(node != null && node.getContent().equals(content)) 
				return node;
			node = node.next;
		}
		return null;
	}
	
	/**
	 * implements operation to retrieve matching content from cache
	 * If the content is present in the cache it is moved to the head
	 * of the DLL implementing the cache
	 * @param content
	 * @return matching content from cache null if no matching content is found
	 */
	public T get(T content) {
		LRUNode<T> node = getNode(content);
		if(node == null) {
			System.out.println("No entry in cache with content: " + content);
			return null;
		} else {
			if(node != head) {
				remove(node);
				setHead(node);
			}
			return node.getContent();
		}
	}
	
	/**
	 * implements operation to store content in the cache
	 * If content is already present in the cache it is moved to
	 * the head of the DLL implementing the cache
	 * If there is an overflow content at the tail is removed and
	 * the new incoming content is set as the head of the DLL implementing the cache
	 * @param content
	 */
	public void put(T content) {
		LRUNode<T> node = getNode(content);
		if(node != null) {
			if(node != head) {
				remove(node);
				setHead(node);
			}
		} else {
			node = new LRUNode<T>(content);
			if(size >= MAX_CAPACITY)
				remove(tail); 
			else
				size++;
			
			setHead(node);
		}
	}

	/**
	 * sets the current node as the head of the DLL implementing the cache
	 * implementation is private and is abstracted from end user
	 * @param node
	 */
	private void setHead(LRUNode<T> node) {
		node.next = head;
		node.prev = null;
		if(head != null)
			head.prev = node;
		head = node;
		if(tail == null)
			tail = head;
	}

	/**
	 * removes current node from the DLL implementing the cache
	 * implementation is private and is abstracted from end user
	 * @param node
	 */
	private void remove(LRUNode<T> node) {
		if(node.prev != null)
			node.prev.next = node.next;
		else
			head = node.next;
		
		if(node.next != null)
			node.next.prev = node.prev;
		else
			tail = node.prev;
	}
}
