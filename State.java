package project_1_CN;

import java.util.List;

/*
 * The State object represents a particular configuration of water levels in each jug
 * This can also be referred to as the Node class
 * It is used to represent a particular state of the jugs
 * Edges between states represent the actions taken to transition from one state to another
 */
public class State {
	
	//The parent or previous state
	private State parent;
	
	/*
	 * An array of Jug objects containing the status and size of each jug
	 */
	public List<Jug> jugs;
	
	/*
	 * goal represents the target value of the puzzle 
	 */
	int goal;
	
	/*
	 * The cost of reaching this state from the initial state. 
	 * Kind of like the progress toward the goal state or sunk cost
	 * The cost is typically defined as the number of moves required to get to this state from the initial state.
	 */
	int steps; 
	
	/*
	 * progress represents how much what has been water has been put towards the goal so far.
	 */
	int progress;
	
	public State(List<Jug> j)
	{
		this.jugs = j;
		this.steps = 0;
		this.parent = null;
	}
	
	public State(List<Jug> j, int s, State p)
	{
		this.jugs = j;
		this.steps = s;
		this.parent = p;
	}
	
	public static int jugHeuristic(State state, int goal) 
	{
		int dist = 0;
		for(int i=0; i <= state.jugs.size(); i++)
		{
			dist += Math.abs(state.jugs.get(i).getStatus() - goal);
		}
	    return dist / state.jugs.size();
	} // heuristic
	
	


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof State) {
            State s = (State) obj;
            
            for(int i = 0; i<=0; i++)
            {
            	// return false if the status of the jugs do not match
            	if(this.jugs.get(i) != s.jugs.get(i))
            		return false;
            } // for
            return true;
        }// if
        return false;
    } // equals()

} // State Class
