package ua.vhor.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.vhor.entity.Criteria;
import ua.vhor.entity.Product;
import ua.vhor.repository.ProductRepository;

@RestController
public class ProductController {

	private final int amountOfCardOnPage = 10;

	@Autowired
	ProductRepository productRepository;

	@RequestMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		Pageable pageable = new PageRequest(0, amountOfCardOnPage);
		List<Product> products = productRepository.findAll(pageable)
				.getContent();
		return products;
	}

	@RequestMapping("/getProductsToCriteria")
	public List<Product> getProductAccordingCriteria(
			@RequestBody Criteria criteria) {
		Pageable pageable = new PageRequest(criteria.getPage(),
				amountOfCardOnPage);
		List<Product> products = productRepository.findByPriceBetween(
				criteria.getMinPrice(), criteria.getMaxPrice(), pageable)
				.getContent();

		return products;
	}

	@RequestMapping("/getMinMaxPrice")
	public Criteria getMinMaxPrice() {
		Double biggestPrice = productRepository.getGreatestPrice();
		Double leastPrice = productRepository.getLeastPrice();
		Criteria criteria = new Criteria(leastPrice, biggestPrice);
		return criteria;
	}

}
