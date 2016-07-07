package com.java.configuration.configloader.providers;

import com.java.configuration.configloader.exceptions.ConfigException;

/**
 * @author: java
 * @date: 5:15 PM 6/4/16
 * @version: 1.0
 * @description: 配置加载方式的提供者
 */


public interface IConfigProvider<P> {

    /**
     * 根据路径提供用于加载配置的配置对象，如Document，Properties等
     *
     * @return
     * @throws ConfigException
     */
    public P provide() throws ConfigException;
}
