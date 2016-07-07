package com.java.configuration.configloader.providers;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.java.configuration.configloader.exceptions.ConfigException;

import java.io.File;

/**
 * @author: java
 * @date: 11:44 PM 6/4/16
 * @version: 1.0
 * @description:
 */


public class DocumentProvider implements IConfigProvider<Document> {

    /* 配置文件路径 */
    String filePath;

    /**
     * 构造函数
     *
     * @param filePath
     */
    public DocumentProvider(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 提供用于加载配置的Document对象
     *
     * @return Document
     * @throws ConfigException
     */
    public Document provide() throws ConfigException {
        Document document = null;
        try {
            File file = new File(filePath);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConfigException(e.toString());
        }

        return document;
    }
}
