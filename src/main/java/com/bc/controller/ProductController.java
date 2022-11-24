package com.bc.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bc.exception.ProductException;
import com.bc.model.Product;
import com.bc.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService pService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> viewAllProduct() throws ProductException{
		return new ResponseEntity<List<Product>>(pService.viewAllProduct(),HttpStatus.OK);
	}
	

	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product p) throws ProductException{
		 Product product = pService.addProduct(p);
		 return new ResponseEntity<Product>(product,HttpStatus.OK);
		 
	}
	
	@PutMapping("/products")
	public ResponseEntity<Product> updateProduct(@RequestBody Product p) throws ProductException{
		
		  Product product= pService.updateProduct(p);
		  return new ResponseEntity<Product>(product,HttpStatus.OK);
		  
	}
	
	@GetMapping("/product")
	public ResponseEntity<List<Product>> viewProduct() throws ProductException{
		return new  ResponseEntity<List<Product>>(pService.viewAllProduct(),HttpStatus.OK);
	}
	
	@GetMapping("/products/{categoryId}")
	public ResponseEntity<List<Product>> viewProductByCategory(@PathVariable("categoryId") Integer categoryId ) throws ProductException{
		
		return new ResponseEntity<List<Product>>(pService.viewProductByCategory(categoryId),HttpStatus.OK);
	}
	
	@DeleteMapping("/products/{pId}")
	public ResponseEntity<Product> removeProduct(@PathVariable("pId") Integer pId) throws ProductException{
		
		return new ResponseEntity<Product>(pService.removeProduct(pId),HttpStatus.OK);
		
	}
	
}
