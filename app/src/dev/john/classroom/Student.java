package dev.john.classroom;

import dev.john.classroom.AttendanceCode;

public class Student {
    private final String firstName, lastName;
    private AttendanceCode attendanceCode;

    public Student(String firstName, String lastName, AttendanceCode attendanceCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.attendanceCode = attendanceCode;
    }

    public Student(String firstName, String lastName) {
        this(firstName, lastName, AttendanceCode.NOT_TAKEN);
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

    private String getFullName() {
        return this.getFirstName() + " "+ this.getLastName();
    }

    public String toString() {
        if (this.getAttendanceCode() == AttendanceCode.NOT_TAKEN) {
            return this.getFullName();
        }
        return this.getFullName() + ", " + this.getAttendanceCode();
    }
}
