package com.bc.service;

import com.bc.exception.CartException;
import com.bc.exception.CustomerException;
import com.bc.exception.ProductException;
import com.bc.model.Cart;

public interface CartService {

	public Cart addProductToCart(Long customerId, Integer productId)
			throws CartException, CustomerException, ProductException;

	public Cart removeProductFromCart(Long customerId, Integer productId)
			throws CartException, CustomerException, ProductException;

	public Cart removeAllProduct(Long customerId) throws CartException, CustomerException;

	public Cart increaseProductQuantity(Long customerId, Integer productId)
			throws CartException, CustomerException, ProductException;

	public Cart decreaseProductQuantity(Long customerId, Integer productId)
			throws CartException, CustomerException, ProductException;
	
	public Cart viewCart(Long cartId) throws CartException;

}
