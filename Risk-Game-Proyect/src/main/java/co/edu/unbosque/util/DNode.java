package co.edu.unbosque.util;

/**
 * Class representing a doubly linked node.
 * @param <T> The type of data that is stored in the node.
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Juan Pablo
 *         Urrego Cortez, Jeisson Nicolas Uyaban Prieto
 */
public class DNode<T> {
	
	private DNode<T> previous,next;
	private T info;
	
	/**
	 * Create an object of class dnode
	 */
	public DNode() {}

	/**
	 * Create an object of class dnode
	 * @param previous The previous node.
	 * @param next The following node.
	 * @param info The value stored in the node.
	 */
	public DNode(DNode<T> previous, DNode<T> next, T info) {
		this.previous = previous;
		this.next = next;
		this.info = info;
	}
	
	/**
	 * Create an object of class dnode
	 * @param info The value stored in the node.
	 */
	public DNode(T info) {
		this(null,null,info);
	}
	
	/**
	 * Gets the previous node.
	 * @return the previous node.
	 */
	public DNode<T> getPrevious() {
		return previous;
	}
	
	/**
	 * Assigns the previous node.
	 * @param previous the previous node.
	 */
	public void setPrevious(DNode<T> previous) {
		this.previous = previous;
	}

	/**
	 * Gets the next node.
	 * @return the next node.
	 */
	public DNode<T> getNext() {
		return next;
	}
	
	/**
	 * Assigns the next node.
	 * @param next the next node.
	 */
	public void setNext(DNode<T> next) {
		this.next = next;
	}
	
	/**
	 * Gets the value stored in the node.
	 * @return the value stored in the node.
	 */
	public T getInfo() {
		return info;
	}
	
	/**
	 * Assigns the value stored in the node.
	 * @param info the value stored in the node.
	 */
	public void setInfo(T info) {
		this.info = info;
	}
	
	@Override
	public String toString() {
		String prev=(previous==null||previous.info==null?"null":previous.getInfo().toString());
		String nex=(next==null||next.info==null?"null":next.getInfo().toString());
		return "DNode [previous=" + prev + ", next=" + nex + ", info=" + info + "]";
	}

}
