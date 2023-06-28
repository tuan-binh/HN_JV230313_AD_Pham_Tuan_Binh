package exam_1.run;

import exam_1.config.Inputmethods;
import exam_1.controller.CatalogController;
import exam_1.controller.ProductController;
import exam_1.model.Product;

import java.util.Collections;

public class ProductManager {
	private ProductController productController;
	private CatalogController catalogController;
	
	public ProductManager(ProductController productController, CatalogController catalogController) {
		this.productController = productController;
		this.catalogController = catalogController;
		while (true) {
			System.out.println("********************PRODUCT-MANAGEMENT********************");
			System.out.println("1. Nhập số sản sản phẩm và nhập thông tin sản phẩm\n" +
					  "2. Hiển thị thông tin các sản phẩm\n" +
					  "3. Sắp xếp sản phẩm theo giá giảm dần\n" +
					  "4. Xóa sản phẩm theo mã\n" +
					  "5. Tìm kiếm sách theo tên sách\n" +
					  "6. Thay đổi thông tin của sách theo mã sách\n" +
					  "7. Quay lại");
			System.out.print("Mời bạn lựa chọn: ");
			int choose = Inputmethods.getInteger();
			switch (choose) {
				case 1:
					addNewProduct();
					break;
				case 2:
					showListProduct();
					break;
				case 3:
					Collections.sort(productController.getAll());
					break;
				case 4:
					deleteProduct();
					break;
				case 5:
					searchProductByName();
					break;
				case 6:
					editProduct();
					break;
				case 7:
					return;
				default:
					System.err.println("Vui lòng nhập lại (1 -> 7)");
					break;
			}
		}
	}
	
	public void addNewProduct() {
		System.out.print("Bạn muốn nhập vào nhiêu sản phẩm: ");
		int n = Inputmethods.getInteger();
		for (int i = 0; i < n; i++) {
			System.out.println("Sản phẩm thứ " + (i + 1));
			Product product = new Product();
			product.setProductId(productController.getNewId());
			product.inputData(catalogController.getAll());
			productController.save(product);
		}
	}
	
	public void showListProduct() {
		if (productController.getAll().size() == 0) {
			System.err.println("Không có sản phẩm nào");
			return;
		}
		for (Product p : productController.getAll()) {
			System.out.println(p);
		}
	}
	
	public void deleteProduct() {
		System.out.print("Nhập vào Mã sản phẩm: ");
		String id = Inputmethods.getString();
		productController.delete(id);
	}
	
	public void searchProductByName() {
		boolean flag = false;
		System.out.print("Nhập vào tên sách bạn muốn tìm: ");
		String text = Inputmethods.getString();
		for (Product p : productController.getAll()) {
			if (p.getProductName().toLowerCase().contains(text.toLowerCase())) {
				System.out.println(p);
				flag = true;
			}
		}
		if (!flag) {
			System.err.println("Không có sản phẩm nào");
		}
	}
	
	public void editProduct() {
		System.out.print("Nhập vào Mã sản phẩm: ");
		String id = Inputmethods.getString();
		Product product = productController.findById(id);
		if (product == null) {
			System.err.println("Không có sản phẩm bạn muốn tìm");
			return;
		}
		Product newProduct = new Product();
		newProduct.setProductId(product.getProductId());
		newProduct.inputData(catalogController.getAll());
		productController.save(newProduct);
	}
	
}
