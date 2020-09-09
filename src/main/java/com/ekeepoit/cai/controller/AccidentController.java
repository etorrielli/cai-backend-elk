package com.ekeepoit.cai.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ekeepoit.cai.dto.AccidentDTO;
import com.ekeepoit.cai.dto.AccidentRequestDTO;
import com.ekeepoit.cai.services.IAccidentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/accident")
public class AccidentController {

    @Inject
    private IAccidentService accidentsService;

    @GetMapping()
    public ResponseEntity<?> getAccidents() {

        ResponseEntity<?> response = null;
        Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();

        result.addAll(this.getAccidentsService().getAccidents());

        response = ResponseEntity.ok(result);

        return response;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAccidents(@PathVariable("id") String id) {

        ResponseEntity<?> response = null;
        AccidentDTO result = this.getAccidentsService().getOneById(id);

        response = ResponseEntity.ok(result);

        return response;
    }

    @GetMapping(value = "/datefrom/{dateFrom}/dateto/{dateTo}")
    public ResponseEntity<?> getAccidentsByDates(@PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo) {

        ResponseEntity<?> response = null;
		Collection<AccidentDTO> result = this.getAccidentsService().getAccidentsByDates(dateFrom, dateTo);

        response = ResponseEntity.ok(result);

        return response;
    }

    @PostMapping(value = "/api/accident")
    public ResponseEntity<?> saveAccidents(@RequestBody AccidentRequestDTO request) {

        ResponseEntity<?> response = null;

        this.getAccidentsService().saveAccident(request.getDescription());

        response = ResponseEntity.ok().build();

        return response;
    }

    public IAccidentService getAccidentsService() {
        return this.accidentsService;
    }

}
