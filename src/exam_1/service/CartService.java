package exam_1.service;

import exam_1.model.CartItem;
import exam_1.model.Catalog;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class CartService implements IService<CartItem, Integer> {
	
	private List<CartItem> listCart = new ArrayList<>();
	
	@Override
	public List<CartItem> getAll() {
		return listCart;
	}
	
	@Override
	public void save(CartItem cartItem) {
		if (findById(cartItem.getCartItemId()) == null) {
			listCart.add(cartItem);
			System.out.println("THÊM THÀNH CÔNG");
		} else {
			listCart.set(listCart.indexOf(findById(cartItem.getCartItemId())), cartItem);
			System.out.println("SỬA THÀNH CÔNG");
		}
	}
	
	public void IncrementCartItem(CartItem cartItem) {
		cartItem.setQuantity(cartItem.getQuantity() + 1);
	}
	
	public void DecrementCartItem(CartItem cartItem) {
		cartItem.setQuantity(cartItem.getQuantity() - 1);
	}
	
	public void IncrementCartItemByNumber(CartItem cartItem, int n) {
		cartItem.setQuantity(cartItem.getQuantity() + n);
	}
	
	public void DecrementCartItemByNumber(CartItem cartItem, int n) {
		cartItem.setQuantity(cartItem.getQuantity() - n);
	}
	
	@Override
	public CartItem findById(Integer integer) {
		for (CartItem c : listCart) {
			if (c.getCartItemId() == integer) {
				return c;
			}
		}
		return null;
	}
	
	@Override
	public void delete(Integer integer) {
		if (findById(integer) != null) {
			listCart.remove(findById(integer));
			System.out.println("XÓA THÀNH CÔNG");
		} else {
			System.err.println("Không có sản phẩm này trong giỏ hàng");
		}
	}
	
	public void deleteAll() {
		listCart.clear();
	}
	
	public int getNewId() {
		int idMax = 0;
		for (CartItem c : listCart) {
			if (c.getCartItemId() > idMax) {
				idMax = c.getCartItemId();
			}
		}
		return idMax + 1;
	}
}
