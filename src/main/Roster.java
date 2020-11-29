package main;

import javax.swing.*;

public class Roster {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
		RosterView view = new RosterView();
		RosterModel model = new RosterModel();
		RosterController controller = new RosterController(model, view);

		view.registerListener(controller);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.pack();
		view.setSize(900, 520);
		view.setLocationRelativeTo(null); // center of screen
        view.setVisible(true);
    }
}
