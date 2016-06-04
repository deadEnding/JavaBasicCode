package com.deadend.configuration.configloader.providers;

import com.deadend.configuration.configloader.exceptions.ConfigException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author: deadend
 * @date: 11:50 PM 6/4/16
 * @version: 1.0
 * @description:
 */


public class PropertiesProvider implements IConfigProvider<Properties> {

    String filePath;

    public PropertiesProvider(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 提供用于加载配置的Properties对象
     *
     * @return Properties
     * @throws ConfigException
     */
    public Properties provide() throws ConfigException {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(filePath);
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConfigException(e.toString());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return prop;
    }
}
