package br.com.agibank.model;

import java.util.List;

public class Sale {
	private String saleId;
	private List<Item> items;
	private Seller seller;
	
	public double getTotal() {
		double total = 0.0;
		
		for (Item item : items) {
			total += item.getPrice();
		}
		
		return total;
	}
	
	public String getSaleId() {
		return saleId;
	}
	
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
}