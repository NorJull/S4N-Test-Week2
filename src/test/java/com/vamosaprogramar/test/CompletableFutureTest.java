package com.vamosaprogramar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CompletableFutureTest {

	private ExecutorService executorService;
	
	@Before
	public void setUp() {
		
		executorService = Executors.newFixedThreadPool(4);
	}
	
	@After
	public void closeThePool() throws InterruptedException {
		
		executorService.shutdown();
		executorService.awaitTermination(5, TimeUnit.SECONDS);
	}
	

	@Test
	public void completableFutureWithSupplyAsync() throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture =
				CompletableFuture.supplyAsync(() -> "NAREN");
		
		//Blocking
		String actual = completableFuture.get();
		
		assertEquals("NAREN", actual);
	}

	/************************************************
	 * Testing thenApply method
	 ***********************************************/
	
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
	public void completableFutureWithThenApplyAsync() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> completableFuture =
				CompletableFuture.supplyAsync(() -> 10);
		
		CompletableFuture<Integer> completableFuture2 =
				completableFuture.thenApplyAsync(x -> x*10);
		//Blocking
		Integer actual = completableFuture2.get();
		
		assertEquals(100, actual, 0.0);
	}
	
	@Test
	public void completableFutureWithThenApplyAsyncWithExecutor() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> completableFuture =
				CompletableFuture.supplyAsync(() -> 10);
		
		CompletableFuture<Integer> completableFuture2 =
				completableFuture.thenApplyAsync(x -> x*10, executorService);
		//Blocking
		Integer actual = completableFuture2.get();
		
		assertEquals(100, actual, 0.0);
	}
	
	/************************************************
	 * Testing thenAccept method
	 ***********************************************/	

		
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
	public void completableFutureWithThenAcceptAsync() throws InterruptedException, ExecutionException {
		
		CompletableFuture<Integer> completableFuture =
				CompletableFuture.supplyAsync(() -> 23);
		
		CompletableFuture<Void> completableFuture2 =
				completableFuture.thenAcceptAsync(x -> System.out.println("I'm "+x+" years old"));
		//Blocking
		completableFuture2.get();
		
	}
	
	@Test
	public void completableFutureWithThenAcceptAsyncwithExecutor() throws InterruptedException, ExecutionException {
		
		CompletableFuture<Integer> completableFuture =
				CompletableFuture.supplyAsync(() -> 23);
		
		CompletableFuture<Void> completableFuture2 =
				completableFuture.thenAcceptAsync(x -> System.out.println("I'm "+x+" years old"), executorService);
		//Blocking
		completableFuture2.get();
		
	}
	
	/*****************************************************
	 Testing thenRun method
	 ****************************************************/
	
	@Test
	public void completableFutureWithThenRun() {
		
		CompletableFuture<String> completableFuture = 
				CompletableFuture.supplyAsync(() -> "Good morning mom");
		CompletableFuture<Void> completableFuture2 =
				completableFuture.thenRun(() -> System.out.println("Morning son! How was ur night"));
	}
	
	/*****************************************************
	 Testing thenCompose method
	 ****************************************************/
	
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
	public void completableFutureWithThenComposeAsync() throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = 
				CompletableFuture.supplyAsync(() -> "Morning Naren!")
				.thenComposeAsync(x -> CompletableFuture.supplyAsync(() -> x+" How are you?"));
		
		//Blocking
		String actual = completableFuture.get();
		
		assertEquals("Morning Naren! How are you?", actual);
	}
	
	@Test
	public void completableFutureWithThenComposeAsyncWithExecutor() throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = 
				CompletableFuture.supplyAsync(() -> "Morning Naren!")
				.thenComposeAsync(x -> CompletableFuture.supplyAsync(() -> x+" How are you?"), executorService);
		
		//Blocking
		String actual = completableFuture.get();
		
		assertEquals("Morning Naren! How are you?", actual);
	}
	/**********************************************
	 Testing thenCombine method
	 **********************************************/
	
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
	public void completableFutureWithThenCombineAsync() throws InterruptedException, ExecutionException {
		
		CompletableFuture<Double> completableFuture =
				CompletableFuture.supplyAsync(() -> 23.0)
				.thenCombineAsync(CompletableFuture.supplyAsync(() -> 10.0), (x, y) -> x * y);
		//Blocking
		double actual = completableFuture.get();
		
		assertEquals(230.0, actual, 0.0);
	}
	
	
	@Test
	public void completableFutureWithThenCombineAsyncWithExecutor() throws InterruptedException, ExecutionException {
		
		CompletableFuture<Double> completableFuture =
				CompletableFuture.supplyAsync(() -> 23.0)
				.thenCombineAsync(CompletableFuture.supplyAsync(() -> 10.0), (x, y) -> x * y, executorService);
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
	
	@Test
	public void completableFuturesInParallel() throws InterruptedException, ExecutionException {
		
		CompletableFuture<String> completableFuture1 =
				CompletableFuture
				.supplyAsync(() -> Arrays.asList("a","b","c").stream().collect(Collectors.joining(",")));
		
		CompletableFuture<List<String>> completableFuture2 =
				CompletableFuture
				.supplyAsync(
						() -> Arrays.asList("Naren", " Fernanda").stream().map(String::toUpperCase).collect(Collectors.toList()));
				
		CompletableFuture.allOf(completableFuture1,completableFuture2).get();
	
		assertTrue(completableFuture1.isDone());
		assertTrue(completableFuture2.isDone());
	
	}
	
	@Test
	public void combinedResultsOfAllFutures() {
		CompletableFuture<String> completableFuture1 =
				CompletableFuture.supplyAsync(() -> "Hello");
		
		CompletableFuture<String> completableFuture2 =
				CompletableFuture.supplyAsync(() -> "Naren!");
		
		String actual = Stream.of(completableFuture1, completableFuture2)
				.map(x -> x.join())
				.collect(Collectors.joining(" "));
				
		assertEquals("Hello Naren!", actual);
	}

	@Test
	public void completableFutureWithHandle_thenThrowException() throws InterruptedException, ExecutionException {
		
		double dividend = 6;
		double divisor  = 0;
		
		CompletableFuture<String> completableFuture =
				CompletableFuture.supplyAsync(() -> {
					
					if(divisor == 0) {
						throw new ArithmeticException(); 
					}
					double quotient = dividend/divisor;
					
					return dividend + " divided by " + divisor + " is equals to " + quotient;
				}).handle((result,  exception) -> exception != null ? "It is not possible to divide by zero." : result);
		
		String actual = completableFuture.get();
		
		assertEquals("It is not possible to divide by zero.", actual);
		
	}
	
	@Test
	public void completableFutureWithHandle() throws InterruptedException, ExecutionException {
		
		double dividend = 6;
		double divisor  = 3;
		
		CompletableFuture<String> completableFuture =
				CompletableFuture.supplyAsync(() -> {
					
					if(divisor == 0) {
						throw new ArithmeticException(); 
					}
					double quotient = dividend/divisor;
					
					return dividend + " divided by " + divisor + " is equals to " + quotient;
				}).handle((result,  exception) -> exception != null ? "It is not possible to divide by zero." : result);
		
		String actual = completableFuture.get();

		assertEquals("6.0 divided by 3.0 is equals to 2.0", actual);
		
	}
	
	/**********************************************
	 Working with acceptEither, applyToEither 
	 **********************************************/
				
	@Test
	public void completableFutureWithAcceptEither() {
		CompletableFuture<String> completableFuture =
				CompletableFuture.supplyAsync(() -> "First");
		
		CompletableFuture<String> completableFuture2 =
				CompletableFuture.supplyAsync(() -> "Second");
		
		completableFuture.acceptEither(completableFuture2,(x) -> System.out.print("Do this regardless of who is finished first!"));
	}
	
	@Test
	public void completableFutureWithApplyToEither() {
		CompletableFuture<String> completableFuture =
				CompletableFuture.supplyAsync(() -> "First");
		
		CompletableFuture<String> completableFuture2 =
				CompletableFuture.supplyAsync(() -> "Second");
		
		CompletableFuture<String> completableFuture3 = 
				completableFuture.applyToEither(completableFuture2,String::toUpperCase);
	}
	
}
