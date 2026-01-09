package dev.john.classroom;

public class Classroom {
    private Student[][] seatingChart;

    private final int CELL_WIDTH = 5;

    public Classroom(int rows, int cols) {
        seatingChart = new Student[rows][cols];
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
            for (final Student student: row) {
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
        while (problem != null) {
            problem.setAttendance(AttendanceCode.PRESENT);
            problem = this.getProblemStudent();
        }
    }
}