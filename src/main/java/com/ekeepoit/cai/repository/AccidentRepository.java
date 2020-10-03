package com.ekeepoit.cai.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ekeepoit.cai.model.Accident;

import java.util.Date;
import java.util.List;

@Repository
public interface AccidentRepository extends MongoRepository<Accident, String> , AccidentRepositoryCustom {

    List<Accident> findByStartTimeBetween(String dateFrom, String dateTo);



}
