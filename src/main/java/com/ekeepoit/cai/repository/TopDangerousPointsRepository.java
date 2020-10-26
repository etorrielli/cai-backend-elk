package com.ekeepoit.cai.repository;

import com.ekeepoit.cai.model.Accident;
import com.ekeepoit.cai.model.TopDangerousPoints;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface TopDangerousPointsRepository extends ElasticsearchRepository<TopDangerousPoints, String> {

    Stream<TopDangerousPoints> findTop10000By();

    Stream<TopDangerousPoints> findAllBy();
}
