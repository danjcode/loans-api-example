package tech.danieljones.loansapiexample.persistance.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class LoanDetails {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private SetupDetails setupDetails;
    @OneToMany(mappedBy = "loanDetails")
    private List<ScheduleItem> scheduleItems;
    private double totalPaymentsDue;
    private double totalInterestDue;
    private double monthlyRepayment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SetupDetails getSetupDetails() {
        return setupDetails;
    }

    public void setSetupDetails(SetupDetails setupDetails) {
        this.setupDetails = setupDetails;
    }

    public List<ScheduleItem> getScheduleItems() {
        return scheduleItems;
    }

    public void setScheduleItems(List<ScheduleItem> scheduleItems) {
        this.scheduleItems = scheduleItems;
    }

    public double getTotalPaymentsDue() {
        return totalPaymentsDue;
    }

    public void setTotalPaymentsDue(double totalPaymentsDue) {
        this.totalPaymentsDue = totalPaymentsDue;
    }

    public double getTotalInterestDue() {
        return totalInterestDue;
    }

    public void setTotalInterestDue(double totalInterestDue) {
        this.totalInterestDue = totalInterestDue;
    }

    public double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public void setMonthlyRepayment(double monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
    }
}
