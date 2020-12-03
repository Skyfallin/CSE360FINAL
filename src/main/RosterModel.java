package main;

/*
 * @author: Jessica Huber and Dimetrius Hightower
 * ClassID: 2020Fall-T-CSE360-70606
 *  FINAL PROJECT
 */

import java.io.*;
import java.util.*;

/**
 * The Model class contains the data used in the system and provides an API for
 * interacting with the data. Fulfills the model part of the model, view,
 * controller architecture.
 */
public class RosterModel {
    private final char NEW_LINE= '\n', COMMA= ',';
    private final int ASURITE_INDEX = 5;
    HashMap<String, Student> studentMap = new HashMap<>();
    HashMap<String, Integer> attendanceMap = new HashMap<>();
    ArrayList<String> dates = new ArrayList<>();

    /**
     * Adds the data from the file to the student hashmap data structure.
     * @param file object
     */
    public void createStudentMap(File file) {
        studentMap.clear();
        attendanceMap.clear();
        dates.clear();
        try {
            FileReader obj = new FileReader(file);
            BufferedReader br = new BufferedReader(obj);
            String line = br.readLine();
            while(line != null) {
                String[] attributes = line.split(",");
                Student student = createStudent(attributes);
                studentMap.put(attributes[ASURITE_INDEX], student);
                line = br.readLine();
            }
        } catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Creates students.  Helper method for createStudentMap.
     * @param attributes String[]
     * @return new Student object
     */
    private Student createStudent(String[] attributes) {
        String id = attributes[0];
        String firstName = attributes[1];
        String lastName = attributes[2];
        String programPlan = attributes[3];
        String academicLevel = attributes[4];
        String asurite = attributes[5];

        return new Student(id, firstName, lastName, programPlan, academicLevel, asurite);
    }

    /**
     * Updates the attendance data with new data from file.  Updates each student
     * attendance and attendance hashmap.  Also stores the date.
     * @param file File
     * @param date String
     */
    public String takeAttendance(File file, String date) {
        dates.add(date);
        attendanceMap.clear();
        try {
            FileReader obj = new FileReader(file);
            BufferedReader br = new BufferedReader(obj);
            String line = br.readLine();
            while(line != null) {
                String[] fields = line.split(",");
                String key = fields[0];
                if (attendanceMap.get(key) != null){
                    attendanceMap.put(key, attendanceMap.get(key) + Integer.parseInt(fields[1]));
                } else {
                    attendanceMap.put(key, Integer.parseInt(fields[1]));
                }

                line = br.readLine();
            }
            br.close();
        } catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (Student student: studentMap.values()){
            student.getAttendance().put(date, attendanceMap.get(student.getAsurite()));
            attendanceMap.remove(student.getAsurite());
        }

        return date;
    }

    /**
     *  Saves the student hashmap data into a new file.
     * @param filePath String
     */
    public void save(String filePath){
        try {
            File outputFile = new File(filePath);

            FileWriter outputWriter = new FileWriter((outputFile));
            BufferedWriter bw = new BufferedWriter(outputWriter);
            StringBuilder writeLine;
            //HEADER
            writeLine = new StringBuilder("ID,First Name,Last Name,Program and Plan,Academic Level,ASURITE");
            for (String date : dates) {
                writeLine.append(COMMA).append(date);
            }
            bw.write(writeLine.toString() + NEW_LINE);
            //ROWS
            for (Student student : studentMap.values()){
                writeLine = new StringBuilder();
                writeLine.append(student.getId()).append(COMMA)
                        .append(student.getFirstName()).append(COMMA)
                        .append(student.getLastName()).append(COMMA)
                        .append(student.getProgramPlan()).append(COMMA)
                        .append(student.getAcademicLevel()).append(COMMA)
                        .append(student.getAsurite());
                for (String date: dates){
                    writeLine.append(COMMA).append(student.getAttendance().get(date));
                }
                bw.write(writeLine.toString() + NEW_LINE);
            }
            bw.close();
        } catch (IOException e){
            System.out.println("An error occurred when writing to file.");
            e.printStackTrace();
        }
    }

    /**
     * Student hashmap getter.
     * @return studentMap
     */
    public HashMap<String, Student> getStudentMap() {
        return studentMap;
    }

    /**
     * Attendance hashmap getter.
     * @return attendanceMap
     */
    public HashMap<String, Integer> getAttendanceMap(){ return attendanceMap; }

    /**
     * Dates hashmap getter.
     * @return dates
     */
    public ArrayList<String> getDates(){
        return dates;
    }
}
