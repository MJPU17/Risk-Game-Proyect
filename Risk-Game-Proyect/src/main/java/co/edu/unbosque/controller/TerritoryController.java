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

import co.edu.unbosque.model.Territory;
import co.edu.unbosque.service.TerritoryService;
import co.edu.unbosque.util.MyDoubleLinkedList;
import jakarta.transaction.Transactional;

/**
 * Class that controls all operations to be performed with the Territory class.
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Juan Pablo
 *         Urrego Cortez, Jeisson Nicolas Uyaban Prieto
 */
@RestController
@RequestMapping("/territory")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8081","*"})
@Transactional
public class TerritoryController {
	
	@Autowired
	private TerritoryService territoryServ;
	
	/**
	 * Creates an object of class  TerritoryController
	 */
	public TerritoryController() {}
	
	/**
	 * Creates an object of class territory in the data base
	 * @param territory Territory to be created
	 * @return Response entity indicating whether the territory were created successfully
	 */
	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createTerritory(@RequestBody Territory territory){
		int status=territoryServ.create(territory);
		if(status==0) {
			return new ResponseEntity<String>("Territory successfully create.",HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Territory unsuccessfully create.",HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Gets all territories of the game specified hash
	 * @param hash hash of territories
	 * @return If the territories were found
	 */
	@GetMapping(path = "/getbyhash/{hash}")
	public ResponseEntity<String> getAllByHashCode(@PathVariable String hash){
		if(!territoryServ.existHashCode(hash)) {
			return new ResponseEntity<String>("Hash not found", HttpStatus.NOT_FOUND);
		}
		MyDoubleLinkedList<Territory> territorys=territoryServ.getAll();
		if(territorys.isEmpty()) {
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}
		StringBuffer buf=new StringBuffer();
		for(Territory current :territorys) {
			if(hash.equals(current.getHashCode())) {
				buf.append(current.getId()+",");
				buf.append(current.getName()+",");
				buf.append(current.getColor()+",");
				buf.append(current.getNumberTroops()+",");
				buf.append(current.getPlayerIndex()+";");
			}
		}
		return new ResponseEntity<String>(buf.substring(0, buf.length()-1),HttpStatus.ACCEPTED);
	}
	
	/**
	 * Delete all territories of the game hash
	 * @param hash hash of territories
	 * @return If the territories delete successfully
	 */
	@DeleteMapping(path = "/deletebyhash/{hash}")
	public ResponseEntity<String> deleteByHashCode(@PathVariable String hash) {
		int status=territoryServ.deleteByHashCode(hash);
		if(status==1) {
			return new ResponseEntity<String>("Hash not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Delete successfully.", HttpStatus.ACCEPTED);
	}
	
	
}
