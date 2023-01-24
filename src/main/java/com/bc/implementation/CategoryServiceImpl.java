package com.bc.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bc.exception.CategoryException;
import com.bc.exception.ProductException;
import com.bc.model.Category;
import com.bc.model.Product;
import com.bc.repository.CategoryRepo;
import com.bc.repository.ProductRepo;
import com.bc.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	private ProductRepo pRepo;

	@Override
	public Category createCategory(String c) throws CategoryException {
		
		
		 List<Category> categoryList = categoryRepo.findAll();
		 if(categoryList.size()!=0) {
			 for(int i =0;i<categoryList.size();i++) {
				 if(categoryList.get(i).getCategoryName().equalsIgnoreCase(c)) {
					 throw new CategoryException("category is already added!");
				 }
			 }
		 }
		Category category = new Category();
		category.setCategoryName(c);
		category.setProductList(new ArrayList<>());
		Category newCategory  = categoryRepo.save(category);
		
		if(newCategory!=null) {
			return newCategory ;
		}else {
			throw new CategoryException("category not added");
		}

	}
	
	
	
	@Override
	public Product addCategory(Integer productId ,Integer categoryId) throws ProductException, CategoryException {
		Product p = pRepo.findById(productId).orElseThrow(() -> new ProductException("Product not found"));
		
		Optional<Category> category = categoryRepo.findById(categoryId);
		if(category.isPresent()) {
			p.setCategory(category.get());
			pRepo.save(p);
		}else {
			throw new CategoryException("category not exist");
		}
		
		return p;
	}

	@Override
	public List<Category> viewAllCategory() throws CategoryException {
		List<Category> categoryList = categoryRepo.findAll();
		
		if(categoryList.size()>0) {
			return categoryList;
		}else {
			throw new CategoryException("no category is available");
		}
		
	}



	@Override
	public String deleteCategory(Integer catId) throws CategoryException {
		 Optional<Category> c= categoryRepo.findById(catId);
		 
		 if(c.isPresent()) {
			 categoryRepo.delete(c.get());
			 return c.get().getCategoryName()+" "+"is deleted";
		 }else {
			 throw new CategoryException("category not found!");
		 }
		
	}



	@Override
	public List<Product> productByCategory(Integer catId) throws CategoryException, ProductException {
		 Optional<Category> c= categoryRepo.findById(catId);
		 if(c.isPresent()) {
			List<Product> productList = c.get().getProductList();
			if(productList.size()>0) {
				return productList;
			}else {
				throw new ProductException("no product found for this category!");
			}
			
		 }else {
			 throw new CategoryException("category not found!");
		 }
	}

}
