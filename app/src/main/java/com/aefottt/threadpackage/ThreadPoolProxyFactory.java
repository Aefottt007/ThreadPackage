package com.aefottt.threadpackage;

public class ThreadPoolProxyFactory {
    static ThreadPoolProxy mNormalThreadPoolProxy;
    static ThreadPoolProxy mDownLoadThreadPoolProxy;

    public static ThreadPoolProxy getNormalThreadPoolProxy(){
        if (mNormalThreadPoolProxy == null){
            synchronized (ThreadPoolProxyFactory.class){
                if (mNormalThreadPoolProxy == null){
                    mNormalThreadPoolProxy = new ThreadPoolProxy(5, 5);
                }
            }
        }
        return mNormalThreadPoolProxy;
    }

    public static ThreadPoolProxy getDownLoadThreadPoolProxy(){
        if (mDownLoadThreadPoolProxy == null){
            synchronized (ThreadPoolProxyFactory.class){
                if (mDownLoadThreadPoolProxy == null){
                    mDownLoadThreadPoolProxy = new ThreadPoolProxy(3, 5);
                }
            }
        }
        return mDownLoadThreadPoolProxy;
    }
}
