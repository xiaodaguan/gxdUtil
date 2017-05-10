package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by guanxiaoda on 17/5/10.
 */
public class ConcurrentTest {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        for(int i=0;i<100;i++){
            es.submit(new LasyThread(i));
        }
    }
}

class LasyThread implements Runnable{

    int id;

    public LasyThread(int i){
        this.id = i;
    }

    public void run() {
        try {
            System.out.println("i am thread: " + id);
            Thread.sleep(5*1000);
            System.out.println(id+ " ok.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}