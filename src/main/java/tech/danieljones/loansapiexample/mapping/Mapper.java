package tech.danieljones.loansapiexample.mapping;

import org.springframework.stereotype.Component;
import tech.danieljones.loansapiexample.loans_api_example.model.ScheduleListResponseItem;
import tech.danieljones.loansapiexample.loans_api_example.model.SchedulePostBody;
import tech.danieljones.loansapiexample.loans_api_example.model.ScheduleResponse;
import tech.danieljones.loansapiexample.persistance.model.LoanDetails;
import tech.danieljones.loansapiexample.persistance.model.SetupDetails;
import tech.danieljones.loansapiexample.util.CalculatorUtil;

import java.nio.file.LinkOption;

@Component
public class Mapper {

    public LoanDetails mapNewLoanDetails(SchedulePostBody schedulePostBody) {
        LoanDetails loanDetails = new LoanDetails();

        loanDetails.setSetupDetails(schedulePostBody2SetupDetails(schedulePostBody));
        double loanAmount = schedulePostBody.getAssetValue()- schedulePostBody.getDeposit();
        double monthlyPayment = CalculatorUtil.calculateMonthlyPayment(loanAmount, schedulePostBody.getAnnualInterestPcnt(), schedulePostBody.getLoanDurationMonths(), schedulePostBody.getBalloonAmount());
        double totalPayment = CalculatorUtil.calculateTotalPayments(monthlyPayment, schedulePostBody.getLoanDurationMonths());
        double totalInterest = CalculatorUtil.calculateTotalInterest(loanAmount, totalPayment);
        loanDetails.setTotalInterestDue(totalInterest);
        loanDetails.setMonthlyRepayment(monthlyPayment);
        loanDetails.setTotalPaymentsDue(totalPayment);
        return loanDetails;
    }


    private SetupDetails schedulePostBody2SetupDetails(SchedulePostBody schedulePostBody) {
        SetupDetails setupDetails = new SetupDetails();

        setupDetails.setAnnualInterestPcnt(schedulePostBody.getAnnualInterestPcnt());
        setupDetails.setAssetValue(schedulePostBody.getAssetValue());
        setupDetails.setBalloonAmount(schedulePostBody.getBalloonAmount());
        setupDetails.setDeposit(schedulePostBody.getDeposit());
        setupDetails.setLoanDurationMonths(schedulePostBody.getLoanDurationMonths());

        return setupDetails;
    }

//    public ScheduleResponse loanDetails2ScheduleResponse(LoanDetails loanDetails) {
//
//    }


    public ScheduleListResponseItem loanDetails2ScheduleListResponseItem(LoanDetails loanDetails) {
        ScheduleListResponseItem response = new ScheduleListResponseItem();

        response.setId(loanDetails.getId());
        response.setScheduleRequestDetails(setupDetails2SchedulePostBody(loanDetails.getSetupDetails()));
        response.setTotalPaymentsDue(loanDetails.getTotalPaymentsDue());
        response.setTotalInterestDue(loanDetails.getTotalInterestDue());
        response.setMonthlyRepayment(loanDetails.getMonthlyRepayment());

        return response;
    }

    private SchedulePostBody setupDetails2SchedulePostBody(SetupDetails setupDetails) {
        SchedulePostBody schedulePostBody = new SchedulePostBody();

        if (setupDetails != null) {
            schedulePostBody.setAssetValue(setupDetails.getAssetValue());
            schedulePostBody.annualInterestPcnt(setupDetails.getAnnualInterestPcnt());
            schedulePostBody.setDeposit(setupDetails.getDeposit());
            schedulePostBody.setLoanDurationMonths(setupDetails.getLoanDurationMonths());
            schedulePostBody.setBalloonAmount(setupDetails.getBalloonAmount());

        }

        return schedulePostBody;
    }
}
