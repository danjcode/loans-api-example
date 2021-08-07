package tech.danieljones.loansapiexample.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.danieljones.loansapiexample.persistance.model.LoanDetails;

public interface LoanDetailsRepo extends JpaRepository<LoanDetails, Long> {


}
