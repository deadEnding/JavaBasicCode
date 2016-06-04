package com.deadend.configuration.configloader.loaders;

import com.deadend.configuration.configloader.configs.CustomConfig;
import com.deadend.configuration.configloader.exceptions.ConfigException;
import com.deadend.configuration.configloader.providers.ConfigProviderFactory;
import com.deadend.configuration.configloader.providers.IConfigProvider;

import java.util.Properties;

/**
 * @author: deadend
 * @date: 12:49 AM 6/5/16
 * @version: 1.0
 * @description:
 */


public class CustomConfigLoader extends AbstractConfigLoader<CustomConfig, Properties> {

    protected CustomConfigLoader(IConfigProvider<Properties> provider) {
        super(provider);
    }

    public CustomConfig load(Properties prop) throws ConfigException {
        CustomConfig customConfig = new CustomConfig();
        customConfig.setName(prop.getProperty("name"));
        customConfig.setAge(Integer.parseInt(prop.getProperty("age")));
        return customConfig;
    }

    public static void main(String[] args) {
        String filePath = "/tmp/test.properties";
        CustomConfigLoader customConfigLoader = new CustomConfigLoader(ConfigProviderFactory.createPropertiesProvider(filePath));
        try {
            CustomConfig customConfig = customConfigLoader.load();
            System.out.println(customConfig.getName() + ": " + customConfig.getAge());
        } catch (ConfigException e) {
            e.printStackTrace();
        }
    }
}
