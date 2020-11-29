package main;

import java.io.*;
import java.util.Date;
import java.util.HashMap;

public class RosterModel {

    HashMap<String, Student> studentMap = new HashMap<>();

    /**
     *
     * @param file
     */
    public void createStudentMap(File file) {
        try {
            FileReader obj = new FileReader(file);
            BufferedReader br = new BufferedReader(obj);
            String line = br.readLine();
            while(line != null) {
                String[] attributes = line.split(",");
                Student student = createStudent(attributes);
                studentMap.put(student.getAsurite(), student);
                line = br.readLine();
            }
        } catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     *
     * @param attributes
     * @return
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
     *
     * @param file
     */
    public void takeAttendance(File file, Date date) {
        try {
            FileReader obj = new FileReader(file);
            BufferedReader br = new BufferedReader(obj);
            String line = br.readLine();
            while(line != null) {
                String[] fields = line.split(",");
                Student student = getStudent(fields[0]);
                student.getAttendance().put(date, Integer.valueOf(fields[1]));
                line = br.readLine();
            }
        } catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public HashMap<String, Student> getStudentMap() {
        return studentMap;
    }

    /**
     *
     * @param asurite
     * @return
     */
    public Student getStudent(String asurite) {
        for (Student student : studentMap.values()) {
            if (student.getAsurite().equals(asurite))
                return student;
        }
        return null;
    }
}
