package main;

import javax.swing.*;

public class Roster {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
		RosterView view = new RosterView();
		RosterModel model = new RosterModel();
		RosterController controller = new RosterController(model, view);

		view.registerListener(controller);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.pack();
		view.setSize(900, 520);
        view.setVisible(true);

    }
}
