package examples.volatileatomic;

/**
 * @author: deadend
 * @date: 8:22 PM 10/9/16
 * @version: 1.0
 * @description: 证明 volatile 不能保证原子性
 */


public class VolatileAtomicDemo {

    private static volatile long count;

    private static class LoopIncrement implements Runnable {

        @Override
        public void run() {
            for (long i = 0; i < 10000000L; i++) {
                count++;
            }
        }
    }

    public long run() throws InterruptedException {
        count = 0;
        Thread t1 = new Thread(new LoopIncrement());
        Thread t2 = new Thread(new LoopIncrement());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return count;
    }


    public static void main(String[] args) throws InterruptedException {
        VolatileAtomicDemo vad1 = new VolatileAtomicDemo();

        long count1 = vad1.run();
        System.out.println(count1);
        long count2 = vad1.run();
        System.out.println(count2);

        System.out.println("Equality: " + (count1 == count2));
    }
}
