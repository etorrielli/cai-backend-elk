package com.ekeepoit.cai.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.geo.Point;

@Document(indexName = "accidentdb")
public class TopDangerousPoints {

    @Id
    private String id;
    @Field("Start_Lat")
    private Double startLat;
    @Field("Start_Lng")
    private Double startLng;
    @Field("total")
    private Integer total;

    public TopDangerousPoints() {
    }

    public Double getStartLat() {
        return startLat;
    }

    public void setStartLat(Double startLat) {
        this.startLat = startLat;
    }

    public Double getStartLng() {
        return startLng;
    }

    public void setStartLng(Double startLng) {
        this.startLng = startLng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
