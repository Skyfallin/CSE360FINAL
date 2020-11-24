package main;

public class Roster {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
		RosterView test = new RosterView();
        javax.swing.SwingUtilities.invokeLater(test::displayFrame);
    }
}
