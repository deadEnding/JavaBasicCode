package com.deadend.blockingqueue;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @file Task.java
 * @author deadend
 * @date Mar 25, 2016
 * @version 1.0
 * @description 任务抽象类 
 *
 */

public abstract class Task implements Runnable {
	
	protected BlockingQueue<File> queue;
	
	/**
	 * 构造函数
	 * @param queue: 阻塞队列
	 */
	public Task(BlockingQueue<File> queue) {
		this.queue = queue;
	}
}
