package project_1_CN;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.function.ToDoubleFunction;


public class Search
{
	
	public static State AStarSearch(List<Jug> jugs, int goal) 
	{
        PriorityQueue<State> fringe = new PriorityQueue<>((s1, s2) -> {
        	 int f1 = s1.cost + State.jugHeuristic(s1, goal);
             int f2 = s2.cost + State.jugHeuristic(s2, goal);
             return Integer.compare(f1, f2);
         });
        
        
        HashSet<State> explored = new HashSet<>();

        State start = new State(jugs);
        fringe.add(start);
		
		
		while (!fringe.isEmpty()) {
            State current = fringe.poll();

            if (current.jugX == target || current.jugY == target) {
                return current;
            }

            explored.add(current);

            // fill jugX
            State next = new State(jugX, current.jugY, current.cost + 1, current);
            if (!explored.contains(next)) {
            	fringe.add(next);
            }

            // fill jugY
            next = new State(current.jugX, jugY, current.cost + 1, current);
            if (!explored.contains(next)) {
            	fringe.add(next);
            }

            // empty jugX
            next = new State(0, current.jugY, current.cost + 1, current);
            if (!explored.contains(next)) {
            	fringe.add(next);
            }

            // empty jugY
            next = new State(current.jugX, 0, current.cost + 1, current);
            if (!explored.contains(next)) {
            	fringe.add(next);
            }

            // pour jugX into jugY
            int amount = Math.min(current.jugX, jugY - current.jugY);
            next = new State(current.jugX - amount, current.jugY + amount, current.cost + 1, current);
            if (!explored.contains(next)) {
            	fringe.add(next);
            }

            // pour jugY
		}
		return null; 
	}
	

	
	public static void main(String [] args) throws FileNotFoundException
	{
		File file = new File("input1.txt");
		Scanner sc = new Scanner(file);
		String[] sizes = sc.nextLine().split(",");
		int goal = Integer.parseInt(sc.nextLine());
		
		List<Jug> jugs = new LinkedList();
		
		for (int i = 0; i < sizes.length; i++)
		{
			// sets the number of jugs and their sizes for the particular puzzle
			jugs.add(new Jug(Integer.parseInt(sizes[i]), goal));
		}
			
		System.out.println("Jug Problem Solver");
		System.out.println("The goal is " + goal + " and there are " + sizes.length + " jugs");
		System.out.println("The jug sizes are as follows: ");
		for(int i = 0; i<= sizes.length; i++)
			System.out.println("Jug " + (i+1) + ": " + sizes[i]);
		System.out.println("Goal is " + goal);
		
		
		State puzzle = AStarSearch(jugs, goal);

		
		if(goal == null)
			System.out.print("No Solution");
		else
			System.out.print("Solution is " + puzzle.cost + " steps");

		
		
	} // main


}