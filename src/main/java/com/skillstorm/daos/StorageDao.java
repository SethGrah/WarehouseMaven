package com.skillstorm.daos;

import java.util.List;

import com.skillstorm.models.Storage;

public interface StorageDao {
	public List<Storage> findAll();
	public Storage findById(int itemId);
	public Storage findByName(String itemName);
	public Storage save(Storage storage);
	public void update(Storage storage); // contains the id and updates as needed
	public void delete(Storage storage);
	public void delete(int itemId);
	public void deleteMany(int[] itemIds);

}
