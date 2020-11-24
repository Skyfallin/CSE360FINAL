package main;

import javax.swing.*;

public class RosterView extends JFrame {

    /**
     *
     */
    public RosterView() {
        super("CSE360 Final Project");
        // Create and set up the window.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create and set up the content pane.
        this.setJMenuBar(createMenuBar());
    }

    /**
     *
     */
    public void displayFrame() {
        // Display the window.
        this.setSize(900, 520);
        this.setVisible(true);
    }

    /**
     *
     * @return
     */
    private JMenuBar createMenuBar() {
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
}
