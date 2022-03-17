package coderxz.uestc.dstarlite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GridProblem extends Problem {
	
	private Cell[][] cellGrid;
	private boolean endpointsChanged;
	
	public static final char WALL = '*';
	public static final char TRAFFIC = '-';
	
	public GridProblem(GridProblem problem) {
		this.cellGrid = problem.getGrid();
		this.changes = new LinkedList<State>();
		this.endpointsChanged = false; 
	}
	
	public GridProblem(String[] contents) {
		this.cellGrid = new Cell[contents.length][contents[0].length()];
		for(int i = 0; i < contents.length; i++) {
			for(int j = 0; j < contents[i].length(); j++) {
				char content = contents[i].charAt(j);
				cellGrid[i][j] = new Cell(i, j);
				if(content == WALL) {
					cellGrid[i][j].setWall(true);
				} else if(content == TRAFFIC) {
					cellGrid[i][j].setWeight(5);
				}
			}
		}
		this.changes = new LinkedList<State>();
		this.endpointsChanged = false;
	}
	
	public GridProblem(int length, int height) {
		this.cellGrid = new Cell[length][height];
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < height; j++) {
				cellGrid[i][j] = new Cell(i, j);
			}
		}
		this.changes = new LinkedList<State>();
		this.endpointsChanged = false;
	}
	
	public void setStart(int x, int y) {
		this.start = cellGrid[x][y];
		this.endpointsChanged = true;
	}
	
	public void setGoal(int x, int y) {
		this.goal = cellGrid[x][y];
		this.endpointsChanged = true;
	}
	
	public void setWall(int x, int y) {
		cellGrid[x][y].setWall(true);
		changes.add(cellGrid[x][y]);
	}

	public void setWallAndChange(int x, int y) {
		cellGrid[x][y].setWall(true);
		changes.add(cellGrid[x][y]);
		this.endpointsChanged = true;
	}
	
	public void removeWall(int x, int y) {
		cellGrid[x][y].setWall(false);
		changes.add(cellGrid[x][y]);
	}
	
	public void setWeight(int x, int y, double val) {
		cellGrid[x][y].setWeight(val);
		changes.add(cellGrid[x][y]);
	}

	/** Returns a list of the successor states for the state that is passed in.
	 * 
	 * @param state The state for which you want the successors.
	 */
	public List<State> getSuccessors(State state) {
		ArrayList<State> neighbors = null;
		if(state instanceof Cell) {
			neighbors = new ArrayList<State>();
			Cell c = (Cell) state;
			/*if(c.getX() < cellGrid.length - 1) {
				neighbors.add(cellGrid[c.getX()+1][c.getY()]);
			}
			if(c.getX() > 0) {
				neighbors.add(cellGrid[c.getX()-1][c.getY()]);
			}
			if(c.getY() < cellGrid[0].length - 1) {
				neighbors.add(cellGrid[c.getX()][c.getY()+1]);
			}
			if(c.getY() > 0) {
				neighbors.add(cellGrid[c.getX()][c.getY()-1]);
			}*/
			
			int lowerX = Math.max(c.getX() - 1, 0);
			int upperX = Math.min(c.getX() + 2, cellGrid.length);
			int lowerY = Math.max(c.getY() - 1, 0);
			int upperY = Math.min(c.getY() + 2, cellGrid[0].length);
			
			for(int i = lowerX; i < upperX; i++) {
				for(int j = lowerY; j < upperY; j++) {
					neighbors.add(cellGrid[i][j]);
				}
			}
			neighbors.remove(c);
		}
		return neighbors;
	}

	@Override
	public List<State> getPredecessors(State state) {
		return getSuccessors(state);
	}
	
	public boolean isWall(int x, int y) {
		return isValid(x, y) &&	cellGrid[x][y].isWall();
	}
	
	public boolean isValid(int x, int y) {
		return x < cellGrid.length && x > 0 &&
				y < cellGrid[0].length && y > 0;
	}
	
	public Cell get(int x, int y) {
		return cellGrid[x][y];
	}

	/** In this problem we use a modified Manhattan distance as the heuristic,
	 * we need to modify it since we allow diagonal movement along the path.
	 * 
	 * @param state The state which you want the heuristic for.
	 */
	public double getHeuristic(State state) {
		double heuristic = 0;
		if(state instanceof Cell) {
			Cell c = (Cell) state;
			heuristic += Math.max(Math.abs(((Cell)start).getX() - c.getX()),
					Math.abs(((Cell)start).getY() - c.getY()));
		}
		state.setHeuristic(heuristic);
		return heuristic;
	}
	
	/** This returns the heuristic from going from the state to the goal.
	 * 
	 * @param state The state that you want the heuristic for.
	 * @return
	 */
	public double getEstimate(State state) {
		int estimate = 0;
		if(state instanceof Cell) {
			Cell c = (Cell) state;
			estimate += Math.max(Math.abs(((Cell)goal).getX() - c.getX()),
					Math.abs(((Cell)goal).getY() - c.getY()));
		}
		return estimate;
	}

	/** This method returns the cost to go from the beginning state to the 
	 * ending state.
	 * 
	 * @param init The start state.
	 * @param end The ending state.
	 */
	public double getCost(State init, State end) {
		double cost = Double.POSITIVE_INFINITY;
		if(init instanceof Cell && end instanceof Cell) {
			Cell c1 = (Cell) init;
			Cell c2 = (Cell) end;
			if(c1.isWall() || c2.isWall()) {
				return Double.POSITIVE_INFINITY;
			}
			cost = 1.0 * c1.getWeight() * c2.getWeight();
		}
		return cost;
	}

	@Override
	public void intialize() {
		for(int i = 0; i < cellGrid.length; i++) {
			for(Cell c : cellGrid[i]) {
				c.setG(Double.POSITIVE_INFINITY);
				c.setRHS(Double.POSITIVE_INFINITY);
				c.setHeuristic(this.getHeuristic(c));
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Cell current = null;
		
		for(int i = 0; i < cellGrid.length; i++) {
			for(int j = 0; j < cellGrid[0].length; j++) {
				current = cellGrid[i][j];
				double val = (current.getHeuristic() + current.getG());
				if(val == Double.POSITIVE_INFINITY) {
					sb.append("\tinf\t");
				} else {
					sb.append("\t" + ((int)val) + "\t");
				}
			}
			sb.append(System.getProperty("line.separator"));
		}
		
		return sb.toString();
	}

	public Cell[][] getGrid() {
		Cell[][] ret = new Cell[cellGrid.length][cellGrid[0].length];
		for (int i = 0; i < ret.length; i++) {
			for(int j = 0; j < ret[0].length; j++) {
				ret[i][j] = new Cell(cellGrid[i][j]);
			}
		}
		return ret;
	}
	
	public String stringG() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < cellGrid.length; i++) {
			for(int j = 0; j < cellGrid[0].length; j++) {
				double val = cellGrid[i][j].getG();
				if(val == Double.POSITIVE_INFINITY) {
					sb.append("\tinf\t");
				} else {
					sb.append("\t" + ((int)val) + "\t");
				}
			}
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

	@Override
	public boolean endpointsChanged() {
		return this.endpointsChanged;
	}
	
	public void clearFlags() {
		this.endpointsChanged = false;
	}
	
	public String stringWall() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < cellGrid.length; i++) {
			for(int j = 0; j < cellGrid[0].length; j++) {
				boolean val = cellGrid[i][j].isWall();
				if(val) {
					sb.append(" * ");
				} else {
					sb.append(" _ ");
				}
			}
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

	@Override
	public String stringRHS() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < cellGrid.length; i++) {
			for(int j = 0; j < cellGrid[0].length; j++) {
				double val = cellGrid[i][j].getRHS();
				if(val == Double.POSITIVE_INFINITY) {
					sb.append("\tinf\t");
				} else {
					sb.append("\t" + ((int)val) + "\t");
				}
			}
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
}
