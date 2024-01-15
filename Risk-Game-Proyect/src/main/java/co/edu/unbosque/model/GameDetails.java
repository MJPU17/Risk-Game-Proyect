package co.edu.unbosque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Class in charge of storing all the game details necessary to load a game.
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Juan Pablo
 *         Urrego Cortez, Jeisson Nicolas Uyaban Prieto
 */
@Entity
@Table(name = "gamedetails")
public class GameDetails {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	public String hashCode;
	public String phase;
	public int playerTurn;
	private String gameInfo;

	/**
	 * Create an object of class GameDetails
	 */
	public GameDetails() {
	}

	/**
	 * Create an object of class GameDetails
	 * 
	 * @param hashCode   hash code to which game details belong
	 * @param phase      Phase at which the game was saved
	 * @param playerTurn Player turn in which the game was left
	 * @param gameInfo   Extra details needed to load some stages of the game
	 */
	public GameDetails(String hashCode, String phase, int playerTurn, String gameInfo) {
		this.hashCode = hashCode;
		this.phase = phase;
		this.playerTurn = playerTurn;
		this.gameInfo = gameInfo;
	}

	/**
	 * Gets the id of the game details.
	 * 
	 * @return id of the game details
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Assigns the id of the game details
	 * 
	 * @param id id of the game details
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the hash code to which the game details belong.
	 * 
	 * @return hash code to which the game details belong
	 */
	public String getHashCode() {
		return hashCode;
	}

	/**
	 * Assigns the hash code to which the set details belong
	 * 
	 * @param hashCode hash code to which the set details belong
	 */
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	/**
	 * Gets the phase in which the game is located.
	 * 
	 * @return phase in which the game is located.
	 */
	public String getPhase() {
		return phase;
	}

	/**
	 * Assigns the phase the game is in.
	 * 
	 * @param phase phase the game is in.
	 */
	public void setPhase(String phase) {
		this.phase = phase;
	}

	/**
	 * Gets the player turn when the game was abandoned
	 * 
	 * @return player turn when the game was abandoned
	 */
	public int getPlayerTurn() {
		return playerTurn;
	}

	/**
	 * Gets the player turn when the game was abandoned
	 * 
	 * @param playerTurn player turn when the game was abandoned
	 */
	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	/**
	 * Gets additional details needed to load some game stages
	 * 
	 * @return additional details needed to load some game stages
	 */
	public String getGameInfo() {
		return gameInfo;
	}

	/**
	 * Assigns additional details needed to load some game stages
	 * 
	 * @param gameInfo additional details needed to load some game stages
	 */
	public void setGameInfo(String gameInfo) {
		this.gameInfo = gameInfo;
	}

}
