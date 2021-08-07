package tech.danieljones.loansapiexample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tech.danieljones.loansapiexample.loans_api_example.api.SchedulesApi;
import tech.danieljones.loansapiexample.loans_api_example.model.ScheduleListResponse;
import tech.danieljones.loansapiexample.persistance.repository.LoanDetailsRepo;

@RestController
public class ScheduleApiController implements SchedulesApi {

    @Override
    public ResponseEntity<ScheduleListResponse> getSchedulesList() {
        // map data
        return ResponseEntity.ok(new ScheduleListResponse());
    }
}
