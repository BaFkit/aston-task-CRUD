package ru.aston.utils;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Log4j2
public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    private PropertiesUtil() {
    }

}
