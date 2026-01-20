package dev.john.classroom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Classroom {

    private final Student[][] seatingChart;

    private final int CELL_WIDTH = 5;

    public Classroom(int rows, int cols) {
        seatingChart = new Student[rows][cols];
    }

    private int getRows() {
        return this.seatingChart.length;
    }

    private int getCols() {
        return this.seatingChart[0].length;
    }

    public void addStudent(Student s, int row, int col) {
        this.seatingChart[row][col] = s;
    }

    public void addStudent(Student s) {
        int r = 0;
        int c = 0;
        for (final Student[] row : this.seatingChart) {
            for (final Student couldPossiblyBeAStudent : row) {
                if (couldPossiblyBeAStudent != null) {
                    c++;
                    continue;
                }
                this.addStudent(s, r, c);
                return;
            }
            r++;
        }
    }

    @Override
    public String toString() {
        String result = "";

        for (final Student[] row : this.seatingChart) {
            result += "-".repeat((1 + this.CELL_WIDTH) * row.length + 1) + "\n";
            for (final Student student : row) {
                result += "|";
                if (student == null) {
                    result += " ".repeat(this.CELL_WIDTH);
                    continue;
                }
                final String initials = student.getInitials();
                result += initials + " ".repeat(this.CELL_WIDTH - initials.length());
            }
            result += "|\n";

            // THIS VIOLATES DRY AND I HATE IT
            for (final Student student : row) {
                result += "|";
                if (student == null) {
                    result += " ".repeat(this.CELL_WIDTH);
                    continue;
                }
                final String code = String.valueOf(student.getAttendanceCode().toChar());
                result += code + " ".repeat(this.CELL_WIDTH - code.length());
            }
            result += "|\n";
        }
        result += "-".repeat((1 + this.CELL_WIDTH) * this.seatingChart[this.seatingChart.length - 1].length + 1) + "\n";

        return result;
    }

    public Student getProblemStudent() {
        for (final Student[] row : this.seatingChart) {
            for (final Student student : row) {
                if (student == null) {
                    continue;
                }
                if (student.getAttendanceCode() == AttendanceCode.NOT_TAKEN) {
                    return student;
                }
            }
        }
        return null;
    }

    public void takeAttendance() {
        Student problem = this.getProblemStudent();
        try (Scanner scanner = new Scanner(System.in)) {
            while (problem != null) {
                System.out.print(problem + " > ");
                String line = scanner.nextLine();
                if (line.length() > 1) {
                    System.out.println("Invalid attendence code: " + line);
                    continue;
                }
                try {
                    final AttendanceCode code = AttendanceCode.codeFromChar(line.charAt(0));
                    if (code == AttendanceCode.NOT_TAKEN) {
                        throw new AttendanceCode.AttendanceCodeError();
                    }
                    problem.setAttendance(code);
                    System.out.println(problem);
                } catch (AttendanceCode.AttendanceCodeError e) {
                    System.out.println("Invalid attendence code: " + line);
                    continue;
                }
                problem = this.getProblemStudent();
            }
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static Classroom loadFromFile(File file) { // Sorry, but I can't make this a constructor without violating DRY. I have done that enough for this class already.
        try {
            final Classroom newRoom;
            try (final Scanner scanner = new Scanner(file)) {
                final Matcher firstLineMatch = Pattern.compile("^(?<width>\\d+) (?<height>\\d+)$").matcher(scanner.nextLine());
                firstLineMatch.find();
                newRoom = new Classroom(
                        Integer.parseInt(firstLineMatch.group("width")),
                        Integer.parseInt(firstLineMatch.group("height"))
                );
                final Pattern studentPattern = Pattern.compile("^(?<firstName>\\w+) (?<lastName>\\w+)$");
                while (scanner.hasNextLine()) {
                    final Matcher studentMatch = studentPattern.matcher(scanner.nextLine());
                    studentMatch.find();
                    newRoom.addStudent(
                            new Student(
                                    studentMatch.group("firstName"),
                                    studentMatch.group("lastName")
                            )
                    );
                }
            }
            return newRoom;
        } catch (FileNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Student[] getStudents() {
        final ArrayList<Student> students = new ArrayList<>(0);
        for (final Student[] row : this.seatingChart) {
            for (final Student student : row) {
                if (student != null) {
                    students.add(student);
                }
            }
        }
        return students.toArray(Student[]::new);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void saveToFile(File filename) {
        try (final PrintWriter file = new PrintWriter(new FileWriter(filename))) {
            file.print(this.getRows() + " " + this.getCols());
            for (final Student student : this.getStudents()) {
                file.print("\n" + student.serialize());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
