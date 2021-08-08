package tech.danieljones.loansapiexample.controller;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(String s) {
        super(s);
    }
}
