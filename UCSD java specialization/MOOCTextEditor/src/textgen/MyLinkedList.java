package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		size = 0;
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		elementCheck(element);
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> prevNode = tail.prev;
		prevNode.next = newNode;
		newNode.prev = prevNode;
		newNode.next = tail;
		tail.prev = newNode;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		rangeCheck(index);
		LLNode<E> node = head.next;
		for(int i=0; i< index; i++)
			node = node.next;
		return node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
        if (index > this.size() | index < 0)
            throw new IndexOutOfBoundsException();
		elementCheck(element);
		LLNode<E> node = head.next;
		for(int i=0; i< index; i++) 
			node = node.next;
		LLNode<E> prevNode = node.prev;
		LLNode<E> newNode = new LLNode<E>(element);
		prevNode.next = newNode;
		newNode.prev = prevNode;
		newNode.next = node;
		node.prev = newNode;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		size = 0;
		LLNode<E> node = head.next;
		while(node != tail) {
			node = node.next;
			size ++;
		}
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		rangeCheck(index);
		LLNode<E> node = head.next;
		for(int i=0; i< index; i++) 
			node = node.next;
		LLNode<E> prevNode = node.prev;
		prevNode.next = node.next;
		node.next.prev = prevNode;
		return node.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		rangeCheck(index);
		elementCheck(element);
		LLNode<E> node = head.next;
		E data;
		for(int i=0; i< index; i++) 
			node = node.next;
		data = node.data;
		node.data = element;
		return data;
	}   
	
	private void rangeCheck(int index) {
        if (index >= this.size() | index < 0)
            throw new IndexOutOfBoundsException();
    }
	
	private void elementCheck(E element) {
		if (element == null)
			throw new NullPointerException();
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
