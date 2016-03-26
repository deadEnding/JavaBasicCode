package com.deadend.future;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @file FileEnumerationTask.java
 * @author deadend
 * @date Mar 25, 2016
 * @version 1.0
 * @description 枚举目录下的文件
 *
 */

public class FileEnumerationTask extends Task implements Runnable {
	
	/** 虚拟对象，用于标识搜索完毕，使搜索线程退出 */
	public static final File DUMMY = new File("");
	/** 目录 */
	private File startDirectory;
	
	
	/**
	 * 构造函数
	 * @param queue: 存放文件的队列
	 * @param keyword: 关键字
	 */
	public FileEnumerationTask(BlockingQueue<File> queue, File startDirectory) {
		super(queue);
		this.startDirectory = startDirectory;
	}
	
	
	/**
	 * 递归枚举目录下的文件
	 * @param directory: 目录
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
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			enumerate(startDirectory);
			queue.put(DUMMY);
		} catch (InterruptedException e) {}
	}
}
