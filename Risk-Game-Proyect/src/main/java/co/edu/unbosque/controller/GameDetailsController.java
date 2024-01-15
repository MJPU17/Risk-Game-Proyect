package co.edu.unbosque.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.GameDetails;
import co.edu.unbosque.service.GameDetailsService;
import co.edu.unbosque.util.MyDoubleLinkedList;
import jakarta.transaction.Transactional;

/**
 * Class that controls all operations to be performed with the GameDetails class.
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Juan Pablo
 *         Urrego Cortez, Jeisson Nicolas Uyaban Prieto
 */
@RestController
@RequestMapping("/gamedetails")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "*" })
@Transactional
public class GameDetailsController {

	@Autowired
	private GameDetailsService gameDetailsServ;

	/**
	 * Creates an object of class GameDetailsController
	 */
	public GameDetailsController() {
	}
	
	/**
	 * Creates an object of class GameDetails in the data base
	 * @param gameDetails Set details to be created
	 * @return Response entity indicating whether the game details were created successfully
	 */
	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createGameDetails(@RequestBody GameDetails gameDetails) {
		int status = gameDetailsServ.create(gameDetails);
		if (status == 0) {
			return new ResponseEntity<String>("GameDetails successfully create.", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("GameDetails unsuccessfully create.", HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Gets the juice details of the specified hash
	 * @param hash hash of gameDetails
	 * @return If the game details were found
	 */
	@GetMapping(path = "/getbyhash/{hash}")
	public ResponseEntity<String> getByHashCode(@PathVariable String hash) {
		if (!gameDetailsServ.existHashCode(hash)) {
			return new ResponseEntity<String>("Hash not found", HttpStatus.NOT_FOUND);
		}
		MyDoubleLinkedList<GameDetails> gameDetails = gameDetailsServ.getAll();
		if (gameDetails.isEmpty()) {
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}
		StringBuffer buf = new StringBuffer();
		for (GameDetails current : gameDetails) {
			if (hash.equals(current.getHashCode())) {
				buf.append(current.getId() + ",");
				buf.append(current.getPhase() + ",");
				buf.append(current.getPlayerTurn() + ",");
				buf.append(current.getGameInfo());
				break;
			}
		}
		return new ResponseEntity<String>(buf.toString(), HttpStatus.ACCEPTED);
	}

	/**
	 * Deletes game details with the specified hash
	 * @param hash hash of the gamedetails
	 * @return If the game details were deleted successfully
	 */
	@DeleteMapping(path = "/deletebyhash/{hash}")
	public ResponseEntity<String> deleteByHashCode(@PathVariable String hash) {
		int status = gameDetailsServ.deleteByHashCode(hash);
		if (status == 1) {
			return new ResponseEntity<String>("Hash not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Delete successfully.", HttpStatus.ACCEPTED);
	}
	
	/**
	 * Gets the last id plus one from the database.
	 * 
	 * @return last id plus one from the database.
	 */
	@GetMapping(path = "/getlastid")
	public ResponseEntity<String> getLastId() {
		return new ResponseEntity<String>(Long.toString(gameDetailsServ.getLastId()), HttpStatus.ACCEPTED);
	}
	
	/**
	 * Gets all game hash codes
	 * 
	 * @return all game hash codes
	 */
	@GetMapping(path = "/getallhashcode")
	public ResponseEntity<String> getAllHashCode() {
		MyDoubleLinkedList<String> allHashCode = gameDetailsServ.getAllHashCode();
		if (allHashCode.isEmpty()) {
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}
		StringBuffer buf = new StringBuffer();
		for (String hash : allHashCode) {
			buf.append(hash + ",");
		}
		return new ResponseEntity<String>(buf.substring(0, buf.length() - 1), HttpStatus.ACCEPTED);
	}
	
	/**
	 * If the specified hascode exists.
	 * 
	 * @param hash The hascode to verify
	 * @return true if it exists, false if it does not
	 */
	@GetMapping(path = "/existhash/{hash}")
	public ResponseEntity<String> existHash(@PathVariable String hash) {
		return new ResponseEntity<String>(gameDetailsServ.existHashCode(hash) ? "Yes" : "No", HttpStatus.ACCEPTED);
	}

	/**
	 * Generate a hashcode for a game
	 * @param password Password needed to generate the hash
	 * @return Game hashcode
	 */
	@GetMapping(path = "/generatehash/{password}")
	public ResponseEntity<String> generateHash(@PathVariable int password) {
		String texto = Integer.toString(password);
		String hashCode = "";
		do {
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				byte[] hash = digest.digest(texto.getBytes(StandardCharsets.UTF_8));
				String generated=Base64.getUrlEncoder().encodeToString(hash);
				if(generated.length()>=12) {
					hashCode=generated.substring(0,12);
				}
			} catch (NoSuchAlgorithmException e) {
				hashCode = "AE3456";
			}
			if (gameDetailsServ.existHashCode(hashCode)) {
				SecureRandom sr=new SecureRandom();
				texto=sr.nextInt(10)+texto;
			}
		} while (gameDetailsServ.existHashCode(hashCode));
		return new ResponseEntity<String>(hashCode,HttpStatus.OK);
	}

}
