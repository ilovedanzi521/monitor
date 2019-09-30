package com.win.dfas.monitor.engine.pool;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 线程工厂类。封装线程的创建，包括TerminatableThread线程的创建
 */
public class MonitorThreadFactory implements ThreadFactory {
    //static final AtomicInteger poolNumber = new AtomicInteger(1);
    final ThreadGroup group;
    final AtomicInteger threadNumber = new AtomicInteger(0);
    private String namePrefix;

    public MonitorThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        //namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
        namePrefix = "IBT-ThreadPool-thread-";
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    /**
     * 实现jdk线程池工厂，扩展线程创建过程
     * 主要包括TerminatableThread线程创建及池中线程的命名
     * @param r  线程接口
     * @return 真正的执行线程
     */
    @Override
    public Thread newThread(Runnable r) {
        Thread t = null;
        boolean isdefault = false;
        try {
            Field firstTask = r.getClass().getDeclaredField("firstTask");
            firstTask.setAccessible(true);
            Object o = firstTask.get(r);
            Field sync = o.getClass().getDeclaredField("sync");
            sync.setAccessible(true);
            o = sync.get(o);
            Field callable = o.getClass().getDeclaredField("callable");
            callable.setAccessible(true);
            o = callable.get(o);
            if (o instanceof MonitorTerminatableThread) {
                TerminatableThread temp = new TerminatableThread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
                temp.setSofaTerminatableThread((MonitorTerminatableThread<?>) o);
                t = temp;
                //System.out.println("%%%"+namePrefix + threadNumber.getAndIncrement());
            } else {
                isdefault = true;
            }
        } catch (Exception e) {
            isdefault = true;
        }
        if (isdefault) {
            t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            //System.out.println("###"+namePrefix + threadNumber.getAndIncrement());
        }
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

    /**
     * 线程类，实现可进行中止的线程。
     *
     */
    class TerminatableThread extends Thread {

        private MonitorTerminatableThread<?> terminatableThread;

        public TerminatableThread(ThreadGroup group,
                                  Runnable target, String name, long stackSize) {
            super(group, target, name, stackSize);
        }

        public void setSofaTerminatableThread(MonitorTerminatableThread<?> terminatableThread) {
            this.terminatableThread = terminatableThread;
        }

        @Override
        public void interrupt() {
            terminatableThread.interrupt();
            super.interrupt();
        }

    }
}