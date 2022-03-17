package coderxz.uestc.dstarlite;

public class Cell extends State {
	
	private int x, y;
	/* An obstacle weight of 1 means that the cell does not resist movement,
	 * and larger obstacle weights mean that the cell resists more movement.
	 */
	private double obstacleWeight;
	
	private boolean isWall;
	
	public int Comparable(State other) {
		int ret = super.Comparable(other);
		if(this.isWall()) {
			return 1;
		} else {
			return ret;
		}
	}
	
	public Cell(int x, int y) {
		this(x, y, 1);
	}
	
	public Cell(int x, int y, double weight) {
		this.x = x;
		this.y = y;
		this.obstacleWeight = weight;
		this.isWall = false;
	}
	
	public Cell(Cell cell) {
		super(cell);
		this.x = cell.x;
		this.y = cell.y;
		this.obstacleWeight = cell.obstacleWeight;
		this.isWall = cell.isWall;
	}
	
	@Override
	public double getG() {
		if(this.isWall) {
			return Double.POSITIVE_INFINITY;
		} else {
			return super.getG();
		}
	}
	
	@Override
	public void setG(double val) {
		if(this.isWall()) {
			super.setG(Double.POSITIVE_INFINITY);
		} else {
			super.setG(val);
		}
	}
	
	public void setRHS(double val) {
		if(this.isWall()) {
			super.setRHS(Double.POSITIVE_INFINITY);
		} else {
			super.setRHS(val);
		}
	}
	
	public boolean isWall() {
		return this.isWall;
	}
	
	@Override
	public boolean equals(State other) {
		if(other instanceof Cell) {
			Cell o = (Cell) other;
			return (o.x == this.x) && (o.y == this.y);
		}
		return false;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setWall(boolean val) {
		this.isWall = val;
		if(val) {
			this.setG(Double.POSITIVE_INFINITY);
			this.setRHS(Double.POSITIVE_INFINITY);
		}
	}

	@Override
	public String toString() {
		return "<Cell> x:" + this.x + " y:" + this.y + " cost:" + (this.getKey() + this.getHeuristic());
	}
	
	public void setWeight(double val) {
		this.obstacleWeight = val;
	}
	
	public double getWeight() {
		return this.obstacleWeight;
	}

}
