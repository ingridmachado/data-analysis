package br.com.agibank.model;

public class Sale {
	private String id;
	private String saleId;
	private Item[] items;
	private String salesmanName;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSaleId() {
		return saleId;
	}
	
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	
	public Item[] getItems() {
		return items;
	}
	
	public void setItems(Item[] items) {
		this.items = items;
	}
	
	public String getSalesmanName() {
		return salesmanName;
	}
	
	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}
}