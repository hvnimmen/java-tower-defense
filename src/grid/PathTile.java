package grid;


// PathTiles are the tiles that make up the path on the map

public class PathTile extends Tile{

	private PathTile nextTile;

	public PathTile(int x, int y) { //constructor to initialise a new PathTile on the map
		super(x, y); //we refer to the abstract tile class to use coordinates

		super.setType(1); //we associate an "int" to the type of tile

		nextTile = null; //we initialize the next but null is a "placeholder" value that
		// means that the object reference in question doesn't actually have a value.
	}

	public void setNextTile(PathTile nextTile) { //method to link the current PathTile and the next one by being able to set
		//the next tile
		this.nextTile = nextTile;
	}

	public PathTile getNextTile() { //method to access the nextTile on the path
		return nextTile;
	}
}
