package com.quotely;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class QuotelyProperties extends Properties {
    private static final String CONFIG_PATH = "/config//";
    private static final String PROPS_FILE_NAME = "quotely.properties";
    private static final Properties appProps;

    static {
        String rootPath = Paths.get("").toAbsolutePath().toString();
        appProps = new Properties();
        try {
            appProps.load(new FileInputStream(rootPath + CONFIG_PATH + PROPS_FILE_NAME));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Properties getProps() {
        return appProps;
    }
}
