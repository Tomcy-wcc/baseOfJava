package thread;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
        priorityBlockingQueue.add(3);
        priorityBlockingQueue.add(5);
        priorityBlockingQueue.add(8);
        priorityBlockingQueue.add(7);
        priorityBlockingQueue.add(4);
        System.out.println(priorityBlockingQueue);
    }
}
