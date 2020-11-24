package main;

import javax.swing.*;

public class Roster {

    public static JMenuBar createMenuBar() {
    	JMenuBar menuBar;
    	JMenu fileMenu, aboutMenu;
    	JMenuItem rosterItem, attendanceItem, saveItem, dataItem;    	
    	
    	menuBar = new JMenuBar();
    	
    	fileMenu = new JMenu("File");
    	menuBar.add(fileMenu);
    	rosterItem = new JMenuItem("Load a Roster");
    	fileMenu.add(rosterItem);
    	attendanceItem = new JMenuItem("Add Attendance");
    	fileMenu.add(attendanceItem);
    	saveItem = new JMenuItem("Save");
    	fileMenu.add(saveItem);
    	dataItem = new JMenuItem("Plot Data");
    	fileMenu.add(dataItem);
    	
    	aboutMenu = new JMenu("About");
    	menuBar.add(aboutMenu);
    	
        return menuBar;
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("CSE360 Final Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        frame.setJMenuBar(createMenuBar());
        //frame.setContentPane(demo.createContentPane());

        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(Roster::createAndShowGUI);
    }
}
