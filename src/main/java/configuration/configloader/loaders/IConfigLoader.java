package configuration.configloader.loaders;

import configuration.configloader.exceptions.ConfigException;

/**
 * @author: java
 * @date: 5:01 PM 6/4/16
 * @version: 1.0
 * @description: 配置加载器接口类
 * @refer: http://www.codeceo.com/article/java-config-load.html#0-tsina-1-50559-397232819ff9a47a7b7e80a40613cfe1
 */
public interface IConfigLoader<T> {

    /**
     * Load the config
     *
     * @return
     * @throws ConfigException
     */
    public T load() throws ConfigException;
}
