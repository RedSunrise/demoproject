package apitests.apiinfo;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static int getAndAdd(int value){
        return atomicInteger.getAndAdd(value);
    }
}
