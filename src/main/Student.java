package main;

public class Student {

    private String id;
    private String firstName;
    private String lastName;
    private String programPlan;
    private String academicLevel;
    private String asurite;

    private int[] attendance;

    public Student(String id, String firstName, String lastName, String programPlan, String academicLevel, String asurite){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.programPlan = programPlan;
        this.academicLevel = academicLevel;
        this.asurite = asurite;
    }

    public int[] getAttendance() {
        return attendance;
    }

    public void setAttendance(int[] attendance) {
        this.attendance = attendance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProgramPlan() {
        return programPlan;
    }

    public void setProgramPlan(String programPlan) {
        this.programPlan = programPlan;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    public String getAsurite() {
        return asurite;
    }

    public void setAsurite(String asurite) {
        this.asurite = asurite;
    }
}
