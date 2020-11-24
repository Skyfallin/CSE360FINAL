package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RosterController implements ActionListener {

    private RosterModel rosterModel;
    private RosterView rosterView;

    /**
     *
     * @param rosterModel
     * @param rosterView
     */
    public RosterController(RosterModel rosterModel, RosterView rosterView) {
        this.rosterModel = rosterModel;
        this.rosterView = rosterView;
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
