package grid;


// PathTiles are the tiles that make up the path on the map

public class PathTile extends Tile{

	private PathTile nextTile;

	public PathTile(int x, int y) { //constructor to initialise a new PathTile on the map
		super(x, y); //we refer to the abstract tile class to use coordinates

		super.setType(1); //we associate an "int" to the type of tile

		nextTile = null;
	}

	/**
	 * Make a link between the current PathTile and the next one
	 *
	 * @param nextTile		The next PathTile according to the current one
	 */
	public void setNextTile(PathTile nextTile) { //method to link the current PathTile and the next one
		this.nextTile = nextTile;
	}

	/**
	 * Lead the pointer to the next PathTile
	 *
	 * @return the next linked PathTile
	 */
	public PathTile getNextTile() { //method to access the nextTile on the path
		return nextTile;
	}
}
