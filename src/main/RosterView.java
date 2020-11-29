package main;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RosterView extends JFrame {

    private final JDialog aboutDialogue;
    private final JMenu fileMenu;
    private final JMenuItem aboutMenu;
    private JTable rosterTable;
    private JPanel displayPanel;

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

        rosterTable = new JTable(rosterData, columnNames);
        JTableHeader header = rosterTable.getTableHeader();
        header.setBackground(Color.GRAY);
        JScrollPane sp = new JScrollPane(rosterTable);
        rosterTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.add(sp);
        this.setVisible(true);
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
}
