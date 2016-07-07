package com.java.concurrent.blockingqueue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @file TaskManager.java
 * @author concurrent
 * @date Mar 25, 2016
 * @version 1.0
 * @description 管理任务
 *
 */

public class TaskManager {
	
	/** 阻塞队列容量 */
	private static final int FILE_QUEUE_SIZE = 10;
	/** 启动的搜索线程数 */
	private static final int SEARCH_THREADS = 100;
	/** 搜索根路径 */
	private String directory;
	/** 关键字 */
	private String keyword;
	/** 阻塞队列，用于存储文件名 */
	private BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
	/** 存储线程 */
	private List<Thread> tasks = new ArrayList<>();
	
	
	/**
	 * 构造函数
	 * @param directory: 搜索关键词的根路径
	 * @param keyword: 关键词
	 */
	public TaskManager(String directory, String keyword) {
		this.directory = directory;
		this.keyword = keyword;
	}
	
	
	/**
	 * 创建线程
	 */
	public void build() {
		File file = new File(directory);
		if(!file.exists()) {
			System.out.printf("Directory \"%s\" doesn't exist.", directory);
			System.exit(0);
		}
		tasks.add(new Thread(new FileEnumerationTask(queue, file)));
		for (int i = 1; i<= SEARCH_THREADS; i++) {
			tasks.add(new Thread(new SearchTask(queue, keyword)));
		}
	}
	
	
	/**
	 * 启动线程
	 */
	public void run() {
		for(Thread t: tasks) {
			t.start();
		}
		
		for(Thread t: tasks) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	public static void main(String[] args) {
		try(Scanner in = new Scanner(System.in)) {
			System.out.print("Enter base directory (e.g. /home/concurrent/test): ");
			String directory = in.nextLine();
			System.out.print("Enter keyword (e.g. equator): ");
			String keyword = in.nextLine();
			TaskManager taskManager = new TaskManager(directory, keyword);
			taskManager.build();
			taskManager.run();
			System.out.println("Tasks done.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
