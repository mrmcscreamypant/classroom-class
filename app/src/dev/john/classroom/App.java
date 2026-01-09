package dev.john.classroom;

import dev.john.classroom.AttendanceCode;

public class App {
    public static void main(String[] args) {
        final Classroom aBlock = new Classroom(4, 4);
        aBlock.addStudent(new Student("John", "Schiefelbein"));
        System.out.println(aBlock);
    }
}
