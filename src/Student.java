/*
 * @author: Jessica Huber and Dimetrius Hightower
 * ClassID: 2020Fall-T-CSE360-70606
 *  FINAL PROJECT
 */

import java.util.TreeMap;

/**
 * This class represents a Student object with several attributes.
 */
public class Student {

    private String id;
    private String firstName;
    private String lastName;
    private String programPlan;
    private String academicLevel;
    private String asurite;

    private final TreeMap<String, Integer> attendance;

    /**
     * This constructor initializes the values of the attributes of a student.
     * @param id
     * @param firstName
     * @param lastName
     * @param programPlan
     * @param academicLevel
     * @param asurite
     */
    public Student(String id, String firstName, String lastName, String programPlan, String academicLevel, String asurite){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.programPlan = programPlan;
        this.academicLevel = academicLevel;
        this.asurite = asurite;
        this.attendance = new TreeMap<>();
    }

    /**
     * Attendance getter.
     * @return attendance String
     */
    public TreeMap<String, Integer> getAttendance() {
        return attendance;
    }

    /**
     * ID getter.
     * @return id String
     */
    public String getId() {
        return id;
    }

    /**
     * First name getter.
     * @return firstName String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Last name getter.
     * @return lastName String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Program Plan getter.
     * @return programPlan String
     */
    public String getProgramPlan() {
        return programPlan;
    }

    /**
     * Academic level getter.
     * @return academicLevel String
     */
    public String getAcademicLevel() {
        return academicLevel;
    }

    /**
     * Asurite getter.
     * @return asurite String
     */
    public String getAsurite() {
        return asurite;
    }


}
