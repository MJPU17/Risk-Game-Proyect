package co.edu.unbosque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * This class represents the players in the game and is responsible for managing
 * and storing all player information.
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Juan Pablo
 *         Urrego Cortez, Jeisson Nicolas Uyaban Prieto
 */
@Entity
@Table(name = "playertable")
public class Player {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private String hashCode;
	private String name;
	private String color;
	private String email;
	private int indexPlayer;

	/**
	 * Creates a player class object.
	 */
	public Player() {
	}

	/**
	 * Creates a player class object.
	 * 
	 * @param hashCode    Hash code of the game the player belongs to
	 * @param name        Player user name
	 * @param color       Color of player territories
	 * @param email       Player mail
	 * @param indexPlayer Index of the player in the list of players in the game
	 */
	public Player(String hashCode, String name, String color, String email, int indexPlayer) {
		this.hashCode = hashCode;
		this.name = name;
		this.color = color;
		this.email = email;
		this.indexPlayer = indexPlayer;
	}

	/**
	 * Gets player id
	 * 
	 * @return Player id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gives a value to the player id
	 * 
	 * @param id Player id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the hash code of the game to which the player belongs
	 * 
	 * @return Hash code game
	 */
	public String getHashCode() {
		return hashCode;
	}

	/**
	 * Assigns the hash code of the game to which the player belongs
	 * 
	 * @param hashCode Has code game
	 */
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	/**
	 * Returns the player user name
	 * 
	 * @return Player username
	 */
	public String getName() {
		return name;
	}

	/**
	 * Assigns a player user name
	 * 
	 * @param name Player username
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the color of the player territories
	 * 
	 * @return color of the player territories
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Assigns a color to the player territories
	 * 
	 * @param color color of the player territories
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Gets the player email address
	 * 
	 * @return player email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Assigns a mail to the player
	 * 
	 * @param email Player mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the index within the list of players in the game
	 * 
	 * @return index within the list of players in the game
	 */
	public int getIndexPlayer() {
		return indexPlayer;
	}

	/**
	 * Assigns the index within the list of players in the game.
	 * 
	 * @param indexPlayer index within the list of players in the game
	 */
	public void setIndexPlayer(int indexPlayer) {
		this.indexPlayer = indexPlayer;
	}

}
