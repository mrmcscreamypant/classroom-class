package dev.john.classroom;

import java.io.File;

public class App {
    public static void main(String[] args) {
        final Classroom aBlock = Classroom.loadFromFile(new File("/home/curra/classroom-class/test.classroom"));
        System.out.println(aBlock);
        aBlock.takeAttendance();
        System.out.println(aBlock);
    }
}
