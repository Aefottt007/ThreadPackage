package com.aefottt.threadpackage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolProxy {

    ThreadPoolExecutor mExecutor;
    // 核心线程数，默认一直存活
    private final int mCorePoolSize;
    // 最大线程数，当活动线程超过该值时，后续新的线程将会被阻塞
    private final int mMaximumPoolSize;

    public ThreadPoolProxy(int mCorePoolSize, int mMaximumPoolSize) {
        this.mCorePoolSize = mCorePoolSize;
        this.mMaximumPoolSize = mMaximumPoolSize;
    }

    /**
     * 单例模式初始化ThreadPoolExecutor
     */
    private void initThreadPoolExecutor(){
        if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()){
            synchronized (ThreadPoolProxy.class){
                if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()){
                    long keepAliveTime = 3000; // 允许最大线程最大存活时间
                    TimeUnit unit = TimeUnit.MILLISECONDS; // 时间单位
                    // 线程等待队列，当达到核心线程数量时，只会执行核心线程，等待队列的线程不会被执行
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
                    // 线程工厂，为线程池提供创建新线程的功能
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    // 拒绝执行策略---如果执行的任务数量超过线程池最大容量，后续任务不会被执行
                    // DiscardPolicy会让后续任务不抛出异常，直接忽略
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

                    // 初始化executor
                    mExecutor = new ThreadPoolExecutor(mCorePoolSize, mMaximumPoolSize, keepAliveTime,
                            unit, workQueue, threadFactory, handler);
                }
            }
        }
    }

    /**
     * 执行任务
     * execute无返回值
     * @param task 要执行的任务对象
     */
    public void execute(Runnable task){
        initThreadPoolExecutor();
        mExecutor.execute(task);
    }

    /**
     * 提交任务
     * submit有返回值
     * @param task 提交的任务对象
     * @return 返回Future（Executor中的一个类）
     */
    public Future<?> submit(Runnable task){
        initThreadPoolExecutor();
        return mExecutor.submit(task);
    }

    /**
     * 移除任务
     * @param task 要移除的任务对象
     */
    public void remove(Runnable task){
        initThreadPoolExecutor();
        mExecutor.remove(task);
    }
}
