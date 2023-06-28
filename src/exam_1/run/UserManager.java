package exam_1.run;

import exam_1.config.Inputmethods;
import exam_1.controller.CartController;
import exam_1.controller.ProductController;
import exam_1.model.CartItem;
import exam_1.model.Product;

import java.util.List;

public class UserManager {
	private ProductController productController;
	private CartController cartController;
	
	public UserManager(ProductController productController, CartController cartController) {
		this.productController = productController;
		this.cartController = cartController;
		while (true) {
			System.out.println("**************************MENU-USER**************************");
			System.out.println("1. Xem danh sách sản phẩm\n" + "2. Thêm vào giỏ hàng\n" + "3. Xem tất cả sản phẩm giỏ hàng\n" + "4. Thay đổi số lượng sản phẩm trong giỏ hàng\n" + "5. Xóa 1 sản phẩm trong giỏ hàng\n" + "6. Xóa toàn bộ sản phẩm trong giỏ hàng\n" + "7. Quay lại\n");
			System.out.print("Mời bạn lựa chọn: ");
			int choose = Inputmethods.getInteger();
			switch (choose) {
				case 1:
					// show list products
					showAllProduct();
					break;
				case 2:
					// add product to cart
					addProductToCart();
					break;
				case 3:
					// show product in cart
					showMyCart();
					break;
				case 4:
					// change number product
					changeNumberProduct();
					break;
				case 5:
					// delete item product
					deleteItemProductInCart();
					break;
				case 6:
					// delete all product in cart
					clearAllProductInCart();
					break;
				case 7:
					return;
				default:
					System.err.println("Vui lòng nhập lại (1 -> 7)");
					break;
			}
		}
	}
	
	public void showAllProduct() {
		if (productController.getAll().size() == 0) {
			System.err.println("Không có sản phẩm nào");
			return;
		}
		for (Product product : productController.getAll()) {
			System.out.println(product);
		}
	}
	
	public void addProductToCart() {
		showMyCart();
		CartItem flag = null;
		System.out.print("Bạn muốn mua sản phẩm có ID là: ");
		String id = Inputmethods.getString();
		System.out.print("Bạn muốn mua bao nhiêu: ");
		int n = Inputmethods.getPosituveNumber();
		Product product = productController.findById(id);
		if (product == null) {
			System.err.println("Chúng tôi không có sản phẩm đó");
			return;
		}
		if (n <= product.getStock()) {
			for (CartItem cartItem : cartController.getAll()) {
				if (cartItem.getProduct().getProductId().equals(product.getProductId())) {
					flag = cartItem;
				}
			}
			if (flag != null) {
				cartController.incrementCartItemByNumber(flag, n);
			} else {
				CartItem cartItem = new CartItem();
				cartItem.setCartItemId(cartController.getNewId());
				cartItem.setProduct(product);
				cartItem.setPrice(product.getProductPrice());
				cartController.save(cartItem);
				cartController.incrementCartItemByNumber(cartItem, n - 1);
				productController.decrementProductByNumber(product, n);
			}
		} else {
			System.err.println("Chúng tôi không đủ số lượng");
		}
	}
	
	public void showMyCart() {
		if (cartController.getAll().size() == 0) {
			System.err.println("Giỏ hàng trống");
			return;
		}
		for (CartItem cartItem : cartController.getAll()) {
			System.out.println(cartItem);
		}
	}
	
	public void changeNumberProduct() {
		showMyCart();
		System.out.print("Bạn muốn chỉnh sưa sản phầm có ID là: ");
		int id = Inputmethods.getInteger();
		CartItem cartItem = cartController.findById(id);
		if (cartItem == null) {
			System.err.println("Không có sản phẩm này");
			return;
		}
		Product product = productController.findById(cartItem.getProduct().getProductId());
		if (product.getStock() == 0) {
			System.err.println("Sản phẩm đã hết hàng");
			return;
		}
		System.out.println(cartItem);
		
		System.out.println("============================");
		System.out.println("1. Thêm 1 sản phẩm");
		System.out.println("2. Giảm 1 sản phẩm");
		System.out.println("3. Thêm n sản phẩm");
		System.out.println("4. Xóa n sản phẩm");
		System.out.println("5. Thoát");
		System.out.print("Mời bạn lựa chọn: ");
		int choose = Inputmethods.getInteger();
		switch (choose) {
			case 1:
				if (product.getStock() > 0) {
					cartController.incrementCartItem(cartItem);
					productController.decrementProduct(product);
				} else {
					System.err.println("Sản phẩm đã hết");
				}
				break;
			case 2:
				if (cartItem.getQuantity() == 1) {
					cartController.delete(cartItem.getCartItemId());
					productController.incrementProduct(product);
				} else {
					cartController.decrementCartItem(cartItem);
					productController.incrementProduct(product);
				}
				break;
			case 3:
				changeProductById("UP", cartItem, product);
				break;
			case 4:
				changeProductById("DOWN", cartItem, product);
				break;
			case 5:
				break;
			default:
				System.err.println("Vui lòng nhập lại (1 -> 5)");
				break;
		}
		
		
	}
	
	public void changeProductById(String type, CartItem cartItem, Product product) {
		if (type.equals("UP")) {
			System.out.print("Bạn muốn thêm bao nhiêu sản phẩm: ");
			int n = Inputmethods.getPosituveNumber();
			if (n > product.getStock()) {
				System.err.println("Chúng tôi không đủ số lượng sản phẩm");
				return;
			}
			cartController.incrementCartItemByNumber(cartItem, n);
			productController.decrementProductByNumber(product, n);
		}
		if (type.equals("DOWN")) {
			System.out.print("Bạn muốn xóa bao nhiêu sản phẩm: ");
			int n = Inputmethods.getPosituveNumber();
			if (n > cartItem.getQuantity()) {
				System.err.println("Bạn không có nhiều như vậy");
				return;
			}
			cartController.decrementCartItemByNumber(cartItem, n);
			productController.incrementProductByNumber(product, n);
		}
	}
	
	
	public void deleteItemProductInCart() {
		System.out.println("Bạn muốn xóa sản phẩm có ID là: ");
		int id = Inputmethods.getInteger();
		CartItem cartItem = cartController.findById(id);
		if (cartItem == null) {
			return;
		}
		int quantity = cartItem.getQuantity();
		Product product = productController.findById(cartItem.getProduct().getProductId());
		for (int i = 0; i < quantity; i++) {
			productController.incrementProduct(product);
		}
		cartController.delete(id);
	}
	
	public void clearAllProductInCart() {
		List<CartItem> list = cartController.getAll();
		for (int i = 0; i < list.size(); i++) {
			Product product = productController.findById(list.get(i).getProduct().getProductId());
			for (int j = 0; j < list.get(i).getQuantity(); j++) {
				productController.incrementProduct(product);
			}
		}
		cartController.deleteAll();
		System.out.println("XÓA THÀNH CÔNG");
	}
	
}
