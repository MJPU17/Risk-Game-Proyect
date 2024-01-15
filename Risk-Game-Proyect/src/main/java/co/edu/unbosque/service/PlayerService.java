package co.edu.unbosque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Player;
import co.edu.unbosque.repository.PlayerRepository;
import co.edu.unbosque.util.MyDoubleLinkedList;

/**
 * Service that provides CRUD operations for the Player entity.
 * 
 * This service is responsible for interacting with the PlayerRepository layer
 * to perform CRUD operations on the database related to the Player entity.
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Juan Pablo
 *         Urrego Cortez, Jeisson Nicolas Uyaban Prieto
 */
@Service
public class PlayerService implements CRUDOperations<Player> {

	@Autowired
	private PlayerRepository playerRepo;

	public PlayerService() {
	}

	@Override
	public int create(Player data) {
		playerRepo.save(data);
		return 0;
	}

	@Override
	public MyDoubleLinkedList<Player> getAll() {
		MyDoubleLinkedList<Player> players = new MyDoubleLinkedList<>();
		for (Player player : playerRepo.findAll()) {
			players.add(player);
		}
		return players;
	}

	@Override
	public long count() {
		return playerRepo.count();
	}

	@Override
	public int deleteByHashCode(String hashCode) {
		if (existHashCode(hashCode)) {
			playerRepo.deleteByHashCode(hashCode);
			return 0;
		}
		return 1;
	}

	@Override
	public MyDoubleLinkedList<String> getAllHashCode() {
		MyDoubleLinkedList<String> hashCodes = new MyDoubleLinkedList<>();
		boolean add;
		for (Player player : playerRepo.findAll()) {
			add = true;
			for (String hash : hashCodes) {
				if (hash == player.getHashCode()) {
					add = false;
					break;
				}
			}
			if (add) {
				hashCodes.add(player.getHashCode());
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

}
