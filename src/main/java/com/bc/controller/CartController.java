package com.bc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bc.exception.CartException;
import com.bc.exception.CustomerException;
import com.bc.exception.ProductException;
import com.bc.model.Cart;
import com.bc.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cService;

	@PostMapping("/add")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Cart> addProductToCart(@RequestParam("customerId") Long cId,
			@RequestParam("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.addProductToCart(cId, productId), HttpStatus.OK);

	}

	@DeleteMapping("/remove/{cartId}/{productId}")
	 @PreAuthorize("hasRole('USER')")
	public ResponseEntity<Cart> removeProductFromCart(@PathVariable("cartId") Long cartId,
			@PathVariable("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.removeProductFromCart(cartId, productId), HttpStatus.OK);
	}

	@DeleteMapping("/remove/{cartId}")
	 @PreAuthorize("hasRole('USER')")
	public ResponseEntity<Cart> removeAllProduct(@PathVariable("cartId") Long cartId)
			throws CartException, CustomerException {
		return new ResponseEntity<Cart>(cService.removeAllProduct(cartId), HttpStatus.OK);
	}

	@PutMapping("/increase/{cartId}/{productId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Cart> increaseProductQuantity(@PathVariable("cartId") Long cartId,
			@PathVariable("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.increaseProductQuantity(cartId, productId), HttpStatus.OK);
	}

	@PutMapping("/decrease/{cartId}/{productId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Cart> decreaseProductQuantity(@PathVariable("cartId") Long cartId,
			@PathVariable("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.decreaseProductQuantity(cartId, productId), HttpStatus.OK);
	}
	
	@GetMapping("/view/{cid}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Cart> viewCart( @PathVariable("cid") Long customerId) throws CartException{
		return new ResponseEntity<Cart>(cService.viewCart(customerId),HttpStatus.OK);
	};
}
