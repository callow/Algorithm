package com.algo.util.scheduler;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SchedulerDemo {
	
	public static final ScheduledExecutorService SCHEDULER = new ScheduledThreadPoolExecutor(1);
	public static final AtomicReference<ScheduledFuture<?>> JOB_RUNNER = new AtomicReference<>();
	
	public static final int MAX_TRIES = 20;
	
	public static void start() {
		if (JOB_RUNNER.get() == null || JOB_RUNNER.get().isCancelled()) {
			System.out.println("Remote Status job started");
			RemoteStatusChecker job1 = new RemoteStatusChecker(MAX_TRIES, callbackMsg -> {
				System.out.println(callbackMsg);
				stop();
			});
			ScheduledFuture<?> future = SCHEDULER.scheduleWithFixedDelay(job1, 0, 6, TimeUnit.SECONDS);
			JOB_RUNNER.set(future);
		} else {
			System.out.println("Job is cancelled");
		}
	}
	
	public static void stop() {
		ScheduledFuture<?> future = JOB_RUNNER.get();
		if (future != null && !future.isCancelled()) {
			future.cancel(false);
			System.out.println("Job is cancelled successfully.");
			System.exit(0);
		} else {
			System.out.println("Job is not running");
		}
	}
	
	
	public static void main(String[] args) {
		start();
	}
	
}
