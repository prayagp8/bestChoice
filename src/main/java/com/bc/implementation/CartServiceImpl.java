package com.bc.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.exception.CartException;
import com.bc.exception.CustomerException;
import com.bc.exception.ProductException;
import com.bc.model.Cart;
import com.bc.model.Customer;
import com.bc.model.Product;
import com.bc.repository.CartRepo;
import com.bc.repository.CustomerRepo;
import com.bc.repository.ProductRepo;
import com.bc.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepo cRepo;

	@Autowired
	private CustomerRepo crRepo;

	@Autowired
	private ProductRepo pRepo;

	@Override
	public Cart addProductToCart(Long customerId, Integer productId)
			throws CartException, CustomerException, ProductException {
		Optional<Customer> opt = crRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("Customer not found!");

		Optional<Product> itemOpt = pRepo.findById(productId);
		if (itemOpt.isEmpty())
			throw new ProductException("Product not found!");

		Customer customer = opt.get();
		Cart cart = customer.getCart();

		List<Product> itemList = cart.getProducts();
		
		if(itemList.size()!=0) {
			
			for(int i=0;i<itemList.size();i++) {
				Product p = itemList.get(i);
				if(p.getProductId()==productId) {
					throw new ProductException("product already in the cart!");
				}
			}
			
		
				itemList.add(itemOpt.get());
				cart.setProduct_quantity(cart.getProduct_quantity()+1);
			
		}else {
			itemList.add(itemOpt.get());
			cart.setProduct_quantity(1);
		}
		

		cRepo.save(cart);
		return cart;

	}

	@Override
	public Cart removeProductFromCart(Long customerId, Integer productId)
			throws CartException, CustomerException, ProductException {
		Optional<Customer> opt = crRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("Customer not found!");

		Optional<Product> itemOpt = pRepo.findById(productId);
		if (itemOpt.isEmpty())
			throw new ProductException("Product not found!");
		Customer customer = opt.get();
		Cart cart = customer.getCart();
		List<Product> itemList = cart.getProducts();
		boolean flag = false;
		for (int i = 0; i < itemList.size(); i++) {
			Product element = itemList.get(i);
			if (element.getProductId() == productId) {
				itemList.remove(element);
				flag = true;
				break;
			}
		}
		if (!flag) {
			throw new CartException("Product not removed from cart");
		}
		cart.setProducts(itemList);
		cRepo.save(cart);
		return cart;

	}

	@Override
	public Cart removeAllProduct(Long customerId) throws CartException, CustomerException {
		Optional<Customer> opt = crRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("Customer not found!");
		Cart c = opt.get().getCart();
		if (c == null) {
			throw new CartException("cart not found");
		}
		c.getProducts().clear();
		return cRepo.save(c);

	}

	@Override
	public Cart increaseProductQuantity(Long customerId, Integer productId)
			throws CartException, CustomerException, ProductException {
		Optional<Customer> opt = crRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("Customer not found!");

		Optional<Product> itemOpt = pRepo.findById(productId);
		if (itemOpt.isEmpty())
			throw new ProductException("Product not found!");

		Customer customer = opt.get();
		Cart cart = customer.getCart();
		List<Product> itemList = cart.getProducts();
		boolean flag = true;
		for (int i = 0; i < itemList.size(); i++) {
			Product element = itemList.get(i);
			if (element.getProductId() == productId) {
				cart.setProduct_quantity(cart.getProduct_quantity() + 1);
				flag = false;
			}
		}
		if (flag) {
			cart.getProducts().add(itemOpt.get());
		}

		cRepo.save(cart);
		return cart;

	}

	@Override
	public Cart decreaseProductQuantity(Long customerId, Integer productId)
			throws CartException, CustomerException, ProductException {
		Optional<Customer> opt = crRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("Customer not found!");

		Optional<Product> itemOpt = pRepo.findById(productId);
		if (itemOpt.isEmpty())
			throw new ProductException("Product not found!");

		Customer customer = opt.get();
		Cart cart = customer.getCart();
		List<Product> itemList = cart.getProducts();
		boolean flag = true;
		if (itemList.size() > 0) {
			for (int i = 0; i < itemList.size(); i++) {
				Product element = itemList.get(i);
				if (element.getProductId() == productId) {
					cart.setProduct_quantity(cart.getProduct_quantity() + 1);
					flag = false;
				}
			}
		}

		if (flag) {
			cart.getProducts().add(itemOpt.get());
		}

		cRepo.save(cart);
		return cart;
	}

	@Override
	public Cart viewCart(Long customerId) throws CartException {
		 Optional<Customer> c = crRepo.findById(customerId);
		 
		 if(c.isPresent()) {
			 Cart cart = c.get().getCart();
			 return cart;
		 }else {
			 throw new CartException("customer id is invalid");
		 }
	
	}

}
