package kangaroo.simulation.world;

public class Path {
	private Point nextPoint;
	private Path nextPath;
	private Integer height;
	
	public Path() {
		nextPoint = null;
		nextPath = null;
		height = null;
	}
	
	public Path(Point nextPoint, Path nextPath, Integer height) {
		this.nextPoint = nextPoint;
		this.nextPath = nextPath;
		this.height = height;
	}
	
	public Point getNextPoint() {return nextPoint;}
	public Path getNextPath() {return this.nextPath;}
	public Integer getHeight() {return this.height;}
	
	public void setNextPoint(Point nextPoint) {this.nextPoint = nextPoint;}
	public void setNextPath(Path nextPath) {this.nextPath = nextPath;}
	public void setHeight(Integer height) {this.height = height;}
	
        @Override
	public String toString() {
		return " -> " + nextPoint.getID() + " : " + height;
	}
}
