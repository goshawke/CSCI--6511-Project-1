package project_1_CN;

public class Jug {
	
	int size;
	int target;
	int status;
	
	public Jug(int size, int target)
	{
		this.size = size;
		this.target = target;
		this.status = 0;
	} // constructor
	
	public Jug()
	{
		this.size = 0;
		this.target = 0;
		this.status = 0;
	} // default constructor
	
	
	
	/*
	 * method to empty Jug
	 */
	public void emptyPitcher()
	{
		System.out.println("Emptying Pitcher ");
		this.setStatus(0); //update status of pitcher to EMPTY
	} // emptyPitcher()
	
	
	/*
	 * method to pour Pitcher X into Pitcher Y
	 * adds one to the number of steps, updates the status of both Pitchers
	 */
	public void pourXintoY(Jug y)
	{
		int spaceInJug = y.getSize() - y.getStatus(); // the remaining space in Jug y
		
		// if the other jug is already full
		if(spaceInJug == 0)
		{
			System.out.println("NOT Pouring Pitcher 1 into Pitcher 2 ");
			return; // nothing happens
		}
		else if (this.getStatus() == 0) // if Jug x is empty
		{
			System.out.println("NOT Pouring Pitcher 1 into Pitcher 2 ");
			return; // nothing happens
		}


		else if(spaceInJug >= this.getStatus()) // if Jug x will not completely fill Jug y when poured
		{
			y.setStatus(y.getStatus() + this.getStatus()); // update Jug y
			this.setStatus(0); // Update jug to empty
			System.out.println("Pouring Pitcher 1 into Pitcher 2");
		}
		else if(spaceInJug < this.getStatus()) // When Jug y doesn't have enough space for Jug x to be poured into it
		{
			this.setStatus(this.getStatus() - spaceInJug); // pouring Jug x into Jug y until full but there is left over
			y.setStatus(y.getSize()); // set Jug y to FULL
			System.out.println("Pouring Pitcher 1 into Pitcher 2 ");
			
		}
			
	} // pourXintoY()
	
	
	/*
	 * method to fill one of the pitchers
	 * adds one to the number of steps, updates the status of the pitcher being filled to full
	 */
	public void fill()
	{
		System.out.println("Filling Pitcher");
		this.setStatus(this.getSize()); // update status of Pitcher to FULL
	} // fill()
	
	
	/*
	 * method to pour one of the pitchers into the goal pitcher
	 * adds one to the number of steps, updates the progress toward the goal,
	 * and updates the status of the pitcher being filled to empty
	 */
	public void pourIntoGoal()
	{
		
		System.out.println("Pour Pitcher " + this.getStatus() + " into goal");
		
		
		int goalProg = this.getTarget() + this.getStatus();
		System.out.println("Progress towards goal: " + goalProg);
		
		this.setStatus(0); // update status of Jug to empty		
	} // pourIntoGoal()
	
	
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
