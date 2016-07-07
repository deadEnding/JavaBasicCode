package com.java.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Manager {
	
	/** 线程池 */
	private ForkJoinPool pool;
	
	
	/**
	 * 执行任务
	 */
	public Integer run(RecursiveTask<Integer> task) {
		pool = new ForkJoinPool();
		pool.invoke(task);
		return task.join();
	}

	
	public static void main(String[] args) {
		final int SIZE = 10000000;
		double[] numbers = new double[SIZE];
		for(int i = 0; i < SIZE; i++) {
			numbers[i] = Math.random();
		}
		Counter counter = new Counter(numbers, 0, numbers.length,
				new Filter(){
					public boolean accept(double v) {
						return v <= 0.3;
					}
				});
		Manager manager = new Manager();
		int count = manager.run(counter);
		System.out.printf("Totol: %d, Count: %d, Percent: %.3f%%", SIZE, count, ((double)count) / SIZE * 100);
	}
}
