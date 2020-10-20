package com.ekeepoit.cai.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import com.ekeepoit.cai.dto.TopDangerousPointsDTO;
import com.ekeepoit.cai.dto.TopStatesDTO;
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

    @GetMapping(value = "/top-states")
    public ResponseEntity<?> getTopStates() {

        ResponseEntity<?> response = null;
        Collection<TopStatesDTO> result = this.getAccidentsService().getTopStates();

        response = ResponseEntity.ok(result);

        return response;
    }

    @GetMapping(value = "/lng/{lng}/lat/{lat}/radius/{radiusKm}")
    public ResponseEntity<?> getAccidentsByRadius(@PathVariable("lng") float lng, @PathVariable("lat") float lat, @PathVariable("radiusKm") float radiusKm) {

        ResponseEntity<?> response = null;
        Collection<AccidentDTO> result = this.getAccidentsService().getAccidentsByRadius(lng, lat, radiusKm);

        response = ResponseEntity.ok(result);

        return response;
    }

    @GetMapping(value = "/top-dangerous-points/{radiusKm}")
    public ResponseEntity<?> getTopDangerousPoints(@PathVariable("radiusKm") float radiusKm) {

        ResponseEntity<?> response = null;
        Collection<TopDangerousPointsDTO> result = this.getAccidentsService().getTopDangerousPoints(radiusKm);

        response = ResponseEntity.ok(result);

        return response;
    }

    @GetMapping(value = "/avg-distance")
    public ResponseEntity<?> getAvgDistance() {

        ResponseEntity<?> response = null;
        Float result = this.getAccidentsService().getAvgDistance();

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
