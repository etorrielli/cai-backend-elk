package com.ekeepoit.cai.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "accidentdb")
public class Accident {

    @Id
    private String id;
    @Field("Start_Time")
    private String startTime;
    @Field("Description")
    private String description;
    private String State;
    @Field("Start_Lat")
    private Float startLat;
    @Field("Start_Lng")
    private Float startLng;
    @Field("End_Lat")
    private Float endLat;
    @Field("End_Lng")
    private Float endLng;
    @Field("Distance(mi)")
    private Float distance;

    public Accident() {
    }

    public Accident(@Value("startTime") String aStartTime, @Value("description") String aDescription) {
        this.setStartTime(aStartTime);
        this.setDescription(aDescription);
    }

    public Accident(@Value("startTime") String aStartTime,
                    @Value("description") String aDescription,
                    @Value("startLat") Float aStartLat,
                    @Value("startLng") Float aStartLng,
                    @Value("endLat") Float aEndLat,
                    @Value("endLng") Float aEndLng) {
        this.setStartTime(aStartTime);
        this.setDescription(aDescription);
        this.setStartLat(aStartLat);
        this.setStartLng(aStartLng);
        this.setEndLat(aEndLat);
        this.setEndLng(aEndLng);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String anId) {
        this.id = anId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String aDescription) {
        this.description = aDescription;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public Float getStartLat() {
        return startLat;
    }

    public void setStartLat(Float startLat) {
        this.startLat = startLat;
    }

    public Float getEndLat() {
        return endLat;
    }

    public void setEndLat(Float endLat) {
        this.endLat = endLat;
    }

    public Float getStartLng() {
        return startLng;
    }

    public void setStartLng(Float startLng) {
        this.startLng = startLng;
    }

    public Float getEndLng() {
        return endLng;
    }

    public void setEndLng(Float endLng) {
        this.endLng = endLng;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }
}
