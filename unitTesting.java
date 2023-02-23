
package project_1_CN;

import java.io.File;
import java.util.List;
import java.util.Scanner;

class unitTesting {

	void test1() {
		
		
		int[] sizes = {1,4,10,15,22};
		int goal = 181;
		
		// Read the capacity of each pitcher
        int[] pitchers = new int[sizes.length + 1];
		pitchers[0] = goal * 5; // goal pitcher
		
		for (int i = 0; i < sizes.length; i++)
		{
			pitchers[i+1] = Integer.parseInt(sizes[i]);
			// sets the number of jugs and their sizes for the particular puzzle
		}
			
		/*
		 * Prints info about the problem to console for error checking
		 */
		System.out.println("Jug Problem Solver");
		System.out.println("The goal is " + goal + " and there are " + sizes.length + " jugs");
		System.out.println("The Pitcher sizes are as follows: "); // Does not print the goal pitcher (Index 0)
		for(int i = 0; i< sizes.length; i++)
			System.out.println("Pitcher " + (i+1) + ": " + sizes[i]);
		System.out.println("Goal is " + goal);
		
            // Solve the problem
            WaterPitcherProblem problem = new WaterPitcherProblem(pitchers, goal);
            
            List<String> path = problem.solve();

            // Output the solution
            if (path == null) {
                System.out.println("No solution found");
            } else {
            	int num = 0;
                System.out.println("Success!!! \nShortest path:");
                for (String step : path) {
                    System.out.println(step);
                    num++;
                }
                System.out.println("Number of Steps: " + num);
            }
            
	}

}
