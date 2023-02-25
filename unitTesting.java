import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class unitTesting {

	@Test
	void test1() {

		int[] sizes = {2,5,6,72};
		int goal = 143;

		// Read the capacity of each pitcher
		int[] pitchers = new int[sizes.length + 1];
		pitchers[0] = goal; // goal pitcher

		for (int i = 0; i < sizes.length; i++)
		{
			pitchers[i+1] = sizes[i];
			// sets the number of jugs and their sizes for the particular puzzle
		}

		// Solve the problem
		Search problem = new Search(pitchers, goal);

		List<String> path = problem.solve();
		
		int num = 0;
		// Output the solution
		if (path == null) {
			System.out.println("No solution found");
		} else {
			
			System.out.println("Success!!! \nShortest path:");
			for (String step : path) {
				System.out.println(step);
				num++;
			}
			System.out.println("Number of Steps: " + num);
		}
		
		problem = null;
		path = null;
		
		
		assertEquals(7, num);

	}
	
	@Test
	void test2() {

		int[] sizes = {1,4,10,15,22};
		int goal = 181;

		// Read the capacity of each pitcher
		int[] pitchers = new int[sizes.length + 1];
		pitchers[0] = goal; // goal pitcher

		for (int i = 0; i < sizes.length; i++)
		{
			pitchers[i+1] = sizes[i];
			// sets the number of jugs and their sizes for the particular puzzle
		}

		// Solve the problem
		Search problem = new Search(pitchers, goal);

		List<String> path = problem.solve();
		
		int num = 0;
		// Output the solution
		if (path == null) {
			System.out.println("No solution found");
		} else {
			
			System.out.println("Success!!! \nShortest path:");
			for (String step : path) {
				System.out.println(step);
				num++;
			}
			System.out.println("Number of Steps: " + num);
		}
		
		assertEquals(19, num);
	}
	
	@Test
	void test3() {

		int[] sizes = {3,6};
		int goal = 2;

		// Read the capacity of each pitcher
		int[] pitchers = new int[sizes.length + 1];
		pitchers[0] = goal; // goal pitcher

		for (int i = 0; i < sizes.length; i++)
		{
			pitchers[i+1] = sizes[i];
			// sets the number of jugs and their sizes for the particular puzzle
		}

		// Solve the problem
		Search problem = new Search(pitchers, goal);

		List<String> path = problem.solve();
		
		int num = 0;
		// Output the solution
		if (path == null) {
			num = -1;
			System.out.println("No solution found");
		} else {
			
			System.out.println("Success!!! \nShortest path:");
			for (String step : path) {
				System.out.println(step);
				num++;
			}
			System.out.println("Number of Steps: " + num);
		}
		
		assertEquals(-1, num);
	}
	
	
	@Test
	void test4() {

		int[] sizes = {2};
		int goal = 143;

		// Read the capacity of each pitcher
		int[] pitchers = new int[sizes.length + 1];
		pitchers[0] = goal; // goal pitcher

		for (int i = 0; i < sizes.length; i++)
		{
			pitchers[i+1] = sizes[i];
			// sets the number of jugs and their sizes for the particular puzzle
		}

		// Solve the problem
		Search problem = new Search(pitchers, goal);

		List<String> path = problem.solve();
		
		int num = 0;
		// Output the solution
		if (path == null) {
			num = -1;
			System.out.println("No solution found");
		} else {
			
			System.out.println("Success!!! \nShortest path:");
			for (String step : path) {
				System.out.println(step);
				num++;
			}
			System.out.println("Number of Steps: " + num);
		}
		
		assertEquals(-1, num);
	}
	
	@Test
	void test5() {

		int[] sizes = {2,3,5,19,121,852};
		int goal = 11443;

		// Read the capacity of each pitcher
		int[] pitchers = new int[sizes.length + 1];
		pitchers[0] = goal; // goal pitcher

		for (int i = 0; i < sizes.length; i++)
		{
			pitchers[i+1] = sizes[i];
			// sets the number of jugs and their sizes for the particular puzzle
		}

		// Solve the problem
		Search problem = new Search(pitchers, goal);

		List<String> path = problem.solve();
		
		int num = 0;
		// Output the solution
		if (path == null) {
			num = -1;
			System.out.println("No solution found");
		} else {
			
			System.out.println("Success!!! \nShortest path:");
			for (String step : path) {
				System.out.println(step);
				num++;
			}
			System.out.println("Number of Steps: " + num);
		}
		
		assertEquals(36, num);

	}

}
