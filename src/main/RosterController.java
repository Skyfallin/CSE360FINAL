package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RosterController implements ActionListener {

    private final RosterModel rosterModel;
    private final RosterView rosterView;

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
        switch (command) {
            case "About":
                rosterView.setAboutVisible(true);
                break;
            case "Load a Roster": {
                File file = rosterView.openFileChooser();
                if (file != null) {
                    rosterModel.createStudentMap(file);
                    rosterView.drawJTable(rosterModel.getStudentMap());
                }
                break;
            }
            case "Add Attendance": {
                File file = rosterView.openFileChooser();
                if (file != null) {
                    rosterModel.takeAttendance(file, new DatePicker(rosterView).getPickedDate());
                }
                break;
            }
        }
    }
}
