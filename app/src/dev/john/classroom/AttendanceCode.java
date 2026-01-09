package dev.john.classroom;

public enum AttendanceCode {
    PRESENT('p'),
    ABSENT('a'),
    TARDY('t'),
    NOT_TAKEN('n');

    private char not_quite_sure_what_to_call_this;

    private AttendanceCode(char value) {
        this.not_quite_sure_what_to_call_this = value;
    }

    public String toString() {
        return this.name().toLowerCase();
    }

    public char toChar(){
        return this.not_quite_sure_what_to_call_this;
    }
}
