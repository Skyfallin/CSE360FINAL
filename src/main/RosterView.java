package main;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class RosterView extends JFrame {

    private final JDialog aboutDialogue;

    /**
     *
     */
    public RosterView() {
        super("CSE360 Final Project");
        this.aboutDialogue = createAboutDialog();
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
        aboutMenu.addMenuListener(aboutMenuListener());
        menuBar.add(aboutMenu);

        return menuBar;
    }

    private JDialog createAboutDialog() {
        JDialog jDialog = new JDialog(this, "About");
        JLabel jLabel = new JLabel("This is our final project!");
        jDialog.add(jLabel);
        jDialog.setSize(200, 200);
        jDialog.setVisible(false);
        return jDialog;
    }

    private MenuListener aboutMenuListener() {
        return new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                getAboutDialogue().setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        };
    }

    public JDialog getAboutDialogue() {
        return aboutDialogue;
    }
}
