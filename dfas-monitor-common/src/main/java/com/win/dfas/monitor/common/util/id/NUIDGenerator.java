package com.win.dfas.monitor.common.util.id;


import java.util.Hashtable;
import java.util.concurrent.CountDownLatch;

/**
 * 生成SOFA规则的数值型ID NUID（可在集群、单机下保障唯一性、顺序性，但不保证连续性），格式为：{yymmddhhmmss}{aa}{bbbbbb},由三段组成:
 * yymmddhhmmss：12位日期，当前应用服务器时间精度到秒。
 * aa：应用服务器编号，用于标示应用服务器，根据应用服务器的ip地址和进程号作为随机数生成因子。
 * bbbbbb：后6位：本应用服务器生产nuid的计数器，当计数到999999后重新开始计数。
 * 如：16051016093057000058标示，2016年5月10号16点09分30秒标示本应用服务器的标号为57本应用服务器生成id的序号为000058
 */
public class NUIDGenerator extends AbsIDGenerator implements IDGenerator {

    public NUIDGenerator() {

        super();

    }

    public NUIDGenerator(int casheSize) {

        super(casheSize);

    }

    public NUIDGenerator(int casheSize, int length) {

        super(casheSize, length);

    }

    @Override
    protected String generate(int order) {
        long seq = globalCount.incrementAndGet();
        if (seq >= seqMaxVal) {
            globalCount.set(APPSERVERID);
            seq = globalCount.incrementAndGet();
        }
        String date = null;

        date = dateTimeFormater.format(System.currentTimeMillis());// 12位日期
        String randStr = "";
        if (randomMaxVal > 0) {
            // Random r = new Random(instanceCount.incrementAndGet() + APPSERVERID + Thread.currentThread().getId());
            // int rInt = r.nextInt(this.randomMaxVal);
            randStr = String.format("%0" + this.randomValLen + "d", APPSERVERID);// 随机数
        }

        String seqStr = String.format("%0" + this.seqValLen + "d", seq);// 顺序号

        return date + randStr + seqStr;
    }

    public static void main(String[] args) {

        final NUIDGenerator nuid = new NUIDGenerator();
        final Hashtable<String, String> ids = new Hashtable<String, String>();

        final int idMax = 50000;
        final CountDownLatch cntdown = new CountDownLatch(4);
        Thread t1 = new Thread(new Runnable() {

            public void run() {

                for (int i = 0; i < idMax; i++) {
                    ids.put(nuid.nextId(), i + "");
                }
                System.out.println("ok1");
                cntdown.countDown();
            }
        });

        Thread t2 = new Thread(new Runnable() {

            public void run() {

                for (int i = 0; i < idMax; i++) {
                    ids.put(nuid.nextId(), i + "");
                }
                System.out.println("ok2");
                cntdown.countDown();
            }
        });

        Thread t3 = new Thread(new Runnable() {

            public void run() {

                for (int i = 0; i < idMax; i++) {
                    ids.put(nuid.nextId(), i + "");
                }
                System.out.println("ok3");
                cntdown.countDown();
            }
        });
        Thread t4 = new Thread(new Runnable() {

            public void run() {

                NUIDGenerator nuid = new NUIDGenerator();
                for (int i = 0; i < idMax; i++) {
                    ids.put(nuid.nextId(), i + "");
                }
                System.out.println("ok4");
                cntdown.countDown();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        int i = 0;

        System.out.println("检查id是否重复....");
        try {
            cntdown.await();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        if (ids.size() < 4 * idMax) {
            System.err.println("id生成重复");
        } else {
            System.out.println("无重复id， size＝" + ids.size());
        }
    }

}
