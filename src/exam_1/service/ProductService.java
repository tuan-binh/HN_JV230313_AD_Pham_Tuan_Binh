package exam_1.service;

import exam_1.model.CartItem;
import exam_1.model.Product;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IService<Product, String> {
	
	private List<Product> products = new ArrayList<>();
	
	@Override
	public List<Product> getAll() {
		return products;
	}
	
	@Override
	public void save(Product product) {
		if (findById(product.getProductId()) == null) {
			products.add(product);
			System.out.println("THÊM THÀNH CÔNG");
		} else {
			products.set(products.indexOf(findById(product.getProductId())), product);
			System.out.println("SỬA THÀNH CÔNG");
		}
	}
	
	public void IncrementProduct(Product product) {
		product.setStock(product.getStock() + 1);
	}
	
	public void DecrementProduct(Product product) {
		product.setStock(product.getStock() - 1);
	}
	
	public void IncrementProductByNumber(Product product, int n) {
		product.setStock(product.getStock() + n);
	}
	
	public void DecrementProductByNumber(Product product, int n) {
		product.setStock(product.getStock() - n);
	}
	
	@Override
	public Product findById(String s) {
		for (Product p : products) {
			if (p.getProductId().equals(s)) {
				return p;
			}
		}
		return null;
	}
	
	@Override
	public void delete(String s) {
		if (findById(s) != null) {
			products.remove(findById(s));
			System.out.println("XÓA THÀNH CÔNG");
		} else {
			System.err.println("Không có sản phẩm này");
		}
	}
	
	public String getNewId() {
		String id = "P";
		int idMax = 0;
		for (Product product : products) {
			if (product != null) {
				int productId = Integer.parseInt(product.getProductId().replace("P", "0"));
				if (idMax < productId) {
					idMax = productId;
				}
			}
		}
		idMax += 1;
		String newId = Integer.toString(idMax);
		if (newId.length() == 1) {
			id += "0" + 0 + 0 + newId;
		}
		if (newId.length() == 2) {
			id += "0" + 0 + newId;
		}
		if (newId.length() == 3) {
			id = "0" + newId;
		}
		if (newId.length() == 4) {
			id = newId;
		}
		return id;
	}
}
