package concurrent.threadpool;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 
 * @file ThreadPoolManager.java
 * @author concurrent
 * @date Mar 27, 2016
 * @version 1.0
 * @description 管理线程池
 *
 */

public class ThreadPoolManager {
	
	/** 搜索根目录 */
	private String directory;
	/** 关键字 */
	private String keyword;
	/** 线程池 */
	private ExecutorService pool;
	/** 线程结果 */
	private Future<Integer> result;
	
	
	/**
	 * 构造函数
	 * @param directory: 搜索根目录
	 * @param keyword: 关键字
	 */
	public ThreadPoolManager(String directory, String keyword) {
		this.directory = directory;
		this.keyword = keyword;
	}
	
	
	/**
	 * 启动任务
	 */
	public void start() {
		File file = new File(directory);
		if(!file.exists()) {
			System.out.printf("[ERROR] Directory \"%s\" doesn't exist.", directory);
			System.exit(0);
		}
		
		pool = Executors.newCachedThreadPool();
		MatchCounter counter = new MatchCounter(file, keyword, pool);
		result = pool.submit(counter);
	}
	
	
	/**
	 * 关闭线程池
	 */
	public void shutdown() {
		pool.shutdown();
	}
	
	
	/**
	 * 获取结果
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public int getResult() throws InterruptedException, ExecutionException {
		return result.get();
	}
	
	
	/**
	 * 获取线程池中线程最大值
	 */
	public int getLargestPoolSize() {
		return ((ThreadPoolExecutor)pool).getLargestPoolSize();
	}
	

	public static void main(String[] args) {
		try(Scanner in = new Scanner(System.in)) {
			System.out.print("Enter base directory (e.g. /home/concurrent/test): ");
			String directory = in.nextLine();
			System.out.print("Enter keyword (e.g. equator): ");
			String keyword = in.nextLine();
			ThreadPoolManager poolManager = new ThreadPoolManager(directory, keyword);
			poolManager.start();
			try {
				System.out.println("Matching files: " + poolManager.getResult());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {}
			poolManager.shutdown();
			System.out.println("Lagest pool size: " + poolManager.getLargestPoolSize());
		}
	}

}
