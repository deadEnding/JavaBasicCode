package com.deadend.threadpool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 
 * @file MatchCounter.java
 * @author deadend
 * @date Mar 27, 2016
 * @version 1.0
 * @description 对关键词搜索并计数 
 *
 */

public class MatchCounter implements Callable<Integer> {
	
	/** 搜索路径 */
	private File directory;
	/** 关键字 */
	private String keyword;
	/** 线程池 */
	private ExecutorService pool;
	/** 计数 */
	private int count;
	
	
	/**
	 * 构造函数
	 * @param directory: 搜索路径
	 * @param keyword: 关键字
	 * @param pool: 线程池
	 */
	public MatchCounter(File directory, String keyword, ExecutorService pool) {
		this.directory = directory;
		this.keyword = keyword;
		this.pool = pool;
	}


	@Override
	public Integer call() {
		count = 0;
		try {
			File[] files = directory.listFiles();
			List<Future<Integer>> results = new ArrayList<>();
			
			for(File file: files) {
				if(file.isDirectory()) {
					MatchCounter counter = new MatchCounter(file, keyword, pool);
					Future<Integer> result = pool.submit(counter);
					results.add(result);
				} else {
					if(search(file)) {
						count++;
					}
				}
			}
		
			for(Future<Integer> result: results) {
				try {
					count += result.get();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {}
		return count;
	}
	
	
	/**
	 * 搜索关键字
	 * @param file: 文件
	 */
	public boolean search(File file) {
		try {
			try(Scanner in = new Scanner(file)) {
				boolean found = false;
				while(!found && in.hasNextLine()) {
					String line = in.nextLine();
					if(line.contains(keyword)) {
						found = true;
					}
				}
				return found;
			}
		} catch(IOException e) {
			return false;
		}
	}
}














