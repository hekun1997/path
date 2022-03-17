package coderxz.uestc.dstarlite;

public abstract class State implements Comparable<State> {
	
	protected double rhs, g, heuristic;
	protected double key;
	
	public State() {
		this.rhs = Double.POSITIVE_INFINITY;
		this.g = Double.POSITIVE_INFINITY;
		this.key = Double.POSITIVE_INFINITY;
		this.heuristic = Double.POSITIVE_INFINITY;
	}
	
	public State(State state) {
		this.key = state.key;
		this.g = state.g;
		this.rhs = state.rhs;
		this.heuristic = state.heuristic;
	}
	
	public abstract boolean equals(State other);
	public abstract String toString();
	
	/** This compare function will compare the right hand side and goal
	 * estimates to each other and return 1 if the other state is smaller and
	 * -1 if the other state is bigger.
	 * @param other The state to compare.
	 * @return
	 */
	public int Comparable(State other){
		if((this.key + this.heuristic) < (other.key + other.heuristic)) {
			return -1;
		} else if ((this.key + this.heuristic) == (other.key + other.heuristic) && this.key < other.key) {
			return -1;
		} else if ((this.key + this.heuristic) == (other.key + other.heuristic) && this.key == other.key) {
			return 0;
		} else {
			return 1;
		}
	}
	
	public int compareTo(State other) {
		if((this.key + this.heuristic) < (other.key + other.heuristic)) {
			return -1;
		} else if (this.key < other.key) {
			return -1;
		} else {
			return 1;
		}
	}
	
	public double getKey() {
		return this.key;
	}
	
	public void setRHS(double val){
		this.rhs = val;
		this.key = (rhs > g) ? g : rhs;
	}
	
	public void setG(double val) {
		this.g = val;
		this.key = (rhs > g) ? g : rhs;
	}
	
	public void setHeuristic(double val) {
		this.heuristic = val;
	}
	
	public double getRHS() {
		return this.rhs;
	}
	
	public double getG() {
		return this.g;
	}
	
	public double getHeuristic() {
		return this.heuristic;
	}

}
