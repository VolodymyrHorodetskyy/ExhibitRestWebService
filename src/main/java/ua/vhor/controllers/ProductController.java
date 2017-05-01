package ua.vhor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.vhor.db.entity.Product;
import ua.vhor.entity.Criteria;
import ua.vhor.entity.GoodsPageInfo;
import ua.vhor.services.GoodsPageService;
import ua.vhor.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private GoodsPageService goodsPageService;
	@Autowired
	private ProductService productService;

	@RequestMapping("/getProductsToCriteria")
	public List<Product> getProductAccordingCriteria(
			@RequestBody Criteria criteria) {
		List<Product> products = productService
				.getProductsAccordingToCriteria(criteria);
		return products;
	}

	@RequestMapping("/getGoodsInfo")
	public GoodsPageInfo getGoodsInfo(@RequestBody Criteria criteria) {
		GoodsPageInfo goodsPageInfo = goodsPageService
				.getGoodsPageInfo(criteria);
		return goodsPageInfo;
	}

	@RequestMapping(value = "/getGoodsInfoForFirstRequest")
	public GoodsPageInfo getGoodsInfoForFirstRequest() {
		GoodsPageInfo goodsPageInfo = goodsPageService.getGoodsPageInfo();
		return goodsPageInfo;
	}
}
