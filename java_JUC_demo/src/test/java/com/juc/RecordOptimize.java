package com.juc;

public class RecordOptimize {
    // 为了避免伪共享，填充缓存行
    public volatile int count;
    public long p1, p2, p3, p4, p5, p6, p7; // 填充
    public volatile int number;
    public long p8, p9, p10, p11, p12, p13, p14;
    public volatile int status;
    public long p15, p16, p17, p18, p19, p20, p21;
    public volatile int size;
    public long p22, p23, p24, p25, p26, p27, p28;
    public volatile int maps;
    public long p29, p30, p31, p32, p33, p34, p35;
}
