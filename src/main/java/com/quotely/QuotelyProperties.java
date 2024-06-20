package com.quotely;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class QuotelyProperties extends Properties {
    private static final String CONFIG_PATH = "/config//";
    private static final String PROPS_FILE_NAME = "quotely.properties";
    private Properties appProps;

    public QuotelyProperties() {
        String rootPath = Paths.get("").toAbsolutePath().toString();
        appProps = new Properties();
        try {
            appProps.load(getFileFromResourceAsStream(PROPS_FILE_NAME));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Properties getProps() {
        return appProps;
    }

    private InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}
