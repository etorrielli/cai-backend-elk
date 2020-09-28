package com.ekeepoit.cai.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.ekeepoit.cai.dto.TopStatesDTO;
import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ekeepoit.cai.dto.AccidentDTO;
import com.ekeepoit.cai.model.Accident;
import com.ekeepoit.cai.repository.AccidentRepository;
import com.ekeepoit.cai.services.IAccidentService;

import static com.mongodb.client.model.Aggregates.*;

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

        long antes = new Date().getTime();
        this.getAccidentRepository().findByStartTimeBetween(dateFrom, dateTo).stream().map(a -> new AccidentDTO(a))
                .collect(Collectors.toCollection(() -> result));
        long despues = new Date().getTime();
        LOGGER.info("Tiempo getAccidentsByDates(): " + (despues - antes) + " milisegundos");

        return result;
    }

    @Override
    public Collection<TopStatesDTO> getTopStates() {
        Collection<TopStatesDTO> result = new ArrayList<TopStatesDTO>();

        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("accidentdb");
        MongoCollection<Document> collection = database.getCollection("accident");

        long antes = new Date().getTime();
        MongoCursor<Document> totalByStateList = collection.aggregate(Arrays.asList(
                group("$State", Accumulators.sum("total", 1)),
                sort(Sorts.descending("total")),
                limit(5))).iterator();
        long despues = new Date().getTime();
        LOGGER.info("Tiempo getTopStates(): " + (despues - antes) + " milisegundos");

        totalByStateList.forEachRemaining(item -> {
            TopStatesDTO topStatesDTO = new TopStatesDTO((String) item.get("_id"), (Integer) item.get("total"));
            result.add(topStatesDTO);
        });

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
