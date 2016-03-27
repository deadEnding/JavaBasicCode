package com.deadend.forkjoin;

/**
 * 
 * @file Filter.java
 * @author deadend
 * @date Mar 27, 2016
 * @version 1.0
 * @description 过滤器接口 
 *
 */
public interface Filter {
	
	/**
	 * 判断参数值是否可接受
	 */
	boolean accept(double value);
}
