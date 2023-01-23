package com.bc.service;

import java.util.List;

import com.bc.exception.CategoryException;
import com.bc.exception.ProductException;
import com.bc.model.Category;
import com.bc.model.Product;

public interface CategoryService {
	
	
	public Category createCategory(String c) throws CategoryException;
	
	
	public Product addCategory(Integer productId , Integer categoryId) throws ProductException,CategoryException; 
	
	public List<Category> viewAllCategory() throws CategoryException;
	
	public String deleteCategory(Integer catId) throws CategoryException;
	
	
	public List<Product> productByCategory(Integer catId) throws CategoryException,ProductException;

}
