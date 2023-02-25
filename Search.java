import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Search {

    private final int[] sizes; // pitcher sizes
    private final int goal; // goal amount

    public int getGoal() {
		return goal;
	}

	public Search(int[] sizes, int goal) {
        this.sizes = sizes;
        this.goal = goal;
    }

    public List<String> solve() {
        // Create the initial state
        State initialState = new State(sizes, this.goal);
        
        //TEST
        //System.out.println(initialState.toString());
        //TEST
        
        // Create the priority queue for the open set
        PriorityQueue<State> openSet = new PriorityQueue<>();
        openSet.add(initialState);

        // Create the set for the closed set
        Set<State> closedSet = new HashSet<>();

        // Iterate until the open set is empty or a goal state is found
        while (!openSet.isEmpty()) {
            // Remove the state with the lowest A* cost from the open set
            State currentState = openSet.poll();
            
            //TEST
            //System.out.println(currentState.toString());
            //TEST

            // Check if the current state is the goal state
            if (currentState.isGoalState()) {
            	System.out.println("Goal State: " + currentState.toString());
                return currentState.getPath();
            }

            // Add the current state to the closed set
            closedSet.add(currentState);

            // Generate the next states
            List<State> nextStates = currentState.getNextStates();
            
            // TEST - PASS
            //System.out.println("Number of next states: " + nextStates.size());
            // TEST - PASS

            // Iterate through the next states
            for (State nextState : nextStates) {
            	
           
            	// Check if the next state is in the closed set
            	 if (closedSet.contains(nextState)) {
            		 // System.out.println("Skip State");
                     continue;
                 }
            	// if True it means that we already visited this state

                
             	
                
                

                // Calculate the cost of the next state
                int cost = currentState.getCost() + 1;
                

                
             // Check if the next state is already in the open set
                if (openSet.contains(nextState)) {
                    // Find the existing state in the open set
                    State existingState = openSet.stream().filter(s -> s.equals(nextState)).findFirst().orElse(null);
                    
                    // Check if the existing state has a lower cost
                    if (existingState.getCost() <= cost) {
                        continue;
                    }
                    
                    // Remove the existing state from the open set
                    openSet.remove(existingState);
                    // System.out.println("Removing Existing State");
                }
                
                
                // TEST - PASS
                // System.out.println("Next State: " + nextState);
                // TEST - PASS
                
       

                // Update the cost of the next state and add it to the open set
                nextState.setCost(cost);
                nextState.setHeuristic(calculateHeuristic(nextState.status));
                openSet.add(nextState);
                
            }
            
        }

        // No solution found
        return null;
    }

    private int calculateHeuristic(int[] status) 
    {
    	int minDiff = Integer.MAX_VALUE;

    	// Find the jug closest to the goal volume
    	for (int i = 1; i < status.length; i++) {
    		
    		int diff = Math.abs(status[i] + status[0] - (goal));
    		if (diff < minDiff) {
    			minDiff = diff;
    		}

    	}

    	// Check if pouring water from one jug to another can get closer to the goal volume
    	for (int i = 1; i < status.length; i++) {
    		for (int j = 0; j < status.length; j++) {
    			if (i == j) {
    				continue;
    			}

    			int maxTransfer = 0;
    			if(j!=0)
    			{
    				// Calculate the maximum amount of water that can be transferred from jug i to jug j
    				maxTransfer = Math.min(status[i], sizes[j] - status[j]);
    				if(j==0 & status[i] > (goal - status[0])) {
    					maxTransfer = 0;	
    				}

    				if (maxTransfer > 0) {
    					int[] newStatus = Arrays.copyOf(status, status.length);
    					newStatus[i] -= maxTransfer;
    					newStatus[j] += maxTransfer;
    					int newDiff = Math.abs(newStatus[j] - (goal - status[0]));
    					if (newDiff < minDiff) {
    						minDiff = newDiff;
    					}
    				}
    			}
    		}
    	}

    	return minDiff;



    }
    
    
    
    
    

    private static class State implements Comparable<State> {
        private final int[] sizes; // initial sizes of all pitchers
        private int cost;
        private int heuristic;
        private final List<String> path;
        public final int target; // the final goal
        public int [] status; // current fill levels

        public State(int[] pitchers, int goal) // initial state
        {
            this.sizes = Arrays.copyOf(pitchers, pitchers.length);
            this.cost = 0;
            this.target = goal;
            this.status = new int [pitchers.length];
            this.status = this.setStatusZero();
            this.heuristic = 0;
            this.path = new ArrayList<>();
        }
        
      

		public State(int[] sizes, int cost, int[] status, int goal) {
            this.sizes = Arrays.copyOf(sizes, sizes.length);
            this.cost = cost;
            this.heuristic = 0;
            
            this.target = goal;
            this.status = status;
            this.path = new ArrayList<>();
        }
        
	  @Override
			public String toString() {
				return "State [sizes=" + Arrays.toString(sizes) + ", cost=" + cost + ", target=" + target + ", status="
						+ Arrays.toString(status) + "]";
			}
	  
	  
        public int[] getSizes() {
            return sizes;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }
       
        
        public int getHeuristic() {
            return heuristic;
        }
        
        public void setHeuristic(int heuristic) {
            this.heuristic = heuristic;
        }

        
        
        // this method initiates all values in the Status array to zero
        public int[] setStatusZero()
        {
        	int [] s = new int [this.status.length];
        	for(int i = 0; i < this.status.length; i++)
        	{
        		s[i] = 0;
        	}
        	return s;
        }

        public List<String> getPath() {
            return path;
        }

        public boolean isGoalState() {
            return this.status[0] == this.target; 
        }

        public List<State> getNextStates() {
            List<State> nextStates = new ArrayList<>();
            

            // Iterate through all pairs of pitchers
            // i starts at 1 bc water cannot be poured from the goal pitcher to a finite pitcher
            for (int i = 1; i < sizes.length; i++) {
                for (int j = 0; j < sizes.length; j++) {
                	
                    if (i == j) 
                    {
                    	// a pitcher cannot be poured into itself 	
                    	continue;
                    }
                    
                    int newStatus[] = this.status.clone(); // set temp to current status array

          
            		// int spaceInJugI = this.sizes[i] - this.status[i]; // the remaining space in Jug i
            		int spaceInJugJ = this.sizes[j] - newStatus[j]; // the remaining space in Jug j

            		if((spaceInJugJ == 0) || (newStatus[i] == 0)) // if there is no room in Pitcher J or Pitcher I is empty
            			continue;

            		// Pouring I into J States

            		


            		if((spaceInJugJ == 0) || (this.status[i] == 0)) // if there is no room in Pitcher J or Pitcher I is empty
            			continue;
            		else if(spaceInJugJ <= newStatus[i] & j != 0) // when there isn't enough space in Pitcher J for all of Pitcher I to be pouring in it
            		{
            			newStatus[i] -= spaceInJugJ;
            			newStatus[j] = this.sizes[j];
            		}
            		else if(spaceInJugJ <= newStatus[i] & j == 0) // when there isn't enough space in Pitcher J for all of Pitcher I to be pouring in it
            		{
            			if(spaceInJugJ != newStatus[i])
            				continue;
            			else
            			{
            				newStatus[i] -= 0;
            				newStatus[j] = this.sizes[j];
            			}
            		
            		}
            		else if(spaceInJugJ > newStatus[i]) // when all of pitcher I fits within pitcher J
            		{
            			newStatus[j] += newStatus[i]; // calc new status of Jug j
            			newStatus[i] = 0; // calc new status of Jug i
            		}
            		else
            			continue;

            		// TEST
            		//for(int k = 0; k< sizes.length; k++)
            		//	System.out.print(newStatus[k] + " ");
            		// TEST

            		State newState = new State(this.sizes, cost + 1, newStatus, this.target);
            		newState.path.addAll(path);
            		newState.path.add(String.format("Pour from pitcher %d to pitcher %d", i, j));
            		nextStates.add(newState);
            		
            		
                } // for j
                
                if (status[i]!= 0) { // emptying
            		int newStatus2[] = this.status.clone(); // set temp to current status array
        			newStatus2[i] = 0;
        			
        			// TEST - PASS
                    //for(int k = 0; k< sizes.length; k++)
            		//	System.out.println("Pitcher " + k + ": " + newStatus[k] + " ");
                    // TEST - PASS
        			
        			State newState2 = new State(this.sizes, cost + 1, newStatus2, this.target);
                    newState2.path.addAll(path);
                    newState2.path.add(String.format("Empty pitcher %d", i));
                    nextStates.add(newState2);
                    // newStatus = null;
                }
            	if (sizes[i] != status[i]) { // filling
            		int newStatus2[] = this.status.clone(); // set temp to current status array
        			newStatus2[i] = this.sizes[i];
        			
        			// TEST - PASS
                    //for(int k = 0; k< sizes.length; k++)
            		//	System.out.println("Pitcher " + k + ": " + newStatus[k] + " ");
                    // TEST - PASS
        			
        			State newState2 = new State(this.sizes, cost + 1, newStatus2, this.target);
                    newState2.path.addAll(path);
                    newState2.path.add(String.format("Fill pitcher %d", i));
                    nextStates.add(newState2);
                    // newStatus = null;
                }
            } // for i
            

//            // Iterate through all pitchers and create a new state by filling it or emptying it
//            for (int i = 1; i < sizes.length; i++) {
//            	if (status[i]!= 0) { // emptying
//            		int newStatus[] = this.status.clone(); // set temp to current status array
//        			newStatus[i] = 0;
//        			
//        			// TEST - PASS
//                    //for(int k = 0; k< sizes.length; k++)
//            		//	System.out.println("Pitcher " + k + ": " + newStatus[k] + " ");
//                    // TEST - PASS
//        			
//        			State newState = new State(this.sizes, cost + 1, newStatus, this.target);
//                    newState.path.addAll(path);
//                    newState.path.add(String.format("Empty pitcher %d", i));
//                    nextStates.add(newState);
//                    // newStatus = null;
//                }
//            	if (sizes[i] != status[i]) { // filling
//            		int newStatus[] = this.status.clone(); // set temp to current status array
//        			newStatus[i] = this.sizes[i];
//        			
//        			// TEST - PASS
//                    //for(int k = 0; k< sizes.length; k++)
//            		//	System.out.println("Pitcher " + k + ": " + newStatus[k] + " ");
//                    // TEST - PASS
//        			
//        			State newState = new State(this.sizes, cost + 1, newStatus, this.target);
//                    newState.path.addAll(path);
//                    newState.path.add(String.format("Fill pitcher %d", i));
//                    nextStates.add(newState);
//                    // newStatus = null;
//                }
//            	
//            	
//            }

            return nextStates;
        } // getNextStates()


        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            State state = (State) o;
            return Arrays.equals(this.status, state.status);
        }
        

        @Override
        public int hashCode() {
            return Arrays.hashCode(sizes);
        }
        
        @Override
        public int compareTo(State other) {
            int f = cost + heuristic;
            int otherF = other.cost + other.heuristic;
            return Integer.compare(f, otherF);
        }
    }
        

    public static void main(String[] args) throws FileNotFoundException {
    	
    	
    	File file = new File("input2.txt");
		Scanner sc = new Scanner(file);
		String[] sizes = sc.nextLine().split(",");
		int goal = Integer.parseInt(sc.nextLine());
		
		// Read the capacity of each pitcher
        int[] pitchers = new int[sizes.length + 1];
		pitchers[0] = goal; // goal pitcher
		
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
            Search problem = new Search(pitchers, goal);
            
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
