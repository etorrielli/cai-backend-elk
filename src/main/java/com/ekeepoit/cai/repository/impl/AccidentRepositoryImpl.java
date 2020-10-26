package com.ekeepoit.cai.repository.impl;

import com.ekeepoit.cai.dto.TopDangerousPointsDTO;
import com.ekeepoit.cai.model.Accident;
import com.ekeepoit.cai.model.TopStates;
import com.ekeepoit.cai.repository.AccidentRepository;
import com.ekeepoit.cai.repository.AccidentRepositoryCustom;
import com.ekeepoit.cai.repository.TopDangerousPointsRepository;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.NumericMetricsAggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHitsIterator;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.*;
import java.util.stream.Collectors;

public class AccidentRepositoryImpl implements AccidentRepositoryCustom {

    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    AccidentRepository accidentRepository;
    @Autowired
    TopDangerousPointsRepository topDangerousPointsRepository;

    @Autowired
    public AccidentRepositoryImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public Collection<TopStates> findTopStates() {
        List<TopStates> topStatesList = new ArrayList<>();
        final TermsAggregationBuilder state = AggregationBuilders.terms("state").field("State.keyword").size(10);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .addAggregation(state)
                .build();

        SearchHits<Accident> searchTop = elasticsearchOperations.search(searchQuery, Accident.class, IndexCoordinates.of("accidentdb"));
        final ParsedStringTerms bucketTop = (ParsedStringTerms) searchTop.getAggregations().asMap().get("state");
        bucketTop.getBuckets().forEach(item -> {
            topStatesList.add(new TopStates(item.getKeyAsString(), (int) item.getDocCount()));
        });
        return topStatesList;
    }

    @Override
    public Collection<TopDangerousPointsDTO> findTopDangerousPoints(float radiusKm) {

        List<TopDangerousPointsDTO> topDangerousPointsDTOList = topDangerousPointsRepository.findTop10000By().map(acc -> {
            String coordinates = acc.getStartLat() + "," + acc.getStartLng();
            float count = accidentRepository.findAccidentsByRadius(coordinates, radiusKm).size();
            return TopDangerousPointsDTO.factory(acc.getStartLng(), acc.getStartLat(), (int) count);
        }).collect(Collectors.toCollection(ArrayList::new));

        topDangerousPointsDTOList.sort(Comparator.comparing(TopDangerousPointsDTO::getTotal).reversed());
        topDangerousPointsDTOList = topDangerousPointsDTOList.subList(0, 10);

        return topDangerousPointsDTOList;
    }

    @Override
    public Float findAvgDistance() {
        Float avgDistance = null;

        AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders.avg("avg_distance").field("Distance(mi)");
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .addAggregation(avgAggregationBuilder)
                .build();

        SearchHitsIterator<Accident> stream = elasticsearchOperations.searchForStream(searchQuery, Accident.class, IndexCoordinates.of("accidentdb"));
        Aggregation aggregation = stream.getAggregations().iterator().next();

        if (aggregation instanceof NumericMetricsAggregation.SingleValue) {
            final NumericMetricsAggregation.SingleValue numericProperty = (NumericMetricsAggregation
                    .SingleValue) aggregation;
            avgDistance = (float) numericProperty.value();
        }
        return avgDistance;
    }

}
