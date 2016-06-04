package com.deadend.concurrent.future;


/**
 * 
 * @file Statistic.java
 * @author concurrent
 * @date Mar 25, 2016
 * @version 1.0
 * @description 搜索结果的统计信息 
 *
 */

public class Statistic {
	
	/** 包含关键字的行 */
	private String line;
	/**	包含关键字的文件路径 */
	private String path;
	/** 包含关键字的行号 */
	private long lineNum;
	
	
	/**
	 * 构造函数
	 * @param line: 包含关键字的行
	 * @param path: 包含关键字的文件路径
	 * @param lineNum: 包含关键字的行号
	 */
	public Statistic(String line, String path, long lineNum) {
		this.line = line;
		this.path = path;
		this.lineNum = lineNum;
	}
	
	
	public String toString() {
		return String.format("[%s:%d] %s", path, lineNum, line);
	}
	
	
	public String getLine() {
		return line;
	}
	
	
	public void setLine(String line) {
		this.line = line;
	}
	
	
	public String getPath() {
		return path;
	}
	
	
	public void setPath(String path) {
		this.path = path;
	}
	
	
	public long getLineNum() {
		return lineNum;
	}
	
	
	public void setLineNum(long lineNum) {
		this.lineNum = lineNum;
	}
}
