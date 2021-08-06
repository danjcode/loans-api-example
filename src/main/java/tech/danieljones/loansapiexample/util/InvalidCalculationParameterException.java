package tech.danieljones.loansapiexample.util;

public class InvalidCalculationParameterException extends RuntimeException {
    public InvalidCalculationParameterException(String message) {
        super(message);
    }
}
