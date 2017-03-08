package configuration.configloader.providers;

import org.w3c.dom.Document;
import java.util.Properties;

/**
 * @author: java
 * @date: 10:26 PM 6/4/16
 * @version: 1.0
 * @description: 配置加载方式提供者的工厂类
 */


public class ConfigProviderFactory {

    private ConfigProviderFactory() {
        throw new UnsupportedOperationException("Unable to initialize a factory class: "
                + getClass().getSimpleName());
    }

    public static IConfigProvider<Document> createDocumentProvider(String filePath) {
        return new DocumentProvider(filePath);
    }

    public static IConfigProvider<Properties> createPropertiesProvider(String filePath) {
        return new PropertiesProvider(filePath);
    }

//    public static IConfigLoader<Digester> createDigesterProvider(String filePath) {
//        return new DigesterProvider(filePath);
//    }
}
