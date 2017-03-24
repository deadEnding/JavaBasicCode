package basic.datastructures;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: deadend
 * @date: 3:46 PM 12/11/16
 * @version: 1.0
 * @description:
 */


public class MaxQueue {

    private LinkedList<Integer> queue;
    private Deque<Integer> deque;

    public MaxQueue() {
        queue = new LinkedList<Integer>();
        deque = new LinkedList<Integer>();
    }

    public void enqueue(int x) {
        queue.addLast(x);
        while (!deque.isEmpty() && deque.getLast() < x) {
            deque.removeLast();
        }
        deque.addLast(x);
    }

    public int dequeue() {
        if (queue.getFirst() == deque.getFirst()) {
            deque.removeFirst();
        }
        return queue.removeFirst();
    }

    public int peek() {
        return queue.getFirst();
    }

    public int getMax() {
        return deque.getFirst();
    }
}
