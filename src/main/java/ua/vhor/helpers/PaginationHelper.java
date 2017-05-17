package ua.vhor.helpers;

import java.util.Optional;

import ua.vhor.utils.ParametersProvider;

public class PaginationHelper {

	private static final int DEFAULT_SIZE = 9;
	private static final String PROPERTY_NAME = "pagination.amountcardsonpage";

	public static int getAmountOfCardsOnPage() {
		Optional<String> optionalValue = Optional.ofNullable(ParametersProvider
				.getProperty(PROPERTY_NAME));
		if (optionalValue.isPresent()) {
			return Integer.parseInt(optionalValue.get());
		} else {
			return DEFAULT_SIZE;
		}
	}

}
