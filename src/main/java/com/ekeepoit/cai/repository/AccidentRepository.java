package com.ekeepoit.cai.repository;

import com.ekeepoit.cai.model.Accident;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccidentRepository extends ElasticsearchRepository<Accident, String>, AccidentRepositoryCustom {

    List<Accident> findByStartTimeBetween(String dateFrom, String dateTo);


}
