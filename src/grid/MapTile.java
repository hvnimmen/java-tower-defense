package grid;

// MapTile is the type of tile that makes up the map and that is not part of the path


public class MapTile extends Tile{

	public MapTile(int x, int y) { //constructor to initialise a MapTile
		super(x, y); //we refer to the abstract tile class to use coordinates

		super.setType(0);  //we associate an "int" to the type of tile
	}
}
