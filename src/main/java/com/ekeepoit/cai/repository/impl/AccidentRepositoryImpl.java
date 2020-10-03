package com.ekeepoit.cai.repository.impl;

import com.ekeepoit.cai.dto.AccidentDTO;
import com.ekeepoit.cai.dto.TopStatesDTO;
import com.ekeepoit.cai.mapper.TopStatesMapper;
import com.ekeepoit.cai.model.Accident;
import com.ekeepoit.cai.model.TopStates;
import com.ekeepoit.cai.repository.AccidentRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class AccidentRepositoryImpl implements AccidentRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AccidentRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Collection<TopStates> findTopStates() {
        MatchOperation matchOperation = match(new Criteria("State").ne(null));
        GroupOperation groupOperation = group("State").count().as("total");
        SortOperation sortOperation = sort(Sort.Direction.DESC, "total");
        LimitOperation limitTopTen = limit(10);
        Aggregation aggregation = newAggregation(matchOperation, groupOperation, sortOperation, limitTopTen);
        AggregationResults<TopStates> result = mongoTemplate.aggregate(aggregation, "accident", TopStates.class);
        return result.getMappedResults().stream().collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Collection<Accident> findAccidentsByRadius(float lng, float lat, float radiusKm) {
        BasicQuery basicQuery = new BasicQuery("{start_location: { $geoWithin: { $centerSphere: [ [" + lng + "," + lat + "], " + radiusKm / 6371 + "] }}})");
        List<Accident> result = mongoTemplate.find(basicQuery, Accident.class);
        return result;
    }


}
