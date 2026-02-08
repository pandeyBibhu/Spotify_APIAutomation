package com.ApiAutomation.Spotify.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

import org.apache.http.entity.mime.content.InputStreamBody;

public class FileUtils {
	/**
	 * load properties file from hardcoded path it is not recommended always but
	 * could be used
	 */
	public static Properties loadProperties() {
		Properties prop = new Properties();

		try {
			File file = new File("src/test/resources/config/config.properties");
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);

		} catch (Exception e) {
			throw new RuntimeException("Config.properties not found at provided location" + e);
		}

		return prop;
	}

	/**
	 * Load properties file from classpath using InputStream It can used to load
	 * configuration files
	 */

	public static Properties loadPropertiesFromClasspath() {
		Properties prop;
		try {
			prop = new Properties();
			InputStream is = FileUtils.class.getClassLoader().getResourceAsStream("config.properties");
			if (is == null) {
				throw new RuntimeException("Config.properties file not available at resource folder");
			}
			prop.load(is);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load config.properties");
		}
		return prop;
	}
	/**Load JsonSchema Files
	 * */
//	public static 

	public static void main(String[] args) {
		System.out.println(loadProperties().get("name"));
	}

}
