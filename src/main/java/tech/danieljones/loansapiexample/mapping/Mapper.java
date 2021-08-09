package tech.danieljones.loansapiexample.mapping;

import org.springframework.stereotype.Component;
import tech.danieljones.loansapiexample.loans_api_example.model.ScheduleListResponseItem;
import tech.danieljones.loansapiexample.loans_api_example.model.SchedulePostBody;
import tech.danieljones.loansapiexample.loans_api_example.model.ScheduleResponse;
import tech.danieljones.loansapiexample.persistance.model.LoanDetails;
import tech.danieljones.loansapiexample.persistance.model.ScheduleItem;
import tech.danieljones.loansapiexample.persistance.model.SetupDetails;
import tech.danieljones.loansapiexample.util.CalculatorUtil;

import java.util.ArrayList;
import java.util.List;

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

        double balance = totalPayment;


        List<ScheduleItem> scheduleItemList = new ArrayList<>();
        for (int i = 0; i < schedulePostBody.getLoanDurationMonths(); i++) {
            ScheduleItem item = new ScheduleItem();
            balance = CalculatorUtil.calculateRemainingBalance(balance, monthlyPayment);
            double interest = CalculatorUtil.calculateInterest(balance, schedulePostBody.getAnnualInterestPcnt());
            double principle = CalculatorUtil.calculatePrinciple(monthlyPayment, interest);
            item.setPeriod(i+1);
            item.setPayment(monthlyPayment);
            item.setInterest(interest);
            item.setPrinciple(principle);
            item.setBalance(balance);
            item.setLoanDetails(loanDetails);

            scheduleItemList.add(item);
        }

        loanDetails.setScheduleItems(scheduleItemList);

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

    public ScheduleResponse loanDetails2ScheduleResponse(LoanDetails loanDetails) {
        ScheduleResponse response = new ScheduleResponse();

        response.setId(loanDetails.getId());
        response.setScheduleRequestDetails(setupDetails2SchedulePostBody(loanDetails.getSetupDetails()));
        response.setTotalPaymentsDue(loanDetails.getTotalPaymentsDue());
        response.setTotalInterestDue(loanDetails.getTotalInterestDue());
        response.setMonthlyRepayment(loanDetails.getMonthlyRepayment());

        SchedulePostBody requestDetails = new SchedulePostBody();
        requestDetails.setAssetValue(loanDetails.getSetupDetails().getAssetValue());
        requestDetails.setBalloonAmount(loanDetails.getSetupDetails().getBalloonAmount());
        requestDetails.setLoanDurationMonths(loanDetails.getSetupDetails().getLoanDurationMonths());
        requestDetails.setDeposit(loanDetails.getSetupDetails().getDeposit());
        requestDetails.setAnnualInterestPcnt(loanDetails.getSetupDetails().getAnnualInterestPcnt());

        response.setScheduleRequestDetails(requestDetails);

        for(ScheduleItem item : loanDetails.getScheduleItems()) {
            tech.danieljones.loansapiexample.loans_api_example.model.ScheduleItem scheduleDetailsItem = new tech.danieljones.loansapiexample.loans_api_example.model.ScheduleItem();

            scheduleDetailsItem.setPeriod((int) item.getPeriod());
            scheduleDetailsItem.setBalance(item.getBalance());
            scheduleDetailsItem.setInterest(item.getInterest());
            scheduleDetailsItem.setPayment(item.getPayment());
            scheduleDetailsItem.setPriciple(item.getPrinciple());

            response.addScheduleDetailsItem(scheduleDetailsItem);
        }

        return response;
    }


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
