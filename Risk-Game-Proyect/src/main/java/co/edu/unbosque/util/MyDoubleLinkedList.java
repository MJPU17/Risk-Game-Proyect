package co.edu.unbosque.util;


import java.util.Iterator;

/**
 * Class that implements a doubly linked list.
 * @param <T> The type of data that is stored in the list.
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Juan Pablo
 *         Urrego Cortez, Jeisson Nicolas Uyaban Prieto
 */
public class MyDoubleLinkedList<T> implements Iterable<T>{
	
	protected DNode<T> head,current;
	protected int size,position;
	
	/**
	 * Create an object of class MyDoubleLinkedList
	 */
	public MyDoubleLinkedList() {
		position=-1;
		size=0;
	}
	
	/**
	 * Gets the initial node of the list.
	 * @return the initial node of the list.
	 */
	public DNode<T> getHead() {
		return head;
	}
	
	/**
	 * Assigns the initial node of the list.
	 * @param head the initial node of the list.
	 */
	public void setHead(DNode<T> head) {
		this.head = head;
	}
	
	/**
	 * Gets the current node in the list.
	 * @return the current node in the list.
	 */
	public DNode<T> getCurrent() {
		return current;
	}
	
	/**
	 * Assigns the current node in the list.
	 * @param current the current node in the list.
	 */
	public void setCurrent(DNode<T> current) {
		this.current = current;
	}
	
	/**
	 * Gets the current size of the list.
	 * @return Gets the current size of the list.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Assigns the current size of the list.
	 * @param size size of the list.
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Gets the current position of the pointer in the list.
	 * @return position of the pointer in the list.
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * Assigns the current position of the pointer in the list.
	 * @param position current position of the pointer in the list.
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
	 * Moves the pointer forward in the list a specified number of positions.
	 * @param numposition Number of positions to advance
	 */
	public void forward(int numposition) {
		if(numposition>0 && this.head!=null) {
			int positionForward=numposition;
			if(this.current==null) {
				current=head;
				positionForward--;
				position++;
			}
			while(positionForward>0&&this.current.getNext()!=null) {
				current=current.getNext();
				positionForward--;
				position++;
			}
		}
	}
	
	/**
	 * Moves the pointer back in the list a specified number of positions.
	 * @param numpostion Number of positions to be moved back.
	 */
	public void backward(int numpostion) {
		if(numpostion<=0 || head==null || current==null) return;
		int positionBackward=numpostion;
		while(this.current!=null && positionBackward>0) {
			current=current.getPrevious();
			positionBackward--;
			position--;
		}
	}
	
	/**
	 * Inserts a node to the list
	 * @param info node information
	 */
	public void insert(T info) {
		DNode<T> temp=new DNode<T>(info);
		if(this.current==null) {
			temp.setNext(head);
			if(this.head!=null)head.setPrevious(temp);
			head=temp;
		}
		else {
			temp.setNext(current.getNext());
			temp.setPrevious(current);
			if(current.getNext()!=null) current.getNext().setPrevious(temp);
			current.setNext(temp);
		}
		current=temp;
		position++;
		size++;
	}
	
	/**
	 * Removes a node from the list
	 * @return Node information deleted
	 */
	public T extract() {
		T info=null;
		if(this.current!=null) {
			info=current.getInfo();
			if(this.head==this.current)head=current.getNext();
			else current.getPrevious().setNext(current.getNext());
			if(this.current.getNext()!=null)current.getNext().setPrevious(current.getPrevious());
			current=current.getNext();
			if(size-1==position)position=-1;
			size--;
		}
		return info;
	}
	
	/**
	 * Removes a node at the specified position
	 * @param index Position to eliminate
	 * @return Node information deleted
	 */
	public T remove(int index) {
		if(position>index) backward(Math.abs(position-index));
		else forward(Math.abs(position-index));
		return extract();
	}
	
	/**
	 * Put a node at the top of the list
	 * @param info Node information
	 */
	public void addInit(T info) {
		backward(size);
		insert(info);
	}
	
	/**
	 * Add a node at the end of the list
	 * @param info Node information
	 */
	public void add(T info) {
		forward(size);
		insert(info);
	}
	
	/**
	 * Adds a node at the specified position
	 * @param index Node position
	 * @param info Node information
	 */
	public void add(int index,T info) {
		index-=1;
		if(position>index) backward(Math.abs(position-index));
		else forward(Math.abs(position-index));
		insert(info);
	}
	
	/**
	 * Gets the node at the specified position
	 * @param index Node position
	 * @return Information of the node
	 */
	public T get(int index) {
		if(position>index) backward(Math.abs(position-index));
		else forward(Math.abs(position-index));
		return current.getInfo();
	}
	
	/**
	 * Updates the node information at the specified position.
	 * @param index Node position
	 * @param info Node information
	 */
	public void set(int index, T info) {
		if(position>index) backward(Math.abs(position-index));
		else forward(Math.abs(position-index));
		current.setInfo(info);
	}
	
	/**
	 * Return whether the list is empty or not.
	 * @return True if empty, false if not
	 */
	public boolean isEmpty() {
		return size==0;
	}
	
	@Override
	public String toString() {
		DNode<T> tempNode = head;
		StringBuilder sb = new StringBuilder();
		while(tempNode != null) {
			sb.append(tempNode.getInfo());
			if(tempNode.getNext() != null) sb.append("<-->");
			tempNode=tempNode.getNext();
		}
		return sb.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			int index=0;
			
			@Override
			public boolean hasNext() {
				return index<size;
			}

			@Override
			public T next() {
				return get(index++);
			}
		};
	}

}
