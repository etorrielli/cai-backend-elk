package com.ekeepoit.cai.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "accidentdb")
public class AverageDistance {
    @Field("distance")
    private Float distance;
    @Field("totalDistance")
    private Float totalDistance;
    @Field("avgDistance")
    private Float avgDistance;

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getAvgDistance() {
        return avgDistance;
    }

    public void setAvgDistance(Float avgDistance) {
        this.avgDistance = avgDistance;
    }
}
