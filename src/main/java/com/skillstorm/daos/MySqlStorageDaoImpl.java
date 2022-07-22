package com.skillstorm.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.skillstorm.conf.warehouseCreds;
import com.skillstorm.models.Storage;

public class MySqlStorageDaoImpl implements StorageDao {

	@Override
	public List<Storage> findAll() {
		String sql = "SELECT * FROM storage";

		try (Connection conn = warehouseCreds.getInstance().getConnection()) {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			LinkedList<Storage> currStorage = new LinkedList<>();
			while(rs.next()) {
				// Looping over individual rows of the result set
				Storage storage = new Storage(rs.getInt("id"), rs.getString("itemName"),rs.getInt("itemSize"),rs.getInt("quantity"),rs.getString("itemDesc"));
				currStorage.add(storage);
			}

			return currStorage;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Storage findById(int itemId) {
		String sql = "SELECT * FROM storage WHERE id = ?";
		try (Connection conn = warehouseCreds.getInstance().getConnection()) {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, itemId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Storage(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public Storage findByName(String itemName) {
		String sql = "SELECT * FROM Storage WHERE itemName = ?";
		try (Connection conn = warehouseCreds.getInstance().getConnection()) {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, itemName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Storage(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public Storage save(Storage storage) {

		String sql = "INSERT INTO storage (itemName,itemSize,quantity,itemDesc) VALUES (?, ?, ?,?)";
		try (Connection conn = warehouseCreds.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, storage.getItemName());
			ps.setInt(2, storage.getItemSize());
			ps.setInt(3, storage.getQuantity());
			ps.setString(4, storage.getItemDesc());

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected != 0) {
				return storage;
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void update(Storage storage) {
		String sql = "UPDATE Storage set itemName = ? setItemSize = ? setQuantity = ? setItemDesc" ;
		try (Connection conn = warehouseCreds.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, storage.getItemName());
			ps.setInt(2,storage.getItemSize() );
			ps.setInt(3,storage.getQuantity() );
			ps.setString(4, storage.getItemDesc());
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected != 0) {
				ps.close();
				System.out.println("Item with id added: " + storage.getId());
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	@Override
	public void delete(Storage storage) {
		String sql = "DELETE FROM Storage WHERE idName = ?" ;
		try (Connection conn = warehouseCreds.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			String id = storage.getItemName();
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected != 0) {
				ps.close();
				System.out.println("Item deleted"+id);
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	@Override
	public void delete(int itemId) {
		String sql = "DELETE FROM Storage WHERE id = ?" ;
		try (Connection conn = warehouseCreds.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


			int rowsAffected = ps.executeUpdate();
			if (rowsAffected != 0) {
				ps.close();
				System.out.println("Item deleted");
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMany(int[] itemIds) {
		// TODO Auto-generated method stub

	}


}
