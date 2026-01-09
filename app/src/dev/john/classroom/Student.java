package dev.john.classroom;

public class Student {
    private final String firstName, lastName;
    private AttendanceCode attendanceCode;

    public Student(String firstName, String lastName, AttendanceCode attendanceCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.attendanceCode = attendanceCode;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public AttendanceCode getAttendanceCode() {
        return this.attendanceCode;
    }

    public String getInitials() {
        return String.valueOf(this.getFirstName().charAt(0)) + String.valueOf(this.getLastName().charAt(0));
    }

    public void setAttendance(AttendanceCode newCode) {
        this.attendanceCode = newCode;
    }

    public String toString() {
        return this.getFirstName() + " " + this.getLastName() + ", " + this.getAttendanceCode();
    }
}
