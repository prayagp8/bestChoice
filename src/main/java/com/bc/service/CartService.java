package com.bc.service;

import com.bc.exception.CartException;
import com.bc.model.Cart;
import com.bc.model.Product;

public interface CartService {
	
	
	public Cart addProductToCart(Integer customerId, Product product) throws CartException;
	
	public Cart removeProductFromCart(Integer customerId, Integer productId) throws CartException;
	
	public Cart removeAllProduct(Integer customerId)throws CartException ;
	
	public Cart increaseProductQuantity(Integer customerId,Integer productId)throws CartException;
	
	public Cart decreaseProductQuantity(Integer customerId, Integer productId)throws CartException;
	
	

}
