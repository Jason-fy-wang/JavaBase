package com.juc;

import org.junit.Test;

import java.util.Arrays;

public class MultipleUpdates {

    private long loop = 10_0000_0000;

    @Test
    public void testUpdate() throws InterruptedException {

        Record record = new Record();
        record.count = 0;
        record.number = 0;
        record.status = 0;
        record.size = 0;
        record.maps = 0;

        long start = System.currentTimeMillis();
        Thread count = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                record.count++;
            }
        });
        Thread number = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                record.number++;
            }
        });
        Thread status = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                record.status++;
            }
        });
        Thread size = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                record.size++;
            }

        });
        Thread maps = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                record.maps++;
            }
        });
        Arrays.asList(count, number, status, size, maps).forEach(Thread::start);
        maps.join();
        count.join();
        number.join();
        status.join();
        size.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("on optimize time = "  + time + " ms");
        System.out.println("no optimize Final Record: " + record.count + ", " + record.number + ", " + record.status + ", " + record.size + ", " + record.maps);
    }

    @Test
    public void testUpdateOptimize() throws InterruptedException {
        RecordOptimize record = new RecordOptimize();
        record.count = 0;
        record.number = 0;
        record.status = 0;
        record.size = 0;
        record.maps = 0;
        long start = System.currentTimeMillis();
        Thread count = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                record.count++;
            }
        });
        Thread number = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                record.number++;
            }
        });
        Thread status = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                record.status++;
            }
        });
        Thread size = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                record.size++;
            }

        });
        Thread maps = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                record.maps++;
            }
        });
        Arrays.asList(count, number, status, size, maps).forEach(Thread::start);
        maps.join();
        count.join();
        number.join();
        status.join();
        size.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("optimize time = "  + time + " ms");
        System.out.println("optimize Final Record: " + record.count + ", " + record.number + ", " + record.status + ", " + record.size + ", " + record.maps);
    }

}
