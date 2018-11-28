package com.vamosaprogramar.test;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
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
	
	@Test
	public void completableFutureWithThenAccept() throws InterruptedException, ExecutionException {
		
		CompletableFuture<Integer> completableFuture =
				CompletableFuture.supplyAsync(() -> 23);
		
		CompletableFuture<Void> completableFuture2 =
				completableFuture.thenAccept(x -> System.out.println("I'm "+x+" years old"));
		//Blocking
		completableFuture2.get();
		
	}
	
	@Test
	public void completableFutureWithThenRun() {
		
		CompletableFuture<String> completableFuture = 
				CompletableFuture.supplyAsync(() -> "Good morning mom");
		CompletableFuture<Void> completableFuture2 =
				completableFuture.thenRun(() -> System.out.println("Morning son! How was ur night"));
	}
	
	@Test
	public void completableFutureWithThenCompose() throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = 
				CompletableFuture.supplyAsync(() -> "Morning Naren!")
				.thenCompose(x -> CompletableFuture.supplyAsync(() -> x+" How are you?"));
		
		//Blocking
		String actual = completableFuture.get();
		
		assertEquals("Morning Naren! How are you?", actual);
	}
	
	@Test
	public void completableFutureWithThenCombine() throws InterruptedException, ExecutionException {
		
		CompletableFuture<Double> completableFuture =
				CompletableFuture.supplyAsync(() -> 23.0)
				.thenCombine(CompletableFuture.supplyAsync(() -> 10.0), (x, y) -> x * y);
		//Blocking
		double actual = completableFuture.get();
		
		assertEquals(230.0, actual, 0.0);
	}
	
	
	@Test
	public void completableFutureWithThenAcceptBoth() {
		
		CompletableFuture<Void> completableFuture = 
				CompletableFuture.supplyAsync(() -> 10)
				.thenAcceptBoth(CompletableFuture.supplyAsync(() -> 13), (x, y) -> System.out.println("My age is "+(x+y)));
		
	}
	
}
