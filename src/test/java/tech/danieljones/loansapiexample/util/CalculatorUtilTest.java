package tech.danieljones.loansapiexample.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorUtilTest {

    @Test
    void calculateMonthlyPaymentValidation_TotalLoanAmountZero() {
        InvalidCalculationParameterException e = assertThrows(InvalidCalculationParameterException.class, () -> CalculatorUtil.calculateMonthlyPayment(0, 1, 1, 0));
        assertEquals("Total Loan Amount of value 0 must be greater than zero", e.getMessage());
    }

    @Test
    void calculateMonthlyPaymentValidation_AnnualInterestPcntZero() {
        InvalidCalculationParameterException e = assertThrows(InvalidCalculationParameterException.class, () -> CalculatorUtil.calculateMonthlyPayment(1, 0, 1, 0));
        assertEquals("Annual Interest Percentage of value 0 must be greater than zero", e.getMessage());
    }

    @Test
    void calculateMonthlyPaymentValidation_NumberOfPaymentsZero() {
        InvalidCalculationParameterException e = assertThrows(InvalidCalculationParameterException.class, () -> CalculatorUtil.calculateMonthlyPayment(1, 1, 0, 0));
        assertEquals("Number Of Payments of value 0 must be greater than zero", e.getMessage());
    }

    @Test
    void calculateMonthlyPaymentValidation_BalloonLessThanZero() {
        InvalidCalculationParameterException e = assertThrows(InvalidCalculationParameterException.class, () -> CalculatorUtil.calculateMonthlyPayment(1, 1, 1, -1));
        assertEquals("Balloon Amount of value -1 must be zero or greater", e.getMessage());
    }

    @Test
    void calculateMonthlyPayment_NoBalloon() {
        assertEquals(400.76, CalculatorUtil.calculateMonthlyPayment(20000, 0.075, 60, 0));
    }

    @Test
    void calculateMonthlyPayment_WithBalloon() {
        assertEquals(262.88, CalculatorUtil.calculateMonthlyPayment(20000, 0.075, 60, 10000));
    }

    @Test
    void calculateTotalPaymentValidation_paymentAmountZero() {
        InvalidCalculationParameterException e = assertThrows(InvalidCalculationParameterException.class, () -> CalculatorUtil.calculateTotalPayments(0, 1));
        assertEquals("Monthly Payment Amount of value 0 must be greater than zero", e.getMessage());
    }

    @Test
    void calculateTotalPaymentValidation_numberOfPaymentsZero() {
        InvalidCalculationParameterException e = assertThrows(InvalidCalculationParameterException.class, () -> CalculatorUtil.calculateTotalPayments(1, 0));
        assertEquals("Number Of Payments of value 0 must be greater than zero", e.getMessage());
    }

    @Test
    void calculateTotalPayment() {
        assertEquals(101, CalculatorUtil.calculateTotalPayments(10.1, 10));
    }

    @Test
    void calculateTotalInterest() {
        assertEquals(5, CalculatorUtil.calculateTotalInterest(10, 15));
    }

    @Test
    void calculateInterest() {
        assertEquals(200, CalculatorUtil.calculateInterest(100, 24));
    }

    @Test
    void calculatePrinciple() {
        assertEquals(10, CalculatorUtil.calculatePrinciple(20, 10));
    }

    @Test
    void calculateRemainingBalance() {
        assertEquals(90, CalculatorUtil.calculateRemainingBalance(100, 10));
    }


}