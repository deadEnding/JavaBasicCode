package com.deadend.blockingqueue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @file TaskManager.java
 * @author deadend
 * @date Mar 25, 2016
 * @version 1.0
 * @description 管理任务
 *
 */

public class TaskManager {
	
	private String path;
	private String keyword;
	private BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
	private List<Thread> tasks = new ArrayList<>();
	private static final int FILE_QUEUE_SIZE = 10;
	private static final int SEARCH_THREADS = 100;
	
	/**
	 * 构造函数
	 * @param path: 搜索关键词的根路径
	 * @param keyword: 关键词
	 */
	public TaskManager(String path, String keyword) {
		this.path = path;
		this.keyword = keyword;
	}
	
	/**
	 * 创建线程
	 */
	public void build() {
		tasks.add(new Thread(new FileEnumerationTask(queue, new File(path))));
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
			System.out.print("Enter base directory (e.g. /home/deadend/test): ");
			String path = in.nextLine();
			System.out.print("Enter keyword (e.g. equator): ");
			String keyword = in.nextLine();
			TaskManager taskManager = new TaskManager(path, keyword);
			taskManager.build();
			taskManager.run();
			System.out.println("All tasks done.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
