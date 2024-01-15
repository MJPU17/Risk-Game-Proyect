package co.edu.unbosque.service;

import co.edu.unbosque.util.MyDoubleLinkedList;

/**
 * Interface describing the basic operations of each entity service,
 * 
 * @param <T> Type of object or entity to be stored
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Juan Pablo
 *         Urrego Cortez, Jeisson Nicolas Uyaban Prieto
 */
public interface CRUDOperations<T> {

	/**
	 * Creates an object and stores it in the database
	 * 
	 * @param data Object to be saved
	 * @return 0 if successfully created, other number if not
	 */
	public int create(T data);

	/**
	 * Gets a list with all stored objects.
	 * 
	 * @return list with all stored objects.
	 */
	public MyDoubleLinkedList<T> getAll();

	/**
	 * Gets all game hash codes
	 * 
	 * @return all game hash codes
	 */
	public MyDoubleLinkedList<String> getAllHashCode();

	/**
	 * Deletes all objects with the specified hascode.
	 * 
	 * @param hashCode Hashcode of items to be deleted
	 * @return 0 if successfully deleted, other number if not
	 */
	public int deleteByHashCode(String hashCode);

	/**
	 * If the specified hascode exists.
	 * 
	 * @param searchHash The hascode to verify
	 * @return true if it exists, false if it does not
	 */
	public boolean existHashCode(String searchHash);

	/**
	 * The object count exists within the database.
	 * 
	 * @return total objects in the database
	 */
	public long count();
}