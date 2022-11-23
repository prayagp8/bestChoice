package com.bc.service;

import com.bc.exception.CartException;
import com.bc.model.Cart;
import com.bc.model.Product;

public class CartServiceImpl implements CartService {

	@Override
	public Cart addProductToCart(Integer customerId, Product product) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart removeProductFromCart(Integer customerId, Integer productId) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart removeAllProduct(Integer customerId) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart increaseProductQuantity(Integer customerId, Integer productId) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart decreaseProductQuantity(Integer customerId, Integer productId) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

}
