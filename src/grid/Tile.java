package grid;


public abstract class Tile {

	private int x, y, type;
	// x-coordinate
	//y-coordinate
	//type is the attribute that allows us to differentiate between path tiles and other tiles

	public Tile(int x, int y){ //constructor to initialise the tile
		this.x = x;
		this.y = y;
	}

	public void setX(int x){//method to set the x-coordinate of the tile
		this.x = x;
	}


	public void setY(int y){//method to set the y-coordinate of the tile
		this.y = y;
	}

	public void setType(int type){ //method to set the type of the tile
		this.type = type;
	}

	public int getX(){ //method to access the x-coordinate of the tile
		return x;
	}

	public int getY(){ //method to access the y-coordinate of the tile
		return y;
	}

	public int getType(){ //method to set the type of the tile we are interacting with
		return type;
	}



	//Lorsqu'on définit une classe, il peut être très utile de redéfinir la méthode toString afin de donner une
	// description satisfaisante des objets de cette classe. Beaucoup de classes de l'API redéfinissent la méthode toString.
	//We redefine the toString method so that when call it it will return the string representation of the int of
	// the type of a tile
	public String toString(){
		return String.valueOf(getType());
	}
}
