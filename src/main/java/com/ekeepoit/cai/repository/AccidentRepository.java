package com.ekeepoit.cai.repository;

import com.ekeepoit.cai.model.Accident;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface AccidentRepository extends ElasticsearchRepository<Accident, String>, AccidentRepositoryCustom {

    List<Accident> findByStartTimeBetween(String dateFrom, String dateTo);

    @Query("{\"bool\":{\"must\":{\"match_all\":{}},\"filter\":{\"geo_distance\":{\"distance\":\"?1km\",\"start_location\":\"?0\"}}}}")
    List<Accident> findAccidentsByRadius(String coordinates, float radius);
}
