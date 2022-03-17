package coderxz.uestc.dstarlite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class DStarLite {
	
	private PriorityQueue<State> Q;
	private Problem problem;
	private boolean initialized;
	private List<State> expanded;
	
	/** Construct a new DStarLite search algorithm object from the given
	 * problem.
	 * @param prob The problem to DStarLite search over.
	 */
	public DStarLite(Problem prob) {
		this.problem = prob;
		this.initialized = false;
		this.expanded = new LinkedList<State>();
	}
	
	private void updateVertex(State s) {
		/* If the vertex is not the goal state then we want to find the minimum
		 * of its neighbors and use that information to update its current
		 * value. */
		if(!s.equals(problem.getGoal())) {
			double min = Double.POSITIVE_INFINITY;
			for(State succ : problem.getSuccessors(s)) {
				double current = problem.getCost(s, succ) + succ.getG();
				if(current < min) {
					min = current;
				}
			}
			s.setRHS(min);
		}

		/* We then remove the state from the queue, and only if we still have
		 * a discrepancy do we put the state back onto the queue. */
		Q.remove(s);
		
		if(s.getG() != s.getRHS()) {
			Q.add(s);
		}
	}
	
	private void initialize() {
		Q = new PriorityQueue<State>();
		problem.intialize();
		State goal = problem.getGoal();
		goal.setRHS(0);
		Q.add(goal);
	}
	
	private void computeShortestPath(){
		State current = null;
		for(State s : problem.getChanges()) {
			updateVertex(s);
			updateVertices(problem.getPredecessors(s));
		}
		problem.clearChanges();
		while(problem.getStart().getG() != problem.getStart().getRHS() || 
				(!Q.isEmpty() &&
				Q.peek().Comparable(problem.getStart()) < 0)) {
			current = Q.remove();
			
			if(current.getG() > current.getRHS()) {
				current.setG(current.getRHS());
				updateVertices(problem.getPredecessors(current));
			} else {
				current.setG(Double.POSITIVE_INFINITY);
				updateVertices(problem.getPredecessors(current));
				updateVertex(current);
			}
			
			if(!expanded.contains(current)){
				expanded.add(current);
			}
		}
	}
	
	/* Updates all the vertex values for every vertex in the list. */
	private void updateVertices(List<State> states) {
		for(int i = 0; i < states.size(); i++) {
			updateVertex(states.get(i));
		}
	}
	
	/** Returns the shortest path from the start to the goal state for the 
	 * pre-defined problem.
	 * @return A list of the states to travel to in order or null if no such
	 * path exists.
	 */
	public List<State> getShortestPath() {
		/* If the problem has yet to be initialized or the problem's definition
		 * has changed, we need to reinitialize the solver.
		 */
		if(!this.initialized || problem.endpointsChanged()) {
			this.initialize();
			this.initialized = true;
			problem.clearFlags();
		} else {
			Q.clear();
			Q.add(problem.getStart());
			problem.getStart().setG(Double.POSITIVE_INFINITY);
		}
		/* We remove all the previously expanded states, in preparation for
		 * the current round of path finding.
		 */
		this.expanded.clear();
		
		/* Compute the path. */
		this.computeShortestPath();
		return getPath();
	}
	
	/** This method will return all the expanded states for the most recent
	 * expansion phase.
	 * @return A list of the expanded states.
	 */
	public List<State> getExpandedStates() {
		return expanded;
	}
	
	/** Returns a list of State objects in the order in which you should
	 * traverse them to get to the goal.
	 * @return An in order list of all the states to traverse in order to reach
	 * the goal state from the start state. If there is no path, null is 
	 * returned.
	 */
	private List<State> getPath() {
		State current = problem.getStart(), next = null;
		List<State> path = new ArrayList<State>();
		path.add(current);
		double curMin = Double.POSITIVE_INFINITY;
		double cost;
		
		if(problem.getStart().getG() == Double.POSITIVE_INFINITY) {
			return null;
		}
		
		while(!current.equals(problem.getGoal())) {
			/* We want to travel from the current state to the next state that
			 * minimizes g(state') + cost(state, state').
			 */
			curMin = Double.POSITIVE_INFINITY;
			List<State> successors = problem.getSuccessors(current);
			for(State s : successors) {
				cost = problem.getCost(current, s) + s.getG();
				if(cost < curMin) {
					next = s;
					curMin = cost;
				}
			}
			current = next;
			next = null;
			path.add(current);
		}
		
		return path;
	}

}
