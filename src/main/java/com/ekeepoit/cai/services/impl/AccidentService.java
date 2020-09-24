package com.ekeepoit.cai.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ekeepoit.cai.dto.AccidentDTO;
import com.ekeepoit.cai.model.Accident;
import com.ekeepoit.cai.repository.AccidentRepository;
import com.ekeepoit.cai.services.IAccidentService;

@Service
@Transactional
public class AccidentService implements IAccidentService {

    static final Logger LOGGER = LoggerFactory.getLogger(AccidentService.class);
    @Inject
    public AccidentRepository accidentRepository;

    @Override
    public Collection<AccidentDTO> getAccidents() {
        Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();
        this.getAccidentRepository().findAll().stream().map(a -> new AccidentDTO(a))
                .collect(Collectors.toCollection(() -> result));
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
        LOGGER.info("-----getAccidentsByDates inicia query-----");
        this.getAccidentRepository().findByStartTimeBetween(dateFrom, dateTo).stream().map(a -> new AccidentDTO(a))
                .collect(Collectors.toCollection(() -> result));
        LOGGER.info("-----getAccidentsByDates fin query-----");
        return result;
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
