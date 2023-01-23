package com.bc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bc.exception.CategoryException;
import com.bc.exception.ProductException;
import com.bc.model.Category;
import com.bc.model.Product;
import com.bc.service.CategoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
	public ResponseEntity<List<Category>> viewAllCategory() throws CategoryException{
		return new ResponseEntity<>(categoryService.viewAllCategory(),HttpStatus.OK);
		
	};
	
	@PostMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Product> addCategory(@RequestParam Integer productId ,@RequestBody Integer categoryId) throws ProductException,CategoryException{
		return new ResponseEntity<Product>(categoryService.addCategory(productId, categoryId),HttpStatus.OK);
	}; 
	
	@PostMapping("/add/{category}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Category> createCategory(@PathVariable("category") String c) throws CategoryException{
		return new ResponseEntity<Category>(categoryService.createCategory(c),HttpStatus.OK);
	};
	
	@DeleteMapping("/delete/{cid}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCategory(@PathVariable("cid") Integer catId) throws CategoryException{
		return new ResponseEntity<String>(categoryService.deleteCategory(catId),HttpStatus.OK);
	};
	
	@GetMapping("/products/{cid}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
	public ResponseEntity<List<Product>> productByCategory(@PathVariable("cid") Integer catId) throws CategoryException,ProductException{
		return new ResponseEntity<List<Product>>(categoryService.productByCategory(catId),HttpStatus.OK);
	};

}
