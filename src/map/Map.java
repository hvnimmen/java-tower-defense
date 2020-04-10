package map;

//this class is made to design the map on which the game happens
import grid.*; // To import the whole package

import java.util.ArrayList;
import java.util.LinkedList; //???????
import java.util.Queue;


public class Map {

	private int widthOfMap, heightOfMap;
	//widthOfMap is the horizontal number of MapTiles (x-axis)
	//heightOfMap is the vertical number of MapTiles (y-axis)
	private Tile[][] mapTile; //the type is [][] because we need a double array of tiles
	// a map tile is a tile with a position
	private String inputCorner;//??????????
	private PathTile entry;//is the name of the tile that is the entry point
	private int[][] mapArray;//??????????
	private int[][] cornerArray;//??????????
	
	private static final int CELL_SIZE = 32; //this is to determine the make the dimension of the map

	private boolean validityOfMap, validityOfPath; //???????

	public Map(){ //constructor to initialize the attributes of the map
		super(); //?????????uses the constructor of which mother class

		this.widthOfMap = 0; // to initialize the attributes
		this.heightOfMap = 0;
		this.inputCorner = "";
		
		validityOfMap = true;
	}

	public void setMapSize(int widthOfMap, int heightOfMap){ //method to set the size of the map with the attributes
		//of width and height previously initialized
		this.widthOfMap = widthOfMap;
		this.heightOfMap = heightOfMap; //to refer to the attributes
	}

	public void setInputCorner(String inputCorner){ // to set Several Points on the path including entry and exit
		this.inputCorner = inputCorner;
	}

	public void initializeMap(){ //method to build the map and work with the attributes previously initialized and set
		//this method will initialize the map and set it as default type
		if (widthOfMap > 0 && heightOfMap > 0){ //basic condition for a tile to be correctly located on the map
			mapTile = new Tile[widthOfMap][heightOfMap]; //we initialize the mapTile attribute by creating a new tyle with
			//a position (widthOfMap,heightOfMap)

			for (int i = 0 ; i < widthOfMap; i++){ //for every unit of width //i is incremented each time it enter the loop
				for (int j = 0; j < heightOfMap; j++){ //and for every unit of height //j is incremented each time it enter the loop
					mapTile[i][j] = new MapTile(i,j); // we create a new tile with the default type Maptile
					// we begin on the first row, first line and then go "down"
				}
			}		//What happens to the first maptile created????????is it replaced
		}
		validityOfMap = true; //this creates a valid map otherwise there is an error
	}

	public int getWidthOfMap(){ //method to get the width of the map
		return widthOfMap;
	}

	public int getWidthInPixel(){ //method to get the width of the map in pixels
		return widthOfMap * CELL_SIZE;
	}

	public int getHeightOfMap(){ //method to get the height of the map
		return heightOfMap;
	}
	
	public int getHeightInPixel(){ //method to get the height of the map in pixels
		return heightOfMap * CELL_SIZE;
	}

	public String getInputCorner(){ //method to get the user's input of path //?????????? what is input corner
		return inputCorner; //input corner is the user's input of path
	}

	public Tile getTile(int x, int y){
		//method to retrieve  and handle a MapTile because it may cause an exception
		try {
			return this.mapTile[x][y]; //it returns the content of a tile ? is the type??????????????????
		} catch(Exception e){// This is a generic Exception handler which means it can handle
		 //all the exceptions. This will execute if the exception is not
		 //handled by previous catch blocks.


		}
		return null;
	}

	public int getPixelSize(){ //method to get the size in pixel of a cell
		return CELL_SIZE;
	}


	public void placeEntry(int x, int y){ // method to place Entry Point on Map and change the tile's type to Entry
		mapTile[x][y] = new PathTile(x, y); //the entry point that is chosen is created and is not a Maptile but a pathtile
		//because it is a part of it
		mapTile[x][y].setType(2); //we set a new type for this tile because it is not only a path tile but the entry point
		entry = (PathTile) mapTile[x][y]; //we name this tile "entry"
	}

	public PathTile getEntry(){ //method to get the entry point
		return (PathTile) this.getTile(entry.getX(), entry.getY());
	}

	public void placeExit(int x, int y){//method to place the Exit point on the Map and change the tile's type to Exit
		mapTile[x][y] = new PathTile(x, y); //same as entry point
		mapTile[x][y].setType(3);
	}

	public void placePathPoint(int x, int y){ //method to place a path point on the map
		if (mapTile[x][y] instanceof MapTile){ //the point of the path must exist on the map and therefore be an a MapTile
			mapTile[x][y] = new PathTile(x, y); //the maptile becomes a PathTile
		}
	}


