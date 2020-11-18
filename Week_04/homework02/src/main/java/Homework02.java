package homework02.src.main.java;

import com.sun.source.tree.WhileLoopTree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework02 {
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1,1,1000L, TimeUnit.SECONDS
            ,new LinkedBlockingDeque<>(),new ThreadPoolExecutor.CallerRunsPolicy());

    private static volatile int volatileInt = 0;

    public static void main(String[] args) {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        //这是得到的返回值
//        int result = method1();
//        int result = method2();
//        int result = method3();
//        int result = method4();
//        int result = method5();
//        int result = method6();
//        int result = method7();
//        int result = method8();
//        int result = method9();
//        int result = method10();
//        int result = method11();
//        int result = method12();
        int result = method13();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    /**
     * 使用volatile+CAS
     * * @return
     */
    private static int method1() {
        new Thread(()-> {
            volatileInt = sum();
        }).start();
        while(volatileInt == 0){
            System.out.println("not done");
        }
        return volatileInt;
    }

    /**
     * 使用autic+CAS
     * * @return
     */
    private static int method2() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        new Thread(()-> {
            atomicInteger.set(sum());
        }).start();
        while(atomicInteger.intValue() == 0){
            System.out.println("not done");
        }
        return atomicInteger.intValue();
    }

    /**
     * 使用thread.join
     */
    private static int method3() {
        Result result = new Result();
        Thread task = new Thread(()-> {
            result.setSum(sum());
        });
        task.start();
        try {
            task.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result.getSum();
    }

    /**
     * 使用wait+notify
     */
    private static int method4() {
        Result result = new Result();
        Thread task = new Thread(()-> {
            synchronized (result) {
                result.setSum(sum());
                result.notifyAll();
            }
        });
        task.start();
        try {
            while (result.getSum() == 0) {
                synchronized (result) {
                    result.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result.getSum();
    }

    /**
     * 使用while
     */
    private static int method5() {
        Result result = new Result();
        Thread task = new Thread(()-> {
            result.setSum(sum());
        });
        task.start();
        while (result.getSum() == 0) {
            System.out.println("not done");
        }
        return result.getSum();
    }

    /**
     * 使用lock+thread sleep,thread sleep 保证子线程能拿到锁
     * @return
     */
    private static int method6() {
        Lock lock = new ReentrantLock(true);
        Result result = new Homework02.Result();
        new Thread(()-> {
            lock.lock();
            result.setSum(sum());
            lock.unlock();
        }).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(!lock.tryLock()){
            System.out.println("locked");
        }
        return result.getSum();
    }

    /**
     * 使用Thread.activeCount
     * @return
     */
    private static int method7() {
        Result result = new Result();
        Thread task = new Thread(()-> {
            result.setSum(sum());
        });
        task.start();
//        当前线程有3个，main、monitor、task
        while (Thread.activeCount() > 2) {
            System.out.println("not done");
        }

        return result.getSum();
    }

    /**
     * 使用thread.isAlive
     * @return
     */
    private static int method8() {
        Result result = new Result();
        Thread task = new Thread(()-> {
            result.setSum(sum());
        });
        task.start();
//        子线程是否还在执行
        while (task.isAlive()) {
            System.out.println("not done");
        }

        return result.getSum();
    }

    /**
     * 使用FutureTask
     * @return
     */
    private static int method9() {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        futureTask.run();
        int sum = 0;
        try {
            sum = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return sum;
    }

    /**
     * 使用CompletableFuture
     * @return
     */
    private static int method10() {
        Result result = new Result();

        result.setSum(CompletableFuture.supplyAsync(()->{
            return sum();
        }).join());

//        第二种写法
//        CompletableFuture future = CompletableFuture.runAsync(new Runnable() {
//            @Override
//            public void run() {
//                result.setSum(sum());
//            }
//        });
//
//        try {
//            future.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


        return result.getSum();
    }

    /**
     * 使用StreamParallel
     * @return
     */
    private static int method11() {
        Result result = new Result();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.stream().parallel().forEach(i->{
            result.setSum(sum());
        });

        return result.getSum();
    }

    /**
     * 使用CountDownLatch
     * @return
     */
    private static int method12() {
        Result result = new Result();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                result.setSum(sum());
                countDownLatch.countDown();
            }
        }).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result.getSum();
    }

    /**
     * 使用Semaphore
     * @return
     */
    private static int method13() {
        Result result = new Result();
        //机器数目
        Semaphore semaphore = new Semaphore(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    result.setSum(sum());
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(10);
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result.getSum();
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }


    static class Result{
        private int sum;

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }
    }
}
