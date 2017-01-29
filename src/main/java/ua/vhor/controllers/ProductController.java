package ua.vhor.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.vhor.entity.Product;
import ua.vhor.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@RequestMapping("/getAllProducts")
	public List<Product> getAllProducts(HttpServletResponse response) {
		
		List<Product> products = productRepository.findAll();
	    return products;

	}

}
