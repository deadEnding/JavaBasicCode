package basic.datastructures.minstack;

import java.util.LinkedList;

/**
 * @author: deadend
 * @date: 3:22 PM 12/11/16
 * @version: 1.0
 * @description:
 */


public class MinStack {

    private LinkedList<Integer> stack;
    private LinkedList<Integer> mins;

    public MinStack() {
        stack = new LinkedList<Integer>();
        mins  = new LinkedList<Integer>();
    }

    public void push(int x) {
        stack.push(x);
        if (mins.isEmpty() || mins.peek() >= x) {
            mins.push(x);
        }
    }

    public int pop() {
        if (stack.peek() == mins.peek()) {
            mins.pop();
        }
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return mins.peek();
    }
}
