package main;

/*
 * @author: Jessica Huber and Dimetrius Hightower
 * ClassID: 2020Fall-T-CSE360-70606
 *  FINAL PROJECT
 */

import javax.swing.*;

/**
 * This class is the main driver for the Roster project.
 */
public class Roster {

    /**
     *  Main driving method of the Roster.
     * @param args
     */
    public static void main(String[] args) {
		setupApplication();
    }

	/**
	 * Code for driving method (for use in JUnit tests)
	 * @return The RosterController, with getters for view and model.
	 */
	public static RosterController setupApplication() {
		RosterView view = new RosterView();
		RosterModel model = new RosterModel();
		RosterController controller = new RosterController(model, view);

		view.registerListener(controller);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.pack();
		view.setSize(900, 520);
		view.setLocationRelativeTo(null); // center of screen
		view.setVisible(true);
		return controller;
	}
}
