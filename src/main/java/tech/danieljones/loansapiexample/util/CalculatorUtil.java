package tech.danieljones.loansapiexample.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;

public class CalculatorUtil {

    private static final String INVALID_PARAM_GT_ZERO_MESSAGE_FORMAT = "{0} of value {1} must be greater than zero";
    private static final String INVALID_PARAM_EQ_OR_GT_ZERO_MESSAGE_FORMAT = "{0} of value {1} must be zero or greater";

    public static double calculateMonthlyPayment(double totalLoanAmount, double annualInterestPcnt, int numberOfPayments, double balloon) {
        validateMonthlyPaymentParams(totalLoanAmount, annualInterestPcnt, numberOfPayments, balloon);
        if (balloon > 0) {
            return calculateMonthlyPaymentWithBalloon(totalLoanAmount, annualInterestPcnt, numberOfPayments, balloon);
        }
        return calculateMonthlyPaymentWithoutBalloon(totalLoanAmount, annualInterestPcnt, numberOfPayments);
    }

    public static double calculateTotalPayments(double monthlyPaymentAmount, int numOfPayments) {
        validateTotalPaymentParams(monthlyPaymentAmount, numOfPayments);
        return monthlyPaymentAmount * numOfPayments;
    }

    private static double calculateMonthlyPaymentWithoutBalloon(double totalLoanAmount, double annualInterestPcnt, int numberOfPayments) {

        double monthlyInterestPcnt = annualInterestPcnt / 12;
        double leftPow = Math.pow(1 + monthlyInterestPcnt, numberOfPayments);
        double rightPow = Math.pow(1 + monthlyInterestPcnt, numberOfPayments) - 1;
        double powDiv = leftPow / rightPow;
        double unroundedMonthlyRepayment = totalLoanAmount * (monthlyInterestPcnt * powDiv);

        return new BigDecimal(unroundedMonthlyRepayment).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

    }

    private static double calculateMonthlyPaymentWithBalloon(double totalLoanAmount, double annualInterestPcnt, int numberOfPayments, double balloon) {

        return (totalLoanAmount - (balloon / Math.pow(1 + annualInterestPcnt, numberOfPayments) * (annualInterestPcnt / Math.pow(1 + annualInterestPcnt, numberOfPayments))));
    }

    private static void validateTotalInterestParams(double loanAmount, double totalPayment) {
        if (!(loanAmount > 0)) {
            throw new InvalidCalculationParameterException(MessageFormat.format(INVALID_PARAM_GT_ZERO_MESSAGE_FORMAT, "Loan Amount", loanAmount));
        }

        if (!(totalPayment > 0)) {
            throw new InvalidCalculationParameterException(MessageFormat.format(INVALID_PARAM_GT_ZERO_MESSAGE_FORMAT, "Total Payment Amount", totalPayment));
        }
    }

    private static void validateTotalPaymentParams(double monthlyPaymentAmount, int numberOfPayments) {
        if (!(monthlyPaymentAmount > 0)) {
            throw new InvalidCalculationParameterException(MessageFormat.format(INVALID_PARAM_GT_ZERO_MESSAGE_FORMAT, "Monthly Payment Amount", monthlyPaymentAmount));
        }

        if (!(numberOfPayments > 0)) {
            throw new InvalidCalculationParameterException(MessageFormat.format(INVALID_PARAM_GT_ZERO_MESSAGE_FORMAT, "Number Of Payments", numberOfPayments));
        }
    }

    private static void validateMonthlyPaymentParams(double totalLoanAmount, double annualInterestPcnt, int numberOfPayments, double balloon) {
        if (!(totalLoanAmount > 0)) {
            throw new InvalidCalculationParameterException(MessageFormat.format(INVALID_PARAM_GT_ZERO_MESSAGE_FORMAT, "Total Loan Amount", totalLoanAmount));
        }

        if (!(annualInterestPcnt > 0)) {
            throw new InvalidCalculationParameterException(MessageFormat.format(INVALID_PARAM_GT_ZERO_MESSAGE_FORMAT, "Annual Interest Percentage", annualInterestPcnt));
        }

        if (!(numberOfPayments > 0)) {
            throw new InvalidCalculationParameterException(MessageFormat.format(INVALID_PARAM_GT_ZERO_MESSAGE_FORMAT, "Number Of Payments", numberOfPayments));
        }

        if (balloon < 0) {
            throw new InvalidCalculationParameterException(MessageFormat.format(INVALID_PARAM_EQ_OR_GT_ZERO_MESSAGE_FORMAT, "Balloon Amount", balloon));
        }
    }

    public static double calculateTotalInterest(double loanAmount, double totalPayment) {
        return totalPayment - loanAmount;
    }
}
