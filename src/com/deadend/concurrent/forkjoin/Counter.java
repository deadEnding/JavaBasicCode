package com.deadend.concurrent.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 
 * @file Counter.java
 * @author concurrent
 * @date Mar 27, 2016
 * @version 1.0
 * @description 计数器 
 *
 */

public class Counter extends RecursiveTask<Integer> {
	
	/** 原子任务阈值 */
	public static final int THRESHOLD = 1000;
	/** 数值序列 */
	private double[] values;
	/** 起始下标 */
	private int from;
	/** 终止下标 */
	private int to;
	/** 过滤器 */
	private Filter filter;
	
	
	/**
	 * 构造函数
	 * @param values: 数值序列
	 * @param from: 起始下标
	 * @param to: 终止下标
	 * @param filter: 过滤器
	 */
	public Counter(double[] values, int from, int to, Filter filter) {
		this.values = values;
		this.from = from;
		this.to= to;
		this.filter = filter;
	}
	
	
	/**
	 * 处理任务
	 */
	public Integer compute() {
		if(to - from < THRESHOLD) {
			int count = 0;
			for(int i = from; i < to; i++) {
				count += (filter.accept(values[i]) ? 1 : 0);
			}
			return count;
		} else {
			int mid = (from + to)/2;
			Counter first = new Counter(values, from, mid, filter);
			Counter second = new Counter(values, mid, to, filter);
			invokeAll(first, second);
			return first.join() + second.join();
		}
	}
}
