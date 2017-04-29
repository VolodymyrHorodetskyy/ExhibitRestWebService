package ua.vhor.utils;

import java.util.Optional;

public class PaginationHelper {

	private static final int DEFAULT_SIZE = 9;
	private static final String PROPERTY_NAME = "pagination.amountcardsonpage";

	public static int getAmountOfPages() {
		Optional<String> optionalValue = Optional.ofNullable(ParametersProvider
				.getProperty(PROPERTY_NAME));
		if (optionalValue.isPresent()) {
			return Integer.parseInt(optionalValue.get());
		} else {
			return DEFAULT_SIZE;
		}
	}

}
