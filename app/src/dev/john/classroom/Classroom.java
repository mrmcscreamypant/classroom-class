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
    }

    @SuppressWarnings("unchecked")
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

    public boolean attendanceTaken() {
        return false;
    }

    public void takeAttendance() {

    }
}