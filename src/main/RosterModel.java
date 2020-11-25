package main;

import java.io.*;
import java.util.HashMap;

public class RosterModel {
    HashMap<String, Student> studentMap = new HashMap<>();

    public void createStudentMap(File file){
        try {
            FileReader obj = new FileReader(file);
            BufferedReader br = new BufferedReader(obj);
            String line = br.readLine();
            while(line != null){
                String[] attributes = line.split(",");
                Student student = createStudent(attributes);
                studentMap.put(student.getAsurite(), student);
                line = br.readLine();
            }
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private Student createStudent(String[] attributes){
        String id = attributes[0];
        String firstName = attributes[1];
        String lastName = attributes[2];
        String programPlan = attributes[3];
        String academicLevel = attributes[4];
        String asurite = attributes[5];

        return new Student(id, firstName, lastName, programPlan, academicLevel, asurite);
    }

    public HashMap<String, Student> getStudentMap() {
        return studentMap;
    }
}
