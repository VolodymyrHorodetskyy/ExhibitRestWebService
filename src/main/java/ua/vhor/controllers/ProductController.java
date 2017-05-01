package ua.vhor.controllers;

import java.util.List;

import org.apache.log4j.Logger;
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

	private final static Logger logger = Logger
			.getLogger(ProductController.class);

	@Autowired
	private GoodsPageService goodsPageService;
	@Autowired
	private ProductService productService;

	@RequestMapping("/getProductsToCriteria")
	public List<Product> getProductAccordingCriteria(
			@RequestBody Criteria criteria) {
		logger.info("Executing method getProductAccordingCriteria()");

		List<Product> products = null;
		try {
			products = productService.getProductsAccordingToCriteria(criteria);
		} catch (Exception e) {
			logger.error("Exception in method getProductAccordingCriteria() \n"
					+ e.getMessage());
		}
		return products;
	}

	@RequestMapping("/getGoodsInfo")
	public GoodsPageInfo getGoodsInfo(@RequestBody Criteria criteria) {
		logger.info("Executing method getGoodsInfo()");

		GoodsPageInfo goodsPageInfo = null;
		try {
			goodsPageInfo = goodsPageService.getGoodsPageInfo(criteria);
		} catch (Exception e) {
			logger.error("Exception in method getGoodsInfo() \n"
					+ e.getMessage());
		}
		return goodsPageInfo;
	}

	@RequestMapping(value = "/getGoodsInfoForFirstRequest")
	public GoodsPageInfo getGoodsInfoForFirstRequest() {
		logger.info("Executing method getGoodsInfoForFirstRequest()");

		GoodsPageInfo goodsPageInfo = null;
		try {
			goodsPageInfo = goodsPageService.getGoodsPageInfo();
		} catch (Exception e) {
			logger.error("Exception in method getGoodsInfoForFirstRequest() \n"
					+ e.getMessage());
		}
		return goodsPageInfo;
	}
}