	//???????????????????????????
	public String arrangePathPoint(ArrayList<Integer> pathPoints){
		String temp = "";
		for (int i = 0 ; i < pathPoints.size(); i+=2){
			temp += "(" + pathPoints.get(i) + "," + pathPoints.get(i + 1) + ") ";
		}

		inputCorner = temp.substring(0);
		
		return inputCorner;
	}

	/**
	 * Read multiple xy-coordinates from a string which is required at least two sets
	 * Split the coordinates and insert them into a queue
	 * 
	 * @param s		a set of PathTile inputs as a string
	 * @return all path coordinates in a Queue
	 */
	public Queue<PathTile> multipleCoordinatesSplit(String s){
		inputCorner = s.substring(0);
		if (inputCorner.isEmpty()){
			return null;
		}

		Queue<PathTile> path = new LinkedList<PathTile>();
		String[] arr = s.split("\\)\\s*"); 
		for (String anArr : arr){
			String x = anArr.substring(1,anArr.indexOf(",")).trim();
			String y = anArr.substring(anArr.indexOf(",") + 1, anArr.length()).trim();

			PathTile p = new PathTile(Integer.valueOf(x), Integer.valueOf(y));
			path.add(p);
		}
		return path;
	}
	
	/**
	 * Convert the corners on the path into 2D array including entry and exit
	 * 
	 * @param path
	 * @return cornerArray
	 */
	public int[][] cornerArray(Queue<PathTile> path){
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		while (!path.isEmpty()){
			PathTile current = path.poll();
			array.add(current.getX());
			array.add(current.getY());
		}
		
		int arraySize = array.size() / 2;
		int count = 0;
		
		cornerArray = new int[arraySize][2];
		for (int i = 0; i < arraySize; i ++){
			if (count == array.size()){
				break;
			}

			cornerArray[i][0] = array.get(count) * getPixelSize() + getPixelSize() / 2;
			cornerArray[i][1] = array.get(count + 1) * getPixelSize() + getPixelSize() / 2;

			if (count == 0){
				if (array.get(count) == 0){
					cornerArray[0][0] = array.get(count) * getPixelSize();
				}
				if (array.get(count) == getWidthOfMap() - 1){
					cornerArray[0][0] = (array.get(count) + 1) * getPixelSize();
				}
				if (array.get(count + 1) == 0){
					cornerArray[0][1] = array.get(count + 1) * getPixelSize() + getPixelSize() / 2;
				}
			}
			count+= 2;
		}

		return cornerArray;
	}
	
	/**
	 * 
	 * @return  cornerArray
	 */
	public int[][] getCornersList(){
		return cornerArray;
	}

	/**
	 * Link all the path tile together
	 * First input is an Entry point
	 * Last input is an Exit point
	 * 
	 * @param newPath		a Queue contains all the path locations
	 */
	public void buildPath(Queue <PathTile> newPath){
		if (newPath == null){
			return;
		}

		//Store the first input coordinate values
		entry = newPath.peek();
		PathTile current, next;

		if (newPath.isEmpty()){
			return;
		}

		if (newPath.size() >= 2){
		}		
				
		current = newPath.remove();
		next = current;

		if (current.getX() < getWidthOfMap() && current.getY() < getHeightOfMap()){
			mapTile[next.getX()][next.getY()] = new PathTile(next.getX(), next.getY());

			while (!newPath.isEmpty()){
				next = newPath.remove();
				linkTwoPoints(current, next);
				current = next;
			}

			//Indicates the Entry and Exit of the path
			placeEntry(entry.getX(), entry.getY());
			placeExit(current.getX(), current.getY());

		}
	}

