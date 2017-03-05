package ua.vhor.helpers;

import ua.vhor.entity.Criteria;
import ua.vhor.entity.GoodsPageInfo;

public class GoodsPageHelper {

	private Criteria criteria;

	public GoodsPageHelper(Criteria criteria) {
		super();
		this.criteria = criteria;
	}

	public GoodsPageInfo getGoodPageInfo() {
		return new GoodsPageInfo(criteria.getMinPrice(),
				criteria.getMaxPrice(), 0, 0);
	}

}
