package com.bc.service;

import com.bc.exception.CartException;
import com.bc.exception.CustomerException;
import com.bc.exception.ProductException;
import com.bc.model.Cart;
import com.bc.model.Product;

public interface CartService {
	
	
	public Cart addProductToCart(Integer customerId, Product product) throws CartException,CustomerException,ProductException;
	
	public Cart removeProductFromCart(Integer customerId, Integer productId) throws CartException,CustomerException,ProductException;
	
	public Cart removeAllProduct(Integer customerId)throws CartException,CustomerException ;
	
	public Cart increaseProductQuantity(Integer customerId,Integer productId)throws CartException,CustomerException,ProductException;
	
	public Cart decreaseProductQuantity(Integer customerId, Integer productId)throws CartException,CustomerException,ProductException;
	
//	customerId kyu le raha ye pahle check karega kya customer hai already acha or agar customer nahi hai hai to exception throw karega
//	na customer ka bhi wahi bol raha hu mai
	

}