
//Name:Diang Li
//USC loginid:diangli
//CS 455 PA3
//Fall 2017

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JComponent;

/**
MazeComponent class

A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent{

    private static final int START_X = 10; // top left of corner of maze in frame
    private static final int START_Y = 10;
    private static final int BOX_WIDTH = 20;  // width and height of one maze "location"
    private static final int BOX_HEIGHT = 20;
    private static final int INSET = 2;  // how much smaller on each side to make entry/exit inner box
    private Maze mazeNow;


/**
   Constructs the component.
   @param maze   the maze to display
*/
	public MazeComponent(Maze maze) {  
	    mazeNow = maze;
	}


	/**
  	   Draws the current state of maze including the path through it if one has
  	   been found.
  	   @param g the graphics context
	 */
	public void paintComponent(Graphics g){	
	    drawFrame(g);
	    drawMaze(g);
	    drawRoute(g);
	}
	
	/**
	   Draws the maze panel in black

	   @param g the graphics context
	*/
	private void drawFrame(Graphics g){
	    Graphics2D g2 = (Graphics2D) g; 
	    g2.setColor(Color.black);
	    //draw the background frame in black color
	    Rectangle border = new Rectangle(START_X,START_Y,BOX_WIDTH*mazeNow.numCols()+INSET*2,BOX_HEIGHT*mazeNow.numRows()+INSET*2);
	    g2.draw(border);
	    g2.fill(border);
	}
	
	/**
	   Draws the maze in relative colors

	   @param g the graphics context
	 */
	private void drawMaze(Graphics g){
	    Graphics2D g2 = (Graphics2D) g; 
	    for(int i=0;i<mazeNow.numCols();i++){
		for(int j=0;j<mazeNow.numRows();j++){
		    MazeCoord loc = new MazeCoord(j,i); 
		    if(mazeNow.hasWallAt(loc)){	//draw the wall in black color
			g2.setColor(Color.black);
			Rectangle content = new Rectangle(i*BOX_WIDTH+START_X+INSET,j*BOX_HEIGHT+START_Y+INSET,BOX_WIDTH,BOX_HEIGHT);
			g2.draw(content);
			g2.fill(content);
		    }
		    else{  	    //draw the free location in white
			g2.setColor(Color.white);
			Rectangle content = new Rectangle(i*BOX_WIDTH+START_X+INSET,j*BOX_HEIGHT+START_Y+INSET,BOX_WIDTH,BOX_HEIGHT);
			g2.draw(content);
			g2.fill(content);
		    }
		    if(loc.equals(mazeNow.getExitLoc())){  //draw the exit location
		        g2.setColor(Color.green);
			Rectangle exit = new Rectangle(i*BOX_WIDTH+START_X+INSET*2,j*BOX_HEIGHT+START_Y+INSET*2,BOX_WIDTH-INSET*2,BOX_HEIGHT-INSET*2);
			g2.draw(exit);
		        g2.fill(exit);
		    }
		    else if(loc.equals(mazeNow.getEntryLoc())){  //draw the entry location
			g2.setColor(Color.yellow);
			Rectangle entry = new Rectangle(i*BOX_WIDTH+START_X+INSET+1,j*BOX_HEIGHT+START_Y+INSET+1,BOX_WIDTH-INSET*2,BOX_HEIGHT-INSET*2);
			g2.draw(entry);
			g2.fill(entry);
		    }			
		}
	    }
	}
	
	/**
	   Draws the route of maze

	   @param g the graphics context
	*/
	private void drawRoute(Graphics g){
	    Graphics2D g2 = (Graphics2D) g;
	    int[] from = new int[mazeNow.numRows()*mazeNow.numCols()+1];
	    int[] to = new int[mazeNow.numRows()*mazeNow.numCols()+1];
	    int m=0,n=0;
	    LinkedList<MazeCoord> list = new LinkedList<>();
	    list=mazeNow.getPath();
	    ListIterator<MazeCoord> iter = list.listIterator();
	    int pointFromRow =0,pointFromCol=0;
	    while(iter.hasNext()){	//get the row and column of each route point and shift them to coordinates of line 
		MazeCoord point = iter.next();
		pointFromRow = point.getRow()*BOX_HEIGHT+BOX_HEIGHT+INSET;
		from[m++]=pointFromRow;
		pointFromCol = point.getCol()*BOX_WIDTH+BOX_WIDTH+INSET;
		to[n++]=pointFromCol;
	    }
	    //draw the route in blue
	    g2.setColor(Color.blue);
	    for(int a=0;a<m-1;a++){
		Line2D.Double route = new Line2D.Double(to[a], from[a], to[a+1],from[a+1]); 
		g2.draw(route); 
	    }
	}
}



