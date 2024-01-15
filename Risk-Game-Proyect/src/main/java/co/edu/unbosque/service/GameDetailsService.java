package co.edu.unbosque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.GameDetails;
import co.edu.unbosque.repository.GameDetailsRepository;
import co.edu.unbosque.util.MyDoubleLinkedList;

/**
 * Service that provides CRUD operations for the GameDetails entity.
 * 
 * This service is responsible for interacting with the GameDetailsRepository
 * layer to perform CRUD operations on the database related to the GameDetails
 * entity.
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Juan Pablo
 *         Urrego Cortez, Jeisson Nicolas Uyaban Prieto
 */
@Service
public class GameDetailsService implements CRUDOperations<GameDetails> {

	@Autowired
	public GameDetailsRepository gameDetailsRepo;

	public GameDetailsService() {
	}

	@Override
	public int create(GameDetails data) {
		gameDetailsRepo.save(data);
		return 0;
	}

	@Override
	public MyDoubleLinkedList<GameDetails> getAll() {
		MyDoubleLinkedList<GameDetails> GameDetailss = new MyDoubleLinkedList<>();
		for (GameDetails GameDetails : gameDetailsRepo.findAll()) {
			GameDetailss.add(GameDetails);
		}
		return GameDetailss;
	}

	@Override
	public long count() {
		return gameDetailsRepo.count();
	}

	@Override
	public int deleteByHashCode(String hashCode) {
		if (existHashCode(hashCode)) {
			gameDetailsRepo.deleteByHashCode(hashCode);
			return 0;
		}
		return 1;
	}

	@Override
	public MyDoubleLinkedList<String> getAllHashCode() {
		MyDoubleLinkedList<String> hashCodes = new MyDoubleLinkedList<>();
		boolean add;
		for (GameDetails gameDetails : gameDetailsRepo.findAll()) {
			add = true;
			for (String hash : hashCodes) {
				if (hash.equals(gameDetails.getHashCode())) {
					add = false;
					break;
				}
			}
			if (add) {
				hashCodes.add(gameDetails.getHashCode());
			}
		}
		return hashCodes;
	}

	@Override
	public boolean existHashCode(String searchHash) {
		for (String hash : getAllHashCode()) {
			if (hash.equals(searchHash))
				return true;
		}
		return false;
	}

	/**
	 * Gets the last id plus one from the database.
	 * 
	 * @return last id plus one from the database.
	 */
	public long getLastId() {
		MyDoubleLinkedList<GameDetails> list = getAll();
		if (list.isEmpty()) {
			return 1;
		} else {
			return list.get(list.getSize() - 1).getId() + 1;
		}
	}

}
