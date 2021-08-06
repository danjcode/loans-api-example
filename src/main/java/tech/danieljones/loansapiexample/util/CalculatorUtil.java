package tech.danieljones.loansapiexample.util;

import java.text.MessageFormat;

public class CalculatorUtil {

    private static final String INVALID_PARAM_GT_ZERO_MESSAGE_FORMAT = "{0} of value {1} must be greater than zero";
    private static final String INVALID_PARAM_EQ_OR_GT_ZERO_MESSAGE_FORMAT = "{0} of value {1} must be zero or greater";

    public static double calculateMonthlyPayment(double totalLoanAmount, double annualInterestPcnt, int numberOfPayments, double balloon) {
        validateParams(totalLoanAmount, annualInterestPcnt, numberOfPayments, balloon);
        if (balloon > 0) {
            return calculateMonthlyPaymentWithBalloon(totalLoanAmount, annualInterestPcnt, numberOfPayments, balloon);
        }
        return calculateMonthlyPaymentWithoutBalloon(totalLoanAmount, annualInterestPcnt, numberOfPayments);
    }

    private static double calculateMonthlyPaymentWithoutBalloon(double totalLoanAmount, double annualInterestPcnt, int numberOfPayments) {
        return (totalLoanAmount * Math.pow(1 + annualInterestPcnt, numberOfPayments) / Math.pow(1 + annualInterestPcnt, numberOfPayments - 1));
    }

    private static double calculateMonthlyPaymentWithBalloon(double totalLoanAmount, double annualInterestPcnt, int numberOfPayments, double balloon) {
        return (totalLoanAmount - (balloon / Math.pow(1 + annualInterestPcnt, numberOfPayments) * (annualInterestPcnt / Math.pow(1 + annualInterestPcnt, numberOfPayments))));
    }

    private static void validateParams(double totalLoanAmount, double annualInterestPcnt, int numberOfPayments, double balloon) {
        if (!(totalLoanAmount > 0)) {
            throw new InvalidCalculationParameterException(MessageFormat.format(INVALID_PARAM_GT_ZERO_MESSAGE_FORMAT, "Total Loan Amount", totalLoanAmount));
        }

        if (!(annualInterestPcnt > 0)) {
            throw new InvalidCalculationParameterException(MessageFormat.format(INVALID_PARAM_GT_ZERO_MESSAGE_FORMAT, "Annual Interest Percentage", annualInterestPcnt));
        }

        if (!(numberOfPayments > 0)) {
            throw new InvalidCalculationParameterException(MessageFormat.format(INVALID_PARAM_GT_ZERO_MESSAGE_FORMAT, "Annual Interest Percentage", annualInterestPcnt));
        }

        if (balloon < 0) {
            throw new InvalidCalculationParameterException(MessageFormat.format(INVALID_PARAM_EQ_OR_GT_ZERO_MESSAGE_FORMAT, "Balloon Amount", balloon));
        }
    }
}
