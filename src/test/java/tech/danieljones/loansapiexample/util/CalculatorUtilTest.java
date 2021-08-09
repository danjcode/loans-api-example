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
}