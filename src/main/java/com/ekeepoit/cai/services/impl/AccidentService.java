package com.ekeepoit.cai.services.impl;

import com.ekeepoit.cai.dto.AccidentDTO;
import com.ekeepoit.cai.dto.TopDangerousPointsDTO;
import com.ekeepoit.cai.dto.TopStatesDTO;
import com.ekeepoit.cai.mapper.AccidentMapper;
import com.ekeepoit.cai.mapper.TopStatesMapper;
import com.ekeepoit.cai.model.Accident;
import com.ekeepoit.cai.repository.AccidentRepository;
import com.ekeepoit.cai.services.IAccidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@Transactional
public class AccidentService implements IAccidentService {

    static final Logger LOGGER = LoggerFactory.getLogger(AccidentService.class);
    @Inject
    public AccidentRepository accidentRepository;

    @Override
    public Collection<AccidentDTO> getAccidents() {
        Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();
        return result;
    }

    @Override
    public AccidentDTO getOneById(String id) {
        Accident accident = this.getAccidentRepository().findById(id).get();
        return new AccidentDTO(accident);
    }

    @Override
    public Collection<AccidentDTO> getAccidentsByDates(String dateFrom, String dateTo) {
        Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();

        long antes = new Date().getTime();
        this.getAccidentRepository().findByStartTimeBetween(dateFrom, dateTo).stream().map(a -> new AccidentDTO(a))
                .collect(Collectors.toCollection(() -> result));
        long despues = new Date().getTime();
        LOGGER.info("ELK - Tiempo getAccidentsByDates(): " + (despues - antes) + " milisegundos");

        return result;
    }

    @Override
    public Collection<AccidentDTO> getAccidentsByDatesElk(String dateFrom, String dateTo) {
        Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();

        long antes = new Date().getTime();
        this.getAccidentRepository().findByStartTimeBetween(dateFrom, dateTo).stream().map(a -> new AccidentDTO(a))
                .collect(Collectors.toCollection(() -> result));
        long despues = new Date().getTime();
        LOGGER.info("ELK - Tiempo getAccidentsByDates(): " + (despues - antes) + " milisegundos");

        return result;
    }

    @Override
    public Collection<TopStatesDTO> getTopStates() {
        Collection<TopStatesDTO> result = new ArrayList<TopStatesDTO>();

        long antes = new Date().getTime();
        result = this.getAccidentRepository().findTopStates().stream().map(TopStatesMapper::dataCoreMapper).collect(Collectors.toCollection(ArrayList::new));
        long despues = new Date().getTime();
        LOGGER.info("ELK - Tiempo getTopStates(): " + (despues - antes) + " milisegundos");

        return result;
    }

    @Override
    public Collection<AccidentDTO> getAccidentsByRadius(float lng, float lat, float radiusKm) {
        Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();

        long antes = new Date().getTime();
        String coordinates = lat + "," + lng;
        result = this.getAccidentRepository().findAccidentsByRadius(coordinates, radiusKm).stream().map(AccidentMapper::dataCoreMapper).collect(Collectors.toCollection(ArrayList::new));
        long despues = new Date().getTime();
        LOGGER.info("ELK - Tiempo getAccidentsByRadius(): " + (despues - antes) + " milisegundos");

        return result;
    }

    @Override
    public Collection<TopDangerousPointsDTO> getTopDangerousPoints(float radiusKm) {
        Collection<TopDangerousPointsDTO> result = new ArrayList<TopDangerousPointsDTO>();

        long antes = new Date().getTime();
        // result = this.getAccidentRepository().findTopDangerousPoints(radiusKm);
        long despues = new Date().getTime();
        LOGGER.info("ELK - Tiempo getTopDangerousPoints(): " + (despues - antes) + " milisegundos");

        return result;
    }

    @Override
    public Float getAvgDistance() {
        Float avgDistance = 0f;

        long antes = new Date().getTime();
        // avgDistance = this.getAccidentRepository().findAvgDistance();
        long despues = new Date().getTime();
        LOGGER.info("ELK - Tiempo getAvgDistance(): " + (despues - antes) + " milisegundos");

        return avgDistance;
    }

    @Override
    public void saveAccident(String aReason) {
        Accident newAccident = new Accident("", aReason);
        this.getAccidentRepository().save(newAccident);

    }

    public AccidentRepository getAccidentRepository() {
        return this.accidentRepository;
    }

    public void setAccidentRepository(AccidentRepository aRepository) {
        this.accidentRepository = aRepository;
    }

}
