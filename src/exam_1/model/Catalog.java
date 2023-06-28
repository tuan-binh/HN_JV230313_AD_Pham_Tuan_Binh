package exam_1.model;

import exam_1.config.Inputmethods;

public class Catalog {
	private int catalogId;
	private String catalogName;
	
	public Catalog() {
	}
	
	public Catalog(int catalogId, String catalogName) {
		this.catalogId = catalogId;
		this.catalogName = catalogName;
	}
	
	public int getCatalogId() {
		return catalogId;
	}
	
	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}
	
	public String getCatalogName() {
		return catalogName;
	}
	
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	
	public void inputData() {
		System.out.print("Nhập tên danh mục: ");
		this.catalogName = Inputmethods.getString();
	}
	
	@Override
	public String toString() {
		return "ID: " + catalogId + " | Catalog: " + catalogName;
	}
}
