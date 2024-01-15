package co.edu.unbosque.controller;

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

import co.edu.unbosque.model.Player;
import co.edu.unbosque.service.PlayerService;
import co.edu.unbosque.util.MyDoubleLinkedList;
import jakarta.transaction.Transactional;
/**
 * Class that controls all operations to be performed with the player class.
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Juan Pablo
 *         Urrego Cortez, Jeisson Nicolas Uyaban Prieto
 */
@RestController
@RequestMapping("/player")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8081","*"})
@Transactional
public class PlayerController {
	
	@Autowired
	private PlayerService playerServ;
	
	/**
	 * Creates an object of class PlayerController controller
	 */
	public PlayerController() {}
	
	/**
	 * Creates an object of class player in the data base
	 * @param player Player to be created
	 * @return Response entity indicating whether the player were created successfully
	 */
	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createPlayer(@RequestBody Player player){
		int status=playerServ.create(player);
		if(status==0) {
			return new ResponseEntity<String>("Player successfully create.",HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Player unsuccessfully create.",HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Gets all players of the game specified hash
	 * @param hash hash of players
	 * @return If the players were found
	 */
	@GetMapping(path = "/getbyhash/{hash}")
	public ResponseEntity<String> getAllByHashCode(@PathVariable String hash){
		if(!playerServ.existHashCode(hash)) {
			return new ResponseEntity<String>("Hash not found", HttpStatus.NOT_FOUND);
		}
		MyDoubleLinkedList<Player> players=playerServ.getAll();
		if(players.isEmpty()) {
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}
		StringBuffer buf=new StringBuffer();
		for(Player current :players) {
			if(hash.equals(current.getHashCode())) {
				buf.append(current.getId()+",");
				buf.append(current.getName()+",");
				buf.append(current.getColor()+",");
				buf.append(current.getEmail()+",");
				buf.append(current.getIndexPlayer()+";");
			}
		}
		return new ResponseEntity<String>(buf.substring(0, buf.length()-1),HttpStatus.ACCEPTED);
	}
	
	/**
	 * Delete all players of the game hash
	 * @param hash hash of players
	 * @return If the players delete successfully
	 */
	@DeleteMapping(path = "/deletebyhash/{hash}")
	public ResponseEntity<String> deleteByHashCode(@PathVariable String hash) {
		int status=playerServ.deleteByHashCode(hash);
		if(status==1) {
			return new ResponseEntity<String>("Hash not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Delete successfully.", HttpStatus.ACCEPTED);
	}
	
}
