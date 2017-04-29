package ua.vhor.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class ParametersProvider {

	private static final String PATH_PROPERTIES_WEB_SERVICES = "parameters/pageparameters.properties";

	public static String getProperty(String name) {
		String property = null;
		try {
			Properties properties = new Properties();
			File file = new ClassPathResource(PATH_PROPERTIES_WEB_SERVICES)
					.getFile();
			InputStream stream = new FileInputStream(file);
			properties.load(stream);
			property = properties.getProperty(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return property;
	};
}
