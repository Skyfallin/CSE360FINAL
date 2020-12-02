package main;

import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class RosterController implements ActionListener {
    private final int PLOT_HEIGHT = 350, PLOT_WIDTH = 700;
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
                    Date date = rosterModel.takeAttendance(file, new DatePicker(rosterView).getPickedDate());
                    rosterView.addAttendance(rosterModel.getStudentMap(), rosterModel.getAttendanceMap(), rosterModel.getDates(), date);
                }
                break;
            }
            case "Save": {
                File outputFile = new File("rosterOutput.csv");
                try {
                    if (outputFile.createNewFile()){
                        String filePath = outputFile.getAbsolutePath();
                        rosterModel.save(filePath);
                        rosterView.saveMessage();
                    } else {
                        outputFile.delete();
                        outputFile = new File("rosterOutput.csv");
                        String filePath = outputFile.getAbsolutePath();
                        rosterModel.save(filePath);
                        rosterView.saveMessage();
                    }
                } catch (IOException e1){
                    e1.printStackTrace();
                }
                break;
            }
            case "Plot Data": {
                SwingUtilities.invokeLater(() -> {
                    Plot plot = new Plot(rosterModel.getStudentMap(),
                            "Percent of Attendance of Students by Date ");
                    plot.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    plot.setSize(PLOT_WIDTH, PLOT_HEIGHT);
                    plot.setLocationRelativeTo(null);
                    plot.setVisible(true);
                });
                //rosterView.plot(rosterModel.getStudentMap());
                break;
            }
        }

    }
}
