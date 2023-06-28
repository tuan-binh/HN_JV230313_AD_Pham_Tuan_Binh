package exam_1.controller;

import exam_1.model.Product;
import exam_1.service.ProductService;

import java.util.List;

public class ProductController implements IController<Product, String> {
	private ProductService productService = new ProductService();
	
	
	@Override
	public List<Product> getAll() {
		return productService.getAll();
	}
	
	@Override
	public void save(Product product) {
		productService.save(product);
	}
	
	public void incrementProduct(Product product) {
		productService.IncrementProduct(product);
	}
	
	public void decrementProduct(Product product) {
		productService.DecrementProduct(product);
	}
	
	public void incrementProductByNumber(Product product, int n) {
		productService.IncrementProductByNumber(product, n);
	}
	
	public void decrementProductByNumber(Product product, int n) {
		productService.DecrementProductByNumber(product, n);
	}
	
	@Override
	public Product findById(String s) {
		return productService.findById(s);
	}
	
	@Override
	public void delete(String s) {
		productService.delete(s);
	}
	
	public String getNewId() {
		return productService.getNewId();
	}
}
