package ua.vhor.helpers;

import java.util.ArrayList;
import java.util.List;

public class OrderByHelper {

	public final static List<String> LIST_ORDER_BY = new ArrayList<String>() {
		{
			add("Newest");
			add("Most Popular");
			add("Highest Price");
			add("Lowest Price");
			add("Name");
		}
	};

}
