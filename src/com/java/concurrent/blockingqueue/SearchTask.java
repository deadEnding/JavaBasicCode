package com.java.concurrent.blockingqueue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @file SearchTask.java
 * @author concurrent
 * @date Mar 25, 2016
 * @version 1.0
 * @description 搜索关键词
 *
 */

public class SearchTask extends Task {
	
	/** 关键字 */
	private String keyword;
	
	
	/**
	 * 构造函数
	 * @param quque: 获取文件的队列
	 * @param keyword: 关键词
	 */
	public SearchTask(BlockingQueue<File> queue, String keyword) {
		super(queue);
		this.keyword = keyword;
	}

	
	@Override
	public void run() {
		try {
			boolean done = false;
			while(!done) {
				File file = queue.take();
				if (file == FileEnumerationTask.DUMMY) {
					queue.put(FileEnumerationTask.DUMMY);
					done = true;
				} else {
					search(file);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {}
	}
	
	
	/**
	 * 搜索关键字
	 * @param file: 文件
	 */
	public void search(File file) throws IOException {
		try (Scanner in = new Scanner(file)) {
			long lineNumber = 0L;
			while(in.hasNextLine()) {
				lineNumber++;
				String line = in.nextLine();
				if (line.contains(keyword)) {
					System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
				}
			}
		}
	}
	
}














