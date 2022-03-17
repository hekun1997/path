package coderxz.uestc.dstarlite;

import java.util.List;

public abstract class Problem {
	
	protected State goal;
	protected State start;
	
	protected List<State> changes;
	
	public abstract List<State> getSuccessors(State state);
	public abstract List<State> getPredecessors(State state);
	
	/** This is the estimated value from the start state to the parameter 
	 * passed in.
	 */
	public abstract double getHeuristic(State state);
	
	/** This method should return the cost of traversing the edge u(init, end).
	 * @param init The state that you are leaving.
	 * @param end The state that you are entering.
	 * @return The cost to take the edge.
	 */
	public abstract double getCost(State init, State end);
	
	/** This method will let the solver know whether or not the end points have
	 * changed.
	 * @return True if the end points have changed, false otherwise.
	 */
	public abstract boolean endpointsChanged();
	
	/** This method is used to clear any flags for subsequent runs. This method
	 * should also clear the end points changed flag.
	 */
	public abstract void clearFlags();
	
	/** This is used to set all the g and rhs values to infinity. */
	public abstract void intialize();
	
	public abstract String stringG();
	public abstract String stringRHS();
	
	/** This method will return the changes seen thus far.
	 * 
	 * @return A list of states that have changed in some manner (ie. weights).
	 */
	public List<State> getChanges() {
		return changes;
	}
	
	public void clearChanges() {
		changes.clear();
	}
	
	public State getGoal() {
		return goal;
	}
	
	public State getStart() {
		return start;
	}

}