	/**
	 * Link the path from a specific PathTile to another one
	 * Two points must have to share the same x position or y position
	 * If two points have the same x-coordinate, then create a vertical line to connect them together
	 * Otherwise, create a horizontal line
	 * 
	 * @param startPoint	The first point (From this PathTile)
	 * @param endPoint		The second point (To this PathTile)
	 */
	public void linkTwoPoints(PathTile startPoint, PathTile endPoint){
		int dx, dy;

		int x1 = startPoint.getX();
		int y1 = startPoint.getY();

		int x2 = endPoint.getX();
		int y2 = endPoint.getY();

		if (x1 != x2 && y1 != y2){
			placePathPoint(x1, y1);
			placePathPoint(x2, y2);

			if (x1 == 0 || x1 == getWidthOfMap() - 1 || y1 == 0 || y1 == getHeightOfMap() - 1){
				validityOfPath = true;
			}
			if (x2 == 0 || x2 == getWidthOfMap() - 1 || y2 == 0 || y2 == getHeightOfMap() - 1){
				validityOfPath = true;
			}
		}
		else {

			PathTile currentPoint = (PathTile) mapTile[x1][y1];

			//If two inputs have the same x-axis, calculate the different of y and create a vertical line to connect two points
			if (x1 == x2){
				dy = y2 - y1;

				if (dy == 0){
					return;
				}
				//If the difference is positive, link them from bottom to top
				//Otherwise, link them from top to bottom
				else if (dy > 0){
					for (int i = 1 ; i <= dy; i++){
						mapTile[x1][y1 + i] = new PathTile(x1, y1 + i);
						currentPoint.setNextTile((PathTile)mapTile[x1][y1 + i]);
						currentPoint = (PathTile)mapTile[x1][y1 + i];
					}
				}
				else {
					for (int i = -1 ; i >= dy; i--){
						mapTile[x1][y1 + i] = new PathTile(x1, y1 + i);
						currentPoint.setNextTile((PathTile)mapTile[x1][y1 + i]);
						currentPoint = (PathTile)mapTile[x1][y1 + i];
					}
				}
				validityOfPath = true;
			}
			//If two inputs have the same y-axis, calculate the different of x and create a horizontal line to connect two points
			else if (y1 == y2){
				dx = x2 - x1;

				if (dx == 0){
					return;
				}
				//If the difference is positive, link them from left to right
				//Otherwise, link them from right to left
				else if (dx > 0){
					for (int i = 1 ; i <= dx; i++){
						mapTile[x1 + i][y1] = new PathTile(x1 + i, y1);
						currentPoint.setNextTile((PathTile)mapTile[x1 + i][y1]);
						currentPoint = (PathTile)mapTile[x1 + i][y1];
					}
				}
				else {
					for (int i = -1 ; i >= dx; i--){
						mapTile[x1 + i][y1] = new PathTile(x1 + i, y1);
						currentPoint.setNextTile((PathTile)mapTile[x1 + i][y1]);
						currentPoint = (PathTile)mapTile[x1 + i][y1];
					}
				}
				validityOfPath = true;
			}
		}
	}
	
	/**
	 * Convert the object Map to 2D integer array version
	 * 
	 * @param map
	 * @return
	 */
	public int[][] convertToBinaryMap(Map map){
		mapArray = new int[map.getHeightOfMap()][map.getWidthOfMap()];
		
		for (int j = 0; j < map.getHeightOfMap(); j++){
			for (int i = 0; i < map.getWidthOfMap(); i++){
				if (map.getTile(i, j) instanceof PathTile){
					if (map.getTile(i, j).getType() == 2){
						mapArray[j][i] = 2;
					}
					else if (map.getTile(i, j).getType() == 3){
						mapArray[j][i] = 3;
					}
					else{
						mapArray[j][i] = 1;
					}
				}
				else {
					mapArray[j][i] = 0;
				}
			}
		}
		return mapArray;
	}
	
	/**
	 *  Turn the binary map into an Object Map
	 *  
	 * @param mapArray	2D integer array version of Map
	 * @return	map
	 */
	public Map convertFromBinaryMap(int[][] mapArray){
		this.setMapSize(mapArray[0].length, mapArray.length);
		this.initializeMap();
		
		for (int i = 0; i < mapArray.length; i++){
			for (int j = 0; j < mapArray[i].length; j++){
				if (mapArray[i][j] == 1){
					this.getTile(j, i).setType(1);
				}
				else if (mapArray[i][j] == 2){
					this.getTile(j, i).setType(2);
				}
				else if (mapArray[i][j] == 3){
					this.getTile(j, i).setType(3);
				}
				else{
					this.getTile(j, i).setType(0);
				}
			}
		}
		return this;
	}
	
	/**
	 * Verify whether the design map is valid according to the game rules
	 * 
	 * @return validity
	 */
	public boolean ValidityOfMap(){
		boolean validity;
		if (validityOfMap || validityOfPath){
			validity = true;
		}
		else {
			validity = false;
		}
		return validity;
	}
	
	/**
	 * Print the Map
	 */
	public String toString(){

		String s = "\n";
		for (int j = 0; j < getHeightOfMap(); j++){
			for (int i = 0; i < getWidthOfMap(); i++){
				s+=(getTile(i,j) + " ");
			}
			s +="\n";
		}
		return s;
	}
}
