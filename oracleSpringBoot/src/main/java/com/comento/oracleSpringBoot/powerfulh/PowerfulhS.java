package com.comento.oracleSpringBoot.powerfulh;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
@Service
public class PowerfulhS {
	@Async
	public Future<Integer> asyncTest(int a) throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		return new AsyncResult<Integer>(a++);
	}
	@Async
	public Future<Integer> asyncInt(int a) throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		System.out.println("done");
		return new AsyncResult<Integer>(a);
	}
}
