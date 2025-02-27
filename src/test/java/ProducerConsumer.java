import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wmg
 * @date 2024/9/1
 */
public class ProducerConsumer {
    private static final int MAX = 5;
    private final List<Integer> queue = new ArrayList<>();

    public void produce() throws InterruptedException {
        synchronized (this){
            while (queue.size() == MAX) {
                wait();
            }
            queue.add(1);
            System.out.println("produced " + queue.size());
            notify();
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this){
            while (queue.isEmpty()) {
                wait();
            }
            queue.remove(0);
            System.out.println("consumed " + queue.size());
            notify();
        }
    }

    public void lockTest() throws InterruptedException {
        synchronized (this){
            System.out.println("Thread " + Thread.currentThread().getName() + " is waiting.");
            wait(); // 释放锁并等待
            System.out.println("Thread " + Thread.currentThread().getName() + " is notified and resumes.");
        }
    }
    public void unlockTest() throws InterruptedException {
        synchronized (this){
            System.out.println("Thread " + Thread.currentThread().getName() + " is notifying.");
            notify(); // 唤醒等待的线程
        }
    }

    public static void main(String[] args) {
//        ProducerConsumer pc= new ProducerConsumer();
//
//        new Thread(()->{
//            try {
//                for (int i = 0; i < 10; i++) {
//                    pc.produce();
//                }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }).start();
//
//        new Thread(()->{
//            try {
//                for (int i = 0; i < 10; i++) {
//                    pc.consume();
//                }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }).start();


    }

    @Test
    public void testAny() throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                lockTest();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
//
        Thread t2 = new Thread(()->{
            try {
                unlockTest();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t2.start();
        t1.start();
        t1.join();
        t2.join();

    }
}
