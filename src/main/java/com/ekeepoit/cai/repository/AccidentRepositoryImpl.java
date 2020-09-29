package com.ekeepoit.cai.repository;

import com.ekeepoit.cai.dto.TopStatesDTO;
import com.ekeepoit.cai.mapper.TopStatesMapper;
import com.ekeepoit.cai.model.TopStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class AccidentRepositoryImpl implements AccidentRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AccidentRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Collection<TopStatesDTO> findTopStates() {
        MatchOperation matchOperation = match(new Criteria("State").ne(null));
        GroupOperation groupOperation = group("State").count().as("total");
        SortOperation sortOperation = sort(Sort.Direction.DESC, "total");
        LimitOperation limitTopTen = limit(10);
        Aggregation aggregation = newAggregation(matchOperation, groupOperation, sortOperation, limitTopTen);
        AggregationResults<TopStates> result = mongoTemplate.aggregate(aggregation, "accident", TopStates.class);
        return result.getMappedResults().stream().map(TopStatesMapper::dataCoreMapper).collect(Collectors.toCollection(ArrayList::new));
    }
}
