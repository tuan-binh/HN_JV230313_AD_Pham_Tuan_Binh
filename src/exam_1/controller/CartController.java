package exam_1.controller;

import exam_1.model.CartItem;
import exam_1.service.CartService;

import java.util.List;

public class CartController implements IController<CartItem, Integer> {
	
	private CartService cartService = new CartService();
	
	@Override
	public List<CartItem> getAll() {
		return cartService.getAll();
	}
	
	@Override
	public void save(CartItem cartItem) {
		cartService.save(cartItem);
	}
	
	public void incrementCartItem(CartItem cartItem) {
		cartService.IncrementCartItem(cartItem);
	}
	
	public void decrementCartItem(CartItem cartItem) {
		cartService.DecrementCartItem(cartItem);
	}
	
	public void incrementCartItemByNumber(CartItem cartItem, int n) {
		cartService.IncrementCartItemByNumber(cartItem, n);
	}
	
	public void decrementCartItemByNumber(CartItem cartItem, int n) {
		cartService.DecrementCartItemByNumber(cartItem, n);
	}
	
	@Override
	public CartItem findById(Integer integer) {
		return cartService.findById(integer);
	}
	
	@Override
	public void delete(Integer integer) {
		cartService.delete(integer);
	}
	
	public void deleteAll() {
		cartService.deleteAll();
	}
	
	public int getNewId() {
		return cartService.getNewId();
	}
}
