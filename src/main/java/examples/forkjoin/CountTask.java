package examples.forkjoin;

import java.util.concurrent.*;

/**
 * @author: deadend
 * @date: 5:14 PM 10/27/16
 * @version: 1.0
 * @description: 计算区间[start, end]中的整数之和
 */


public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;   // 阈值
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int mid = start + (end - start) / 2;
            CountTask leftTask = new CountTask(start, mid);
            CountTask rightTask = new CountTask(mid + 1, end);

            // 执行任务
            leftTask.fork();
            rightTask.fork();

            // 等待子任务执行结束，并得到其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 合并子任务
            sum = leftResult + rightResult;
        }

        return sum;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(1, 100);
        Future<Integer> result = forkJoinPool.submit(countTask);
        System.out.println(result.get());
    }
}
