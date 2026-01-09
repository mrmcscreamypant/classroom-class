package dev.john.classroom;

import dev.john.classroom.AttendanceCode;

public class App {
    private static final Student[] students = new Student[] {
        new Student("John","Schiefelbein"),
        new Student("Alan", "Turing")
    };

    public static void main(String[] args) {
        final Classroom aBlock = new Classroom(4, 4);
        for (final Student student: App.students) {
            aBlock.addStudent(student);
        }
        System.out.println(aBlock);
        aBlock.takeAttendance();
        System.out.println(aBlock);
    }
}
