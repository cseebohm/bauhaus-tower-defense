/*
 * 
 *  
 * 
 * @author Clarissa Seebohm
 * @version 11/6/2022
 */

package path;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PathEditor extends JPanel implements Runnable, MouseListener, ActionListener
{
	static Path currentPath;
	
	/**
     * The application entry point.
     * 
     * @param args unused
     */
    public static void main (String[] args)
    {
        // Main runs in the 'main' execution thread, and the GUI
        //   needs to be built by the GUI execution thread.
        //   Ask the GUI thread to run our 'run' method (at some
        //   later time).
        
        SwingUtilities.invokeLater(new PathEditor());
        
        currentPath = new Path();

        // Done.  Let the main thread of execution finish.  All the
        //   remaining work will be done by the GUI thread.
    }
	
	/**
     * Builds the GUI for this application.  This method must
     * only be called/executed by the GUI thread. 
     */
	public void run() {
        
		// make JFrame
		JFrame f = new JFrame("Path Editor");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // create menu bar, menu, and items 
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu("File");
 
        JMenuItem load = new JMenuItem("Load");
        JMenuItem clear = new JMenuItem("Clear");
        JMenuItem save = new JMenuItem("Save");
 
        // add menu items to menu
        menu.add(load);  
        menu.add(clear);
        menu.add(save);
 
        // listen to menu items
        load.addActionListener(this);
        clear.addActionListener(this);
        save.addActionListener(this);
        
        // add menu to menu bar and menu bar to JFrame
        menuBar.add(menu);
        f.setJMenuBar(menuBar);
        
        // set sizes
        this.setMinimumSize(new Dimension(600, 600));
        this.setPreferredSize(new Dimension(600, 600));
        
        f.setContentPane(this); 
 
        // pack and display JFrame
        f.pack();
        f.setVisible(true);
        
        // listen to mouse
        this.addMouseListener(this);
	}
	
    /**
     * Draws paths
     *
     * @param g: the Graphics object to draw to
     */
    public void paint (Graphics g)
    {
        // background image
        BufferedImage backdrop;
        
        try 
        {
            backdrop = javax.imageio.ImageIO.read(new File("bauhausBackground.png"));
            g.drawImage(backdrop, 0, 0, null);
            
        } 
        
        catch (IOException e) 
        {
            System.out.println("Error drawing background image");
        }   
        
        currentPath.draw(g);
    }

	public void mouseReleased(MouseEvent e) {	
		// get the location of the mouse click within this window.
    	int x = e.getX (); 
    	int y = e.getY ();
    	
    	// add point to path and draw
    	currentPath.add(x, y);
    	repaint ();
    	
	}

	public void mouseClicked(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }

	public void actionPerformed(ActionEvent e) { 
		System.out.println("Selected: " + e.getActionCommand());
		
		if(e.getActionCommand() == "Load") {
			System.out.println("Load!");
			loadPath();
			
		}
		else if(e.getActionCommand() == "Save") {
			if(currentPath.getPointCount()>2)
			{
				String pathToSave = currentPath.toString();
				System.out.println(pathToSave);
				savePath(pathToSave);
			}
	        
		}
		else if(e.getActionCommand() == "Clear") {
			currentPath = new Path();
			repaint ();
		}
	}
	
	
	/**
	 * Called when the user has selected the 'save' menu item.
	 * The user is asked to specify a save filename.  The path
	 * string is written to that file (so that it can be loaded later).
	 * 
	 * If the user cancels the filename selection dialog, no action
	 * is taken.
	 */
	private void savePath(String pathToSave)
	{
		// Ask the user for a save filename.
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(this);
		if (result != JFileChooser.APPROVE_OPTION)
			return;  // Bail out - user cancelled
		
		File f = chooser.getSelectedFile();

		String completeFilePath = f.getAbsolutePath();
		if(!completeFilePath.endsWith(".txt"))
		    f = new File(completeFilePath + ".txt");

		// Save the file.
		try
		{
			PrintWriter out = new PrintWriter(f);
			out.println(pathToSave); 
			out.close();
		}
		
		catch (IOException e)
		{
			// It would be better to put up an error dialog box, like this:
			// JOptionPane.showConfirmDialog(this, "Could not load that file.");
			System.out.println("Error.");
		}
	}
	
	/**
	 * Called when the user has selected the 'load' menu item.
	 * Prompts the user for a file to load, then loads the 
	 * shapes from that file.  The existing arrangement of shapes
	 * is lost.
	 * 
	 * If the user cancels the load dialog, no action is taken.
	 */
	private void loadPath ()
	{
		// Ask the user for a file to load.  Restrict their choices
		// to files that end in .shapes
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
		chooser.setFileFilter(filter);
		
		int result = chooser.showOpenDialog(this);
		
		if (result != JFileChooser.APPROVE_OPTION)
			return;  // Bail out - user cancelled
		
		// Get the file the user selected.
		File f = chooser.getSelectedFile();
		
		// Load the shapes.
		
		try
		{
		Scanner in = new Scanner(f);
		
		currentPath = new Path(in);

		}
		catch (IOException e)
		{
		// It would be better to put up an error dialog box, like this:
		// JOptionPane.showConfirmDialog(this, "Could not load that file.");
		System.out.println("error loading.");
		}
		// Repaint the window.  (Changing the array does not change
		// the screen.  We must redraw it.)
		repaint();
	}
}


