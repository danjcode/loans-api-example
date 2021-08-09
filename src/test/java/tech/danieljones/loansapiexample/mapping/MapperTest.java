package tech.danieljones.loansapiexample.mapping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.danieljones.loansapiexample.loans_api_example.model.SchedulePostBody;
import tech.danieljones.loansapiexample.persistance.model.LoanDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MapperTest {

    private Mapper mapper;

    @BeforeEach
    void setup() {
        mapper = new Mapper();
    }

    @Test
    void mapNewLoanDetails() {
        SchedulePostBody testBody = new SchedulePostBody();
        testBody.setAssetValue(25000d);
        testBody.setDeposit(5000d);
        testBody.setAnnualInterestPcnt(0.075);
        testBody.setLoanDurationMonths(60);
        testBody.setBalloonAmount(0d);

        LoanDetails loanDetails = mapper.mapNewLoanDetails(testBody);

        assertNotNull(loanDetails);
        assertEquals(400.76, loanDetails.getMonthlyRepayment());
        assertEquals(4045.6, loanDetails.getTotalInterestDue());
        assertEquals(24045.6, loanDetails.getTotalPaymentsDue());

        assertNotNull(loanDetails.getSetupDetails());
        assertEquals(5000, loanDetails.getSetupDetails().getDeposit());
        assertEquals(25000, loanDetails.getSetupDetails().getAssetValue());
        assertEquals(0.075, loanDetails.getSetupDetails().getAnnualInterestPcnt());
        assertEquals(60, loanDetails.getSetupDetails().getLoanDurationMonths());
        assertEquals(0, loanDetails.getSetupDetails().getBalloonAmount());

        assertNotNull(loanDetails.getScheduleItems());
        assertEquals(60, loanDetails.getScheduleItems().size());

        // period 1
        assertEquals(1, loanDetails.getScheduleItems().get(0).getPeriod());
        assertEquals(400.76, loanDetails.getScheduleItems().get(0).getPayment());
        assertEquals(252.98, loanDetails.getScheduleItems().get(0).getPrinciple());
        assertEquals(147.78, loanDetails.getScheduleItems().get(0).getInterest());
        assertEquals(23644.84, loanDetails.getScheduleItems().get(0).getBalance());

        assertEquals(2, loanDetails.getScheduleItems().get(1).getPeriod());
        assertEquals(400.76, loanDetails.getScheduleItems().get(1).getPayment());
        assertEquals(255.48, loanDetails.getScheduleItems().get(1).getPrinciple());
        assertEquals(145.28, loanDetails.getScheduleItems().get(1).getInterest());
        assertEquals(23244.08, loanDetails.getScheduleItems().get(1).getBalance());





    }

}