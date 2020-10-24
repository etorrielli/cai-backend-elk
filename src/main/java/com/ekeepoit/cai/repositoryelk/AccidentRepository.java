package com.ekeepoit.cai.repositoryelk;

import com.ekeepoit.cai.model.Accident;
import com.ekeepoit.cai.repository.AccidentRepositoryCustom;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccidentRepository extends ElasticsearchRepository<Accident, String>, AccidentRepositoryCustom {

    List<Accident> findByStartTimeBetween(String dateFrom, String dateTo);



}
