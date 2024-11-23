import java.util.LinkedList;
import java.util.Queue;

class MessageQueue {
    private final int MAX_SIZE = 10;
    private final Queue<String> queue = new LinkedList<>();
    private final Object lock = new Object();

    public void enqueue(String message) {
        synchronized (lock) {
            while (queue.size() == MAX_SIZE) {
                try {
                    System.out.println("Queue is full, producer waiting...");
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            queue.add(message);
            System.out.println("Produced: " + message);
            lock.notifyAll();
        }
    }

    public String dequeue() {
        synchronized (lock) {
            while (queue.isEmpty()) {
                try {
                    System.out.println("Queue is empty, consumer waiting...");
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            String message = queue.poll();
            System.out.println("Consumed: " + message);
            lock.notifyAll();
            return message;
        }
    }
}

class Producer implements Runnable {
    private final MessageQueue queue;
    private final int id;

    public Producer(MessageQueue queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            String message = "Message " + id + "-" + i;
            queue.enqueue(message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private final MessageQueue queue;
    private final int id;

    public Consumer(MessageQueue queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            String message = queue.dequeue();
            System.out.println("Consumer " + id + " processing: " + message);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class MessageQueueSystem {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();
        Thread[] producers = new Thread[3];
        Thread[] consumers = new Thread[2];

        for (int i = 0; i < 3; i++) {
            producers[i] = new Thread(new Producer(queue, i));
            producers[i].start();
        }

        for (int i = 0; i < 2; i++) {
            consumers[i] = new Thread(new Consumer(queue, i));
            consumers[i].start();
        }

        try {
            for (Thread producer : producers) {
                producer.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
