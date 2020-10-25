package com.ekeepoit.cai.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.geo.Point;

@Document(indexName = "accidentdb")
public class TopDangerousPoints {

    @Id
    private String id;
    @Field("start_location")
    private Point startLocation;
    @Field("total")
    private Integer total;

    public TopDangerousPoints() {
    }

    public TopDangerousPoints(String id, Point startLocation, Integer total) {
        this.id = id;
        this.startLocation = startLocation;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Point getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Point startLocation) {
        this.startLocation = startLocation;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
