package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

public class RosterView extends JFrame {
    private final String[] HEADERS = {"ID", "First Name", "Last Name",
            "Program and Plan", "Level", "ASURITE"};
    private final int  SAVE_WIDTH = 300,
            SAVE_HEIGHT = 100,
            ATTENDANCE_WIDTH = 300,
            ATTENDANCE_HEIGHT = 300;

    private final JDialog aboutDialogue;
    private final JMenu fileMenu;
    private final JMenuItem aboutMenu;
    //private DefaultTableModel model = new DefaultTableModel();
    private JTable rosterTable;
    private JPanel displayPanel;
    //private JTableHeader header;
    private JScrollPane sp;

    /**
     *
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
     *
     * @param controller
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
     *
     * @return
     */
    private JDialog createAboutDialog() {
        JDialog jDialog = new JDialog(this, "About");
        JLabel jLabel = new JLabel("This is our final project! Team members: Jessica Huber, Dimetrius Hightower");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jDialog.add(jLabel);
        jDialog.setSize(500, 200);
        jDialog.setLocationRelativeTo(null); // center
        jDialog.setVisible(false);
        return jDialog;
    }

    public void setAboutVisible(boolean b){
        getAboutDialogue().setVisible(b);
    }

    public JDialog getAboutDialogue() {
        return aboutDialogue;
    }

    /**
     *
     * @param studentMap
     */
    public void drawJTable(HashMap<String, Student> studentMap) {
        int studentCount = 0;
        String[] columnNames = {"ID", "First Name", "Last Name",
                "Program and Plan", "Academic Level", "ASURITE"};
        String[][] rosterData = new String[studentMap.size()][6];
        for (Map.Entry mapElement : studentMap.entrySet()){
            Student temp = (Student) mapElement.getValue();
            rosterData[studentCount] = new String[] {temp.getId(),
                    temp.getFirstName(), temp.getLastName(),
                    temp.getProgramPlan(), temp.getAcademicLevel(), temp.getAsurite()};
            studentCount++;
        }
        /*if (model.getRowCount() > 0){
            for (int index = model.getRowCount() - 1; index > -1; index--){
                model.removeRow(index);
            }
        }*/
        //model = new DefaultTableModel(rosterData, columnNames);
        DefaultTableModel model = new DefaultTableModel(rosterData, columnNames);
        //rosterTable = new JTable(model);
        rosterTable.setModel(model);
        //JScrollPane sp = new JScrollPane(rosterTable);
        //rosterTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //this.add(sp);
        sp.setVisible(true);
        this.setVisible(true);
    }

    public void addAttendance(HashMap<String, Student> studentMap, HashMap<String, Integer> attendanceMap, ArrayList<Date> dates, Date date){
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
        //panel.setVisible(true);
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
     *
     * @return
     */
    public File openFileChooser() {
        File f;
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            f = fc.getSelectedFile();
            return f;
        }
        return null;
    }

    public void saveMessage(){
        JDialog saveDialog = new JDialog(this, "Data Saved", true);
        saveDialog.setSize(SAVE_WIDTH, SAVE_HEIGHT);
        saveDialog.add(new JLabel("Data has been saved to rosterOutput.csv."));
        saveDialog.setLocationRelativeTo(null);
        saveDialog.setVisible(true);
    }
}
