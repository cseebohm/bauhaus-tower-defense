/**
 * this class defines the path 
 * 
 * @author  Clarissa Seebohm
 * @version November 14, 2022
 */

package path;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

public class Path {
	
	private int n;
	private ArrayList<Point> points = new ArrayList<Point>();
	
	/**
	 * This constructor takes a Scanner as a parameter. It assumes the Scanner is already open and scanning a path file, 
	 * and scans in the count and the points to build a path.  (This loads the path into the object.)  
	 * 
	 *
	 * @param Scanner 
	 */
	public Path(Scanner scan)
	{	
		Point temp;
		
		//count
		this.n = scan.nextInt();
						
	    //loop for points
		for(int i = 0; i < n; i++) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			
			temp = new Point(x,y);
			
			this.points.add(temp);
		}

	}
	
	/**
	 * This constructor creates an empty path
	 * 
	 */
	public Path()
	{	
		this.n = 0;
	}
	
	/**
	 * An "int getPointCount()" function that returns the number of points in the path.
	 * 
	 *
	 * @return int
	 */
	public int getPointCount()
	{
		return this.n;
	}
	
	/**
	 * An "int getX(int n)" function that returns the nth x coordinate in the path, with n starting at 0 for the first x coordinate.  (This makes it easy to use ArrayLists.)
	 *
	 * @param int
	 * @return int
	 */
	public int getX(int n)
	{
		int x = (int) this.points.get(n).getX();
		
		return x;
	}
	
	/**
	 * An "int getY(int n)" function that returns the nth y coordinate in the path, with n starting at 0 for the first y coordinate.
	 *
	 * @param int
	 * @return int
	 */
	public int getY(int n)
	{
		int y = (int) this.points.get(n).getY();
		
		return y;
	}
	
	/**
	 * A "void add(int x, int y) function that adds the specified (x, y) coordinate to the end of the path.
	 *
	 * @param int x
	 * @param int y 
	 */
	public void add(int x, int y)
	{
		Point newPoint = new Point (x,y);
		
		this.points.add(newPoint);
		this.n++;

	}

	/**
	 * removes point at i
	 * 
	 *
	 * @return int
	 */
	public void remove(int i)
	{
		this.n = n-1;
		points.remove(i);
	}
	
	
	/**
	 * A "toString() function" that returns a String representation of the path in the file format above.  
	 * The String should have a count, then the x and y coordinates.  
	 * 
	 * @return String
	 */
	public String toString()
	{
		String pointString = (int) points.get(0).getX() + " " + (int) points.get(0).getY();
		
		for(int i = 1; i < n; i++) {
			pointString = pointString + "\n" + (int) points.get(i).getX() + " " + (int) points.get(i).getY();
		}
		
		String pathString = this.n + "\n" + pointString;
		
		return pathString;

	}
	
	/**
	 * "void draw (Graphics g)" that draws the path to the graphics object (using line segments). 
	 * 
	 * @param Graphics
	 */
	
	public void draw (Graphics g)
	{
		int x1, y1, x2, y2;
			
		for(int i = 0; i < n-1; i++) {
			if(this.n < 2) {
				break;
			}
			
			x1 = (int) points.get(i).getX();
			y1 = (int) points.get(i).getY();
			
			x2 = (int) points.get(i+1).getX();
			y2 = (int) points.get(i+1).getY();
			
			g.setColor(Color.white);
			g.drawLine(x1, y1, x2, y2);
			
		}
	}
		
	/** 
	 * Given a percentage between 0% and 100%, this method calculates
	 * the location along the path that is exactly this percentage
	 * along the path. The location is returned in a Point object
	 * (integer x and y), and the location is a screen coordinate.
	 * 
	 * If the percentage is less than 0%, the starting position is
	 * returned. If the percentage is greater than 100%, the final
	 * position is returned.
	 * 
	 * Callers must not change the x or y coordinates of any returned
	 * point object (or the caller could be changing the path).
	 * 
	 * @param percentTraveled a distance along the path
	 * @return the screen coordinate of this position along the path
	 */
	 public Point convertToCoordinates(double percentTraveled)
	 {
		//initialize local variables
		Point location = new Point();
 		double length = 0;
 		double [] segmentLength = new double[n];
 		 
 		//loop through segments to find length
		for(int i=0; i<n-1; i++) {
			int x = this.getX(i+1) - this.getX(i);
			int y = this.getY(i+1) - this.getY(i);
						
			segmentLength[i] = pythagoras(x,y);
			length += segmentLength[i];
		}

 		
		double currentLoc = percentTraveled * length;
 		double distanceCovered = 0;
 		double segPercent = 0;
 		int currentI = 0;
 				
 		for(int i=0; i<n-1; i++) {
 			distanceCovered += segmentLength[i];
 					
 			if(currentLoc < distanceCovered) {
 				segPercent = 1 - ((distanceCovered - currentLoc) / segmentLength[i]);
 				currentI = i;
 				break;
 			}
 		}
 		
 		//now currentI is segment # & segmentLength[i] is segmentLength
 		location.x = (int) (segPercent * (int)this.getX(currentI+1) + (1-segPercent) * (int)this.getX(currentI));
 		location.y = (int) (segPercent * (int)this.getY(currentI+1) + (1-segPercent) * (int)this.getY(currentI));
 		
 		return location;
	 	}
	
	/**
	 * gets the length of the path in pixels
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public double getLength()
	{
		double length = 0;
		double [] segmentLength = new double[n];

	 		//loop through segments to find length
			 for(int i=0; i<n-1; i++) {
				int x = this.getX(i+1) - this.getX(i);
				int y = this.getY(i+1) - this.getY(i);
				
				segmentLength[i] = pythagoras(x,y);
				length += segmentLength[i];
			}
		return length;
	}
	/** 
	 * performs the pythagorean theorem on a given x and y lengths
	 * 
	 * @param int x, y
	 * @return double h 
	 */
	private double pythagoras (int x, int y)
		{
			double xD = (double) x;
			double yD = (double) y;
			
			double h = Math.sqrt(xD*xD + yD*yD);
			
			return h;
		}
}
