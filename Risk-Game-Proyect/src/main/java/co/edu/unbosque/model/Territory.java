package co.edu.unbosque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Class that represents the territories within the game and is responsible for
 * managing and storing all the information related to them.
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Juan Pablo
 *         Urrego Cortez, Jeisson Nicolas Uyaban Prieto
 */
@Entity
@Table(name = "territorytable")
public class Territory {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private String hashCode;
	private String name;
	private String color;
	private int numberTroops;
	private int playerIndex;

	/**
	 * Creates an object of class Territory
	 */
	public Territory() {
	}

	/**
	 * Creates an object of class Territory
	 * 
	 * @param hashCode     Hash code of the game the territory belongs to
	 * @param name         Name of territory
	 * @param color        Color of the territory
	 * @param numberTroops Number of troops in the territory
	 * @param playerIndex  Index of player to which the territory belongs
	 */
	public Territory(String hashCode, String name, String color, int numberTroops, int playerIndex) {
		this.hashCode = hashCode;
		this.name = name;
		this.color = color;
		this.numberTroops = numberTroops;
		this.playerIndex = playerIndex;
	}

	/**
	 * Gets the id of the territory
	 * 
	 * @return id of the territory
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Assigns the id of the territory
	 * 
	 * @param id id of the territory
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the hash code of the game to which the territory belongs.
	 * 
	 * @return hash code of the game to which the territory belongs.
	 */
	public String getHashCode() {
		return hashCode;
	}

	/**
	 * Assigns the hash code of the set the territory belongs to
	 * 
	 * @param hashCode hash code of the set the territory belongs to
	 */
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	/**
	 * Gets the name of the territory
	 * 
	 * @return name of the territory
	 */
	public String getName() {
		return name;
	}

	/**
	 * Assigns the name of the territory
	 * 
	 * @param name name of the territory
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtains the color of the territory
	 * 
	 * @return color of the territory
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Assigns the color of the territory
	 * 
	 * @param color color of the territory
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Gets the number of troops in the territory.
	 * 
	 * @return number of troops in the territory.
	 */
	public int getNumberTroops() {
		return numberTroops;
	}

	/**
	 * Allocates the number of troops in the territory
	 * 
	 * @param numberTroops number of troops in the territory
	 */
	public void setNumberTroops(int numberTroops) {
		this.numberTroops = numberTroops;
	}

	/**
	 * Gets the index of the player to which the territory belongs.
	 * 
	 * @return index of the player to which the territory belongs.
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	/**
	 * Assigns the index of the player to whom the territory belongs
	 * 
	 * @param playerIndex index of the player to whom the territory belongs
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

}
