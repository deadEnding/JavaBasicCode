package com.deadend.future;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

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
	/** 线程运行结果 */
	private List<Future<List<Statistic>>> results = new ArrayList<>();

	
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
			FutureTask<List<Statistic>> task = new FutureTask<>(new SearchTask(queue, keyword));
			results.add(task);
			tasks.add(new Thread(task));
		}
	}
	
	
	/**
	 * 启动线程，获取结果
	 */
	public List<Statistic> run() {
		for(Thread t: tasks) {
			t.start();
		}
		
		List<Statistic> statList = new ArrayList<>();
		for(Future<List<Statistic>> result: results) {
			try {
				statList.addAll(result.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return statList;
	}
	

	public static void main(String[] args) {
		try(Scanner in = new Scanner(System.in)) {
			System.out.print("Enter base directory (e.g. /home/deadend/test): ");
			String directory = in.nextLine();
			System.out.print("Enter keyword (e.g. equator): ");
			String keyword = in.nextLine();
			TaskManager taskManager = new TaskManager(directory, keyword);
			taskManager.build();
			List<Statistic> statList = taskManager.run();
			
			for(Statistic stat: statList) {
				System.out.println(stat.toString());
			}
			System.out.printf("Tasks done. Total: %d%n", statList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
