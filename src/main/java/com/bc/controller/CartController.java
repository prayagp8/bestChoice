package com.bc.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bc.exception.CartException;
import com.bc.exception.CustomerException;
import com.bc.exception.ProductException;
import com.bc.model.Cart;
import com.bc.model.Product;
import com.bc.service.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cService;
	
	@PostMapping("/cart")
	public ResponseEntity<Cart> addProductToCart(@RequestParam("customerId") Integer cId , @RequestParam("productId") Integer productId  ) throws CartException, CustomerException, ProductException{
		return new ResponseEntity<Cart>(cService.addProductToCart(cId, productId),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/cart/{cId}/{pId}")
	public ResponseEntity<Cart> removeProductFromCart(@PathVariable Integer cId, @PathVariable Integer pId) throws CartException, CustomerException, ProductException{
		
		return new ResponseEntity<Cart>(cService.removeProductFromCart(cId, pId),HttpStatus.OK);
	}
	
	
	@DeleteMapping("cart/{cId}")
	public ResponseEntity<Cart> removeAllProduct(@PathVariable("cId") Integer cId) throws CartException, CustomerException{
		
		return new ResponseEntity<Cart>(cService.removeAllProduct(cId),HttpStatus.OK);
	}
	
	@PutMapping("/cart/icart/{cId}/{pId}")
	public ResponseEntity<Cart> increaseProductQuantity(@PathVariable Integer cId, @PathVariable Integer pId) throws CartException, CustomerException, ProductException{
		
		return new ResponseEntity<Cart>(cService.increaseProductQuantity(cId, pId),HttpStatus.OK);
	}
	
	
	@PutMapping("/cart/dcart/{cId}/{pId}")
	public ResponseEntity<Cart> decreaseProductQuantity(@PathVariable Integer cId, @PathVariable Integer pId) throws CartException, CustomerException, ProductException{
		
		return new ResponseEntity<Cart>(cService.decreaseProductQuantity(cId, pId),HttpStatus.OK);
	}
}
