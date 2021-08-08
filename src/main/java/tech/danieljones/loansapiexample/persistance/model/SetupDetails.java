package tech.danieljones.loansapiexample.persistance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SetupDetails {
    @Id
    @GeneratedValue
    private long id;
    private double assetValue;
    private double deposit;
    private double annualInterestPcnt;
    private int loanDurationMonths;
    private double balloonAmount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(double assetValue) {
        this.assetValue = assetValue;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getAnnualInterestPcnt() {
        return annualInterestPcnt;
    }

    public void setAnnualInterestPcnt(double annualInterestPcnt) {
        this.annualInterestPcnt = annualInterestPcnt;
    }

    public int getLoanDurationMonths() {
        return loanDurationMonths;
    }

    public void setLoanDurationMonths(int loanDurationMonths) {
        this.loanDurationMonths = loanDurationMonths;
    }

    public double getBalloonAmount() {
        return balloonAmount;
    }

    public void setBalloonAmount(double balloonAmount) {
        this.balloonAmount = balloonAmount;
    }
}
