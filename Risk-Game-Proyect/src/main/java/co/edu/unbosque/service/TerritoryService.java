package co.edu.unbosque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Territory;
import co.edu.unbosque.repository.TerritoryRepository;
import co.edu.unbosque.util.MyDoubleLinkedList;
/**
 * Service that provides CRUD operations for the Territory entity.
 * 
 * This service is responsible for interacting with the TerritoryRepository layer
 * to perform CRUD operations on the database related to the Territory entity.
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Juan Pablo
 *         Urrego Cortez, Jeisson Nicolas Uyaban Prieto
 */
@Service
public class TerritoryService implements CRUDOperations<Territory>{
	
	@Autowired
	private TerritoryRepository territoryRepo;
	
	public TerritoryService() {}

	@Override
	public int create(Territory data) {
		territoryRepo.save(data);
		return 0;
	}

	@Override
	public MyDoubleLinkedList<Territory> getAll() {
		MyDoubleLinkedList<Territory> territorys=new MyDoubleLinkedList<>();
		for(Territory territory: territoryRepo.findAll()) {
			territorys.add(territory);
		}
		return territorys;
	}

	@Override
	public MyDoubleLinkedList<String> getAllHashCode() {
		MyDoubleLinkedList<String> hashCodes=new MyDoubleLinkedList<>();
		boolean add;
		for(Territory territory: territoryRepo.findAll()) {
			add=true;
			for(String hash:hashCodes) {
				if(hash.equals(territory.getHashCode())) {
					add=false;
					break;
				}
			}
			if(add) {
				hashCodes.add(territory.getHashCode());
			}
		}
		return hashCodes;
	}

	@Override
	public int deleteByHashCode(String hashCode) {
		if(existHashCode(hashCode)) {
			territoryRepo.deleteByHashCode(hashCode);
			return 0;
		}
		return 1;
	}

	@Override
	public long count() {
		return territoryRepo.count();
	}
	
	@Override
	public boolean existHashCode(String searchHash) {
		for(String hash:getAllHashCode()) {
			if(hash.equals(searchHash))return true;
		}
		return false;
	}

}
