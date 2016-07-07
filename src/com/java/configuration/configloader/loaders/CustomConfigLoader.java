package com.java.configuration.configloader.loaders;

import com.java.configuration.configloader.configs.CustomConfig;
import com.java.configuration.configloader.exceptions.ConfigException;
import com.java.configuration.configloader.providers.ConfigProviderFactory;
import com.java.configuration.configloader.providers.IConfigProvider;

import java.util.Properties;

/**
 * @author: java
 * @date: 12:49 AM 6/5/16
 * @version: 1.0
 * @description: 自定义配置加载器
 *
 * @ps 在使用时，需要定义CustomConfig类和CustomConfigLoader类，其中CustomConfigLoader类需要定义构造函数并重写load(P)方法
 */


public class CustomConfigLoader extends AbstractConfigLoader<CustomConfig, Properties> {

    protected CustomConfigLoader(IConfigProvider<Properties> provider) {
        super(provider);
    }

    /**
     * 从Provider提供的Properties对象加载配置的具体实现
     *
     * @see AbstractConfigLoader#load()
     *
     * @param prop
     * @return CustomConfig
     * @throws ConfigException
     */
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
