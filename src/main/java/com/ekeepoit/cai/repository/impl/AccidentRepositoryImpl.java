package com.ekeepoit.cai.repository.impl;

import com.ekeepoit.cai.dto.TopDangerousPointsDTO;
import com.ekeepoit.cai.model.Accident;
import com.ekeepoit.cai.model.AverageDistance;
import com.ekeepoit.cai.model.TopStates;
import com.ekeepoit.cai.repository.AccidentRepositoryCustom;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.NumericMetricsAggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHitsIterator;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

public class AccidentRepositoryImpl implements AccidentRepositoryCustom {

    private ElasticsearchOperations elasticsearchOperations;

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

//    @Override
//    public Collection<Accident> findAccidentsByRadius(float lng, float lat, float radiusKm) {
//        String coordinates = lat + "," + lng;
//
//        Stream<Accident> retrieve = accidentsInRadiusCRUD.findInRadius(coordinates, radiusKm);
//        return retrieve.map(AccidentDataMapper::dataCoreMapper).collect(Collectors.toCollection(ArrayList::new));
//    }

    @Override
    public Collection<TopDangerousPointsDTO> findTopDangerousPoints(float radiusKm) {
        return null;
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
