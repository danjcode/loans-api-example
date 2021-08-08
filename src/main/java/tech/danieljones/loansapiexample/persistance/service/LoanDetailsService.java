package tech.danieljones.loansapiexample.persistance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.danieljones.loansapiexample.persistance.model.LoanDetails;
import tech.danieljones.loansapiexample.persistance.repository.LoanDetailsRepo;

import java.util.List;
import java.util.Optional;

@Component
public class LoanDetailsService {
    @Autowired
    LoanDetailsRepo loanDetailsRepo;

    public List<LoanDetails> getAllSchedules() {
        return loanDetailsRepo.findAll();
    }

    public Optional<LoanDetails> getLoanDetails(int id) {
        return loanDetailsRepo.findById(Long.valueOf(id));
    }

    public LoanDetails saveLoanDetails(LoanDetails loanDetails) {
        return loanDetailsRepo.saveAndFlush(loanDetails);
    }


}

