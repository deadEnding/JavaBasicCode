package com.deadend.blockingqueue;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @file FileEnumeartionTask.java
 * @author deadend
 * @date Mar 25, 2016
 * @version 1.0
 * @description 枚举目录下的文件
 *
 */

public class FileEnumeartionTask implements Runnable {
	
	public static final File DUMMY = new File("");
	private BlockingQueue<File> queue;
	private File startDirectory;
	
	/**
	 * 构造函数
	 * @param queue: 存放文件的队列
	 * @param keyword: 关键字
	 */
	public FileEnumeartionTask(BlockingQueue<File> queue, File startDirectory) {
		this.queue = queue;
		this.startDirectory = startDirectory;
	}

	@Override
	public void run() {
		try {
			enumerate(startDirectory);
			queue.put(DUMMY);
		} catch (InterruptedException e) {}
	}
	
	/**
	 * 递归枚举目录下的文件
	 * @throws InterruptedException 
	 */
	public void enumerate(File diretory) throws InterruptedException {
		File[] files = diretory.listFiles();
		for(File file: files) {
			if (file.isDirectory()) {
				enumerate(file);
			} else {
				queue.put(file);
			}
		}
	}
}
