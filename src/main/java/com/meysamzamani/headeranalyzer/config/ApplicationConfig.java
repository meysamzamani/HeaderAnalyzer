package com.meysamzamani.headeranalyzer.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The ApplicationConfig class represents a singleton configuration class that provides access to application-specific
 * properties stored in a "mail.properties" file. This class follows the Singleton design pattern, ensuring that only
 * one instance of the configuration is available throughout the application.
 */
public class ApplicationConfig {

    /**
     * The single instance of the ApplicationConfig class.
     */
    private static ApplicationConfig instance;

    /**
     * The Properties object that holds the application configuration properties loaded from the "mail.properties" file.
     */
    private Properties properties;

    /**
     * Private constructor for the ApplicationConfig class to prevent external instantiation. The constructor loads the
     * application properties from the "mail.properties" file located in the classpath.
     *
     * @throws IOException if an I/O error occurs while reading the "mail.properties" file.
     */
    private ApplicationConfig() throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "mail.properties";
        properties = new Properties();
        properties.load(new FileInputStream(appConfigPath));
    }

    /**
     * Gets the single instance of the ApplicationConfig class. If the instance does not exist, a new instance is created.
     *
     * @return the single instance of the ApplicationConfig class.
     * @throws IOException if an I/O error occurs while reading the "mail.properties" file.
     */
    public static ApplicationConfig getInstance() throws IOException {
        if (instance == null) {
            return new ApplicationConfig();
        } else {
            return instance;
        }
    }

    /**
     * Retrieves the Properties object containing the application configuration properties.
     *
     * @return the Properties object containing the application configuration properties.
     */
    public Properties getConfigs() {
        return properties;
    }
}
