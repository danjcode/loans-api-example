package tech.danieljones.loansapiexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tech.danieljones.loansapiexample.loans_api_example.api.SchedulesApi;
import tech.danieljones.loansapiexample.loans_api_example.model.ScheduleListResponse;
import tech.danieljones.loansapiexample.loans_api_example.model.SchedulePostBody;
import tech.danieljones.loansapiexample.loans_api_example.model.ScheduleResponse;
import tech.danieljones.loansapiexample.mapping.Mapper;
import tech.danieljones.loansapiexample.persistance.model.LoanDetails;
import tech.danieljones.loansapiexample.persistance.service.LoanDetailsService;

import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@RestController
public class ScheduleApiController implements SchedulesApi {

    private final LoanDetailsService loanDetailsService;
    private final Mapper mapper;

    @Autowired
    public ScheduleApiController(LoanDetailsService loanDetailsService, Mapper mapper) {
        this.loanDetailsService = loanDetailsService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ScheduleListResponse> getSchedulesList() {
        List<LoanDetails> loanDetailsList= loanDetailsService.getAllSchedules();
        ScheduleListResponse response = new ScheduleListResponse();

        for(LoanDetails loanDetails : loanDetailsList) {
            response.addSchedulesItem(mapper.loanDetails2ScheduleListResponseItem(loanDetails));
        }

        return ResponseEntity.ok(new ScheduleListResponse());
    }

    @Override
    public ResponseEntity<Void> addNewSchedule(@Valid SchedulePostBody schedulePostBody) {
        LoanDetails loanDetails = mapper.mapNewLoanDetails(schedulePostBody);

        loanDetailsService.saveLoanDetails(loanDetails);

        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<ScheduleResponse> getSchedule(Integer id) {
        Optional<LoanDetails> loanDetails = loanDetailsService.getLoanDetails(id);

        if (loanDetails.isEmpty()) {
            throw new ScheduleNotFoundException(MessageFormat.format("Schedule with id {0} not found", id));
        }


        return null;
    }
}
