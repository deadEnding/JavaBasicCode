package com.java.configuration.configloader.loaders;

import com.java.configuration.configloader.providers.IConfigProvider;
import com.java.configuration.configloader.exceptions.ConfigException;

/**
 * @author: java
 * @date: 10:39 PM 6/4/16
 * @version: 1.0
 * @description: 配置加载器抽象类
 */


public abstract class AbstractConfigLoader<T, P> implements IConfigLoader<T> {

    protected IConfigProvider<P> provider;

    protected AbstractConfigLoader(IConfigProvider<P> provider) {
        this.provider = provider;
    }

    /**
     * @see IConfigLoader#load()
     */
    @Override
    public T load() throws ConfigException {
        return load(getProvider().provide());
    }

    public abstract T load(P loaderSource) throws ConfigException;

    protected IConfigProvider<P> getProvider() {
        return this.provider;
    }
}
