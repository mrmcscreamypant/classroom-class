package dev.john.classroom;

public enum AttendanceCode {
    PRESENT('p'),
    ABSENT('a'),
    TARDY('t'),
    NOT_TAKEN('n');

    private char that;

    protected static class AttendanceCodeError extends Exception {

    }

    private AttendanceCode(char value) {
        this.that = value;
    }

    public String toString() {
        return this.name().toLowerCase();
    }

    public char toChar(){
        return this.that;
    }

    public static AttendanceCode codeFromChar(char c) throws AttendanceCodeError {
        for (AttendanceCode code : AttendanceCode.values()) {
            if (code.that == c) {
                return code;
            }
        }
        throw new AttendanceCodeError();
    }
}
