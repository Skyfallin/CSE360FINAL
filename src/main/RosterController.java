package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
        String command = e.getActionCommand();
        /*if (command.equals("Load a Roster")){
            rosterModel.createStudentMap();
            rosterView.update(rosterModel.getStudentMap());
        }*/
        if (command.equals("About")) {
            rosterView.setAboutVisible(true);
        } else if (command.equals("Load a Roster")){
            File file = rosterView.openFileChooser();
            if (file != null){
                rosterModel.createStudentMap(file);
                rosterView.drawJTable(rosterModel.getStudentMap());
            }

        }
    }
}
