package com.skillstorm.models;

public class Storage {
	private int id;
	private String itemName;
	private int itemSize;
	private int quantity;
	private String itemDesc;

	public Storage() {

	}


	public Storage(int id, String itemName, int itemSize, int quantity) {
		this.id = id;
		this.itemName = itemName;
		this.itemSize = itemSize;
		this.quantity = quantity;
	}


	public Storage(int id, String itemName, int itemSize, int quantity, String itemDesc) {
		this.id = id;
		this.itemName = itemName;
		this.itemSize = itemSize;
		this.quantity = quantity;
		this.itemDesc = itemDesc;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemSize() {
		return itemSize;
	}
	public void setItemSize(int itemSize) {
		this.itemSize = itemSize;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	@Override
	public String toString() {
		return "Storage [id=" + id + ", itemName=" + itemName + ", itemSize=" + itemSize + ", quantity=" + quantity
				+ ", itemDesc=" + itemDesc + "]";
	}



}
