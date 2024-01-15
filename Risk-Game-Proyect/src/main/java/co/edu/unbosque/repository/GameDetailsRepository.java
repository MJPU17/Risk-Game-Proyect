package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.model.GameDetails;

/**
 * Repository interface for the GameDetails entity using Spring Data JPA.
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Juan Pablo
 *         Urrego Cortez, Jeisson Nicolas Uyaban Prieto
 */
public interface GameDetailsRepository extends JpaRepository<GameDetails, Long> {

	/**
	 * Removes a record from the database based on the hashCode of the game.
	 * 
	 * @param hashCode hashCode of the game.
	 */
	public void deleteByHashCode(String hashCode);

}
