package com.algo.cpu;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.algo.cpu.jna.affinitywraper.Affinity;
import com.algo.cpu.jna.affinitywraper.AffinityLock;

/**
 * 
 * https://www.jb51.net/article/247735.htm
 * 
 * 但是如果是不同CPU核对同一线程进行调度，则可能会出现CPU切换造成的性能损失
 *
 */
public class Test {

	  public static void main(String[] args) {

	        int cpus = 1;
	        if (args.length == 0) {
	            cpus = AffinityLock.cpuLayout().cpus() / 12;
	        } else {
	            cpus = Integer.valueOf(args[0]);
	        }

	        for (int i = 0; i < cpus; i++) {
	            acquireAndDoWork();
	        }
	    }

	    private static void acquireAndDoWork() {

	        Thread t = new Thread(() -> {
	            final SimpleDateFormat df = new SimpleDateFormat("yyyy.MM" + ".dd 'at' HH:mm:ss z");
	            try (AffinityLock al = Affinity.acquireLock()) { // 下面程序在一个CPU里面执行
	                String threadName = Thread.currentThread().getName();
	                System.out.println("Thread (" + threadName + ") locked onto cpu " + al.cpuId());

	                while (true) {
	                    System.out.println(df.format(new Date()) + " - Thread (" + threadName + ") doing work on cpu " + al.cpuId() + ". IsAllocated = " + al.isAllocated() + ", isBound = " + al.isBound() + ". " + al.toString());

	                    try {
	                        Thread.sleep(10000L);
	                    } catch (InterruptedException e) {
	                        //nothing
	                    }
	                }
	            }
	        });
	        t.start();
	    }
}
