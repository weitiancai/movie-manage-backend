import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wmg
 * @date 2024/9/1
 */
public class cyclicBarrierTest {
    static CyclicBarrier cyclicBarrier;
    public static void main(String[] args) throws InterruptedException {
        cyclicBarrier = new CyclicBarrier(10, ()-> System.out.println("begin game"));
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 11; i++) {
            new Thread(()->{
                try{
                    Thread.sleep((long) (Math.random() *3000));
                    System.out.println(Thread.currentThread().getName() + " 加载完毕！");
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " into game");
                countDownLatch.countDown();
            },"number"+i+"号").start();
        }
        countDownLatch.await();
        System.out.println("end");



    }



}