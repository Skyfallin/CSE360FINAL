/*
 * @author: Jessica Huber and Dimetrius Hightower
 * ClassID: 2020Fall-T-CSE360-70606
 *  FINAL PROJECT
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The RosterView class is used to set up and initialize the GUI
 * components as well as enabling the user to load and save files.
 * It fulfills the 'view' component of the model, view, controller architecture.
 */
public class RosterView extends JFrame {
    private final String[] HEADERS = {"ID", "First Name", "Last Name",
            "Program and Plan", "Level", "ASURITE"};
    private final int  SAVE_WIDTH = 300,
            SAVE_HEIGHT = 100,
            ATTENDANCE_WIDTH = 300,
            ATTENDANCE_HEIGHT = 300,
            PLOT_WIDTH = 750,
            PLOT_HEIGHT = 400,
            ABOUT_WIDTH = 500,
            ABOUT_HEIGHT = 200,
            NUM_DEFAULT_ATTRIBUTES = 6;

    private final JDialog aboutDialogue;
    private final JMenu fileMenu;
    private final JMenuItem aboutMenu;
    private JTable rosterTable;
    private JPanel displayPanel;
    private JScrollPane sp;

    /**
     * Initializes the components of the GUI.
     */
    public RosterView(){
        super("CSE360 Final Project");
        this.aboutDialogue = createAboutDialog();
        //Create the menu
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem rosterItem = new JMenuItem("Load a Roster");
        fileMenu.add(rosterItem);
        JMenuItem attendanceItem = new JMenuItem("Add Attendance");
        fileMenu.add(attendanceItem);
        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);
        JMenuItem dataItem = new JMenuItem("Plot Data");
        fileMenu.add(dataItem);

        aboutMenu = new JMenuItem("About");
        menuBar.add(aboutMenu);

        //Create the display
        displayPanel = new JPanel();
        this.add(displayPanel);
        displayPanel.setVisible(false);

        rosterTable = new JTable();
        sp = new JScrollPane(rosterTable);
        this.add(sp);
        sp.setVisible(false);
    }

    /**
     *  Registers the menu items with listeners for the controller.
     * @param controller object
     */
    public void registerListener(RosterController controller){
        Component[] components = fileMenu.getMenuComponents();
        for (Component component: components){
            if (component instanceof AbstractButton){
                AbstractButton button = (AbstractButton) component;
                button.addActionListener(controller);
            }
        }
        Component aboutComponent = aboutMenu.getComponent();
        if (aboutComponent instanceof AbstractButton){
            AbstractButton button = (AbstractButton) aboutComponent;
            button.addActionListener(controller);
        }
    }

    /**
     * Creates the About pop-up box.
     * @return jDialog for About
     */
    private JDialog createAboutDialog() {
        JDialog jDialog = new JDialog(this, "About");
        JLabel jLabel = new JLabel("This is our final project! Team members: " +
                "Jessica Huber, Dimetrius Hightower");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jDialog.add(jLabel);
        jDialog.setSize(ABOUT_WIDTH, ABOUT_HEIGHT);
        jDialog.setLocationRelativeTo(null); // center
        jDialog.setVisible(false);
        return jDialog;
    }

    /**
     * Toggles the visibility of the About.
     * @param b boolean
     */
    public void setAboutVisible(boolean b){
        getAboutDialogue().setVisible(b);
    }

    /**
     * About dialog getter.
     * @return aboutDialog JDialog
     */
    public JDialog getAboutDialogue() {
        return aboutDialogue;
    }

    /**
     * Draws the roster table from the data in the student map.
     * @param studentMap contains the data for the roster
     */
    public void drawJTable(HashMap<String, Student> studentMap) {
        int studentCount = 0;
        String[] columnNames = {"ID", "First Name", "Last Name",
                "Program and Plan", "Academic Level", "ASURITE"};
        String[][] rosterData = new String[studentMap.size()][NUM_DEFAULT_ATTRIBUTES];
        for (Map.Entry mapElement : studentMap.entrySet()){
            Student temp = (Student) mapElement.getValue();
            rosterData[studentCount] = new String[] {temp.getId(),
                    temp.getFirstName(), temp.getLastName(),
                    temp.getProgramPlan(), temp.getAcademicLevel(), temp.getAsurite()};
            studentCount++;
        }

        DefaultTableModel model = new DefaultTableModel(rosterData, columnNames);
        rosterTable.setModel(model);
        rosterTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        sp.setVisible(true);
        this.setVisible(true);
    }

    /**
     * Updates the table with a table model containing the new attendance data.
     * Also draws a dialog box containing information on the attendance that
     * was added.
     * @param studentMap contains student data
     * @param attendanceMap contains attendance data
     * @param dates contains date data
     * @param date current date being added
     */
    public void addAttendance(HashMap<String, Student> studentMap,
                              HashMap<String, Integer> attendanceMap,
                              ArrayList<String> dates, String date){
        int entryCount = 0;
        DefaultTableModel model = (DefaultTableModel) rosterTable.getModel();
        Integer[] attendanceColumn = new Integer[studentMap.size()];
        for (Student student : studentMap.values()){
            attendanceColumn[entryCount] = student.getAttendance().get(date);
            entryCount++;
        }
        model.addColumn(date, attendanceColumn);
        rosterTable.setModel(model);
        this.setVisible(true);

        JPanel panel = new JPanel();
        panel.setSize(ATTENDANCE_WIDTH, ATTENDANCE_HEIGHT);
        JDialog attendanceDialog = new JDialog(this, "Add Attendance", true);
        attendanceDialog.setSize(ATTENDANCE_WIDTH, ATTENDANCE_HEIGHT);
         int attendanceEntryCount = 0;
         for (Student student: studentMap.values()){
             if (student.getAttendance().containsKey(date)){
                 attendanceEntryCount++;
             }
         }
         panel.add(new JLabel("Data loaded for " + attendanceEntryCount
            + " user(s) in the roster.\n"));

         if (!attendanceMap.isEmpty()){
             panel.add(new JLabel("" + attendanceMap.size() +
                " additional attendee(s) were found:\n"));
             for (String asurite : attendanceMap.keySet()){
                 panel.add(new JLabel("" + asurite + ", connected for "
                    + attendanceMap.get(asurite) + " minute(s)."));
             }
         }
         panel.validate();
         attendanceDialog.add(panel);
         attendanceDialog.setLocationRelativeTo(null);
         panel.setVisible(true);
         attendanceDialog.setVisible(true);

    }

    /**
     * Draws the attendance plot.
     * @param studentMap contains the data for the plot
     */
    public void plot(HashMap<String, Student> studentMap) {
        SwingUtilities.invokeLater(()-> {
            Plot plot = new Plot(studentMap, "Count of Students on Percent of Attendance by Date");
            plot.setSize(PLOT_WIDTH, PLOT_HEIGHT);
            plot.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            plot.setLocationRelativeTo(null);
            plot.setVisible(true);
        });

    }

    /**
     * Populates a File Chooser for the user to select a file load.
     * @return f File to be read
     */
    public File openFileChooser() {
        File f = null;
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            f = fc.getSelectedFile();
        }
        return f;
    }

    /**
     * Creates a pop-up letting the user know the data was saved.
     */
    public void saveMessage(){
        JDialog saveDialog = new JDialog(this, "Data Saved", true);
        saveDialog.setSize(SAVE_WIDTH, SAVE_HEIGHT);
        saveDialog.add(new JLabel("Data has been saved to rosterOutput.csv."));
        saveDialog.setLocationRelativeTo(null);
        saveDialog.setVisible(true);
    }

    /**
     * Grab the menu for testing purposes.
     * @return A JMenu component
     */
    public JMenu getFileMenu() {
        return fileMenu;
    }

    /**
     * Grab the 'About' AbstractButton for testing
     * @return an AbstractButton
     */
    public JMenuItem getAboutMenu() {
        return aboutMenu;
    }
}
