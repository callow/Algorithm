package com.algo.util.scheduler;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class RemoteStatusChecker implements Runnable {
	
	private final AtomicInteger tryCounter = new AtomicInteger(0);
	private final int maxTries;
	private final Consumer<String> stopCallback;
	
	public RemoteStatusChecker(int maxTries, Consumer<String> stopCallback) {
		this.maxTries = maxTries;
		this.stopCallback = stopCallback;
	}

	@Override
	public void run() {
		int current = tryCounter.incrementAndGet();
		System.out.println("Checking job. Try: " + current);

		boolean status = checkRemoteMachine();  // ¡û simulated logic

		if (status) {
//			System.out.println("Remote Status is ready. Stopping job. status is:" + status);
			stopCallback.accept("Good! Remote Status is ready. Stopping job. status is: " + status);
			return;
		}

		if (current >= maxTries) {
//			System.out.println("Max tries reached. Stopping job. current is :" + current);
			stopCallback.accept("Bad! Max tries reached. Stopping job. current is: " + current);
		}
		
	}
	
	boolean checkRemoteMachine() {
		return Math.random() > 0.8;
	}

}
