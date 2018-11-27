package com.vamosaprogramar.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.junit.Test;

public class CompletableFutureTest {

	/**********************************************************
	 * supplyAsync[Testing]
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * 
	 *********************************************************/

	@Test
	public void completableFutureWithSupplyAsync() throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture =
				CompletableFuture.supplyAsync(() -> "NAREN");
		
		//Blocking
		String actual = completableFuture.get();
		
		assertEquals("NAREN", actual);
	}

	@Test
	public void completableFutureWithThenApply() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> completableFuture =
				CompletableFuture.supplyAsync(() -> 10);
		
		CompletableFuture<Integer> completableFuture2 =
				completableFuture.thenApply(x -> x*10);
		//Blocking
		Integer actual = completableFuture2.get();
		
		assertEquals(100, actual, 0.0);
	}
	
	
}
