/**
 * 
 */
package com.tbc.playarea.collections.lru;

/**
 * @author parthp
 * Class Represent A Doubly Linked List Node which will be used by the LRUCache
 */
public class LRUNode<T> {
	
	/**
	 * Content to be stored in cache of generic type
	 */
	private T content;
	
	/**
	 * Links to the prev and next nodes in the cache
	 */
	protected LRUNode<T> prev;
	protected LRUNode<T> next;
	
	/**
	 * Constructor for initializing Doubly Linked list node
	 * @param content
	 */
	public LRUNode(T content) {
		super();
		this.content = content;
		this.prev = null;
		this.next = null;
	}

	/**
	 * returns content of generic type T
	 * @return content
	 */
	public T getContent() {
		return content;
	}

	/**
	 * sets the content of the node
	 * @param content
	 */
	public void setContent(T content) {
		this.content = content;
	}
}
