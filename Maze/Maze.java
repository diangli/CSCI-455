
//Name:Diang Li
//USC loginid:9310767693
//CS 455 PA3
//Fall 2017

import java.util.LinkedList;


/**
Maze class

Stores information about a maze and can find a path through the maze
(if there is one).

Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
(parameters to constructor), and the path:
  -- no outer walls given in mazeData -- search assumes there is a virtual 
     border around the maze (i.e., the maze path can't go outside of the maze
     boundaries)
  -- start location for a path is maze coordinate startLoc
  -- exit location is maze coordinate exitLoc
  -- mazeData input is a 2D array of booleans, where true means there is a wall
     at that location, and false means there isn't (see public FREE / WALL 
     constants below) 
  -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
  -- only travel in 4 compass directions (no diagonal paths)
  -- can't travel through walls

*/

public class Maze {
    public static final boolean FREE = false;
    public static final boolean WALL = true;
    private boolean[][] maze;
    private boolean[][] visited;
    private LinkedList<MazeCoord> route;
    private MazeCoord startPoint;
    private MazeCoord exitPoint;
/**
   Constructs a maze.
   @param mazeData the maze to search.  See general Maze comments above for what
   goes in this array.
   @param startLoc the location in maze to start the search (not necessarily on an edge)
   @param exitLoc the "exit" location of the maze (not necessarily on an edge)
   PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
      and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

 */
	public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord exitLoc) {
	     maze = mazeData;
	     startPoint = startLoc; 
	     exitPoint = exitLoc;
	     route = new LinkedList<MazeCoord>();
	     visited = new boolean[numRows()][numCols()];
	}


/**
   Returns the number of rows in the maze
   @return number of rows
*/
	public int numRows() {
	    if (maze != null){
		return maze.length;
	    }
	    else return 0;   // DUMMY CODE TO GET IT TO COMPILE
	}


/**
   Returns the number of columns in the maze
   @return number of columns
*/   
	public int numCols() {
	    if (maze != null){
		return maze[0].length;
	    }
	    else return 0;   // DUMMY CODE TO GET IT TO COMPILE
	} 


/**
   Returns true iff there is a wall at this location
   @param loc the location in maze coordinates
   @return whether there is a wall here
   PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
*/
	public boolean hasWallAt(MazeCoord loc) {
	    if (loc.getRow() < numRows() && loc.getCol() < numCols()){
		if(maze[loc.getRow()][loc.getCol()]==WALL){
		    return true;
		}
		else{
		    return false;
		}
	    }
	    else return false;
		   // DUMMY CODE TO GET IT TO COMPILE
	}


/**
   Returns the entry location of this maze.
 */
	public MazeCoord getEntryLoc() {
	    int entryRow = startPoint.getRow();
	    int entryCol = startPoint.getCol();
	    return new MazeCoord(entryRow,entryCol);   // DUMMY CODE TO GET IT TO COMPILE
	}


/**
  Returns the exit location of this maze.
*/
	public MazeCoord getExitLoc() {
	    int exitRow = exitPoint.getRow();
	    int exitCol = exitPoint.getCol();
	    return new MazeCoord(exitRow,exitCol);   // DUMMY CODE TO GET IT TO COMPILE
	}


/**
   Returns the path through the maze. First element is start location, and
   last element is exit location.  If there was not path, or if this is called
   before a call to search, returns empty list.
   
   @return the maze path
 */
	public LinkedList<MazeCoord> getPath() {

	    return route;   // DUMMY CODE TO GET IT TO COMPILE

	}


/**
   Find a path from start location to the exit location (see Maze
   constructor parameters, startLoc and exitLoc) if there is one.
   Client can access the path found via getPath method.

   @return whether a path was found.
 */
	public boolean search()  {  
	    route.clear();	//clear the path in maze to prepare for invoking next search(), avoiding exit location being connected with entry location
	    if(maze[startPoint.getRow()][startPoint.getCol()]==WALL||maze[exitPoint.getRow()][exitPoint.getCol()]==WALL){//check if a wall is the exit location or entry location, no path
		return false;
	    }   

	    else {
		 if (dfs(startPoint.getRow(), startPoint.getCol())){
		     visited = new boolean[numRows()][numCols()];   //renew the visited[][] to prepare for invoking next search()
		     route.add(new MazeCoord(startPoint.getRow(),startPoint.getCol()));
		     return true;
		 }
		 else return false;
	    } // DUMMY CODE TO GET IT TO COMPILE
	}

	/**
	 * Depth-First-Search and recursion to find a path from start location 
	   to the exit location. If there is one path, return true.
		
	   @param r  the row of entry location
	   @param c  the column of entry location
	   @return whether a path was found.
	 */
	private boolean dfs(int r, int c){
	    int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
	    if(isLegal(r,c)){
	         visited[r][c]=true;	//mark legal location
	    }
	    else{
		return false;
	    }
	    if((r==exitPoint.getRow())&&(c==exitPoint.getCol())){ //reach the exit location
		route.add(new MazeCoord(r,c));
		return true;
	    }
	    for(int[]d:directions){
		int nr = r+d[0];
		int nc = c+d[1];
					
		if(dfs(nr,nc)){
		    visited[nr][nc]=true;
		    route.add(new MazeCoord(nr,nc));
		    return true;
		}
	        else{
		    continue;
		}
	    }
	    return false;
	}

	/**
	 * Check if a location is out of boundary or visited
		
	   @param x the row of the location
	   @param y the column the location
	   @return whether can continue search from this location.
	 */
	private boolean isLegal(int x, int y){
	    if (x < 0 || y < 0 || x >= numRows() || y >= numCols()||maze[x][y]==WALL){ //check if out of boundary
		return false;
	    }
	    else if (visited[x][y] == true){ //check if this location has been marked 
		return false;
	    }
	    else {
		return true; 
	    }
	}
}
