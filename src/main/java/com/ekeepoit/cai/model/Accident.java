package com.ekeepoit.cai.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
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
    @Field("End_Lat")
    private Float endLat;
    @Field("Start_Lng")
    private Float startLng;
    @Field("End_Lng")
    private Float endLng;


    public Accident(@Value("startTime") String aStartTime, @Value("description") String aDescription) {
        this.setStartTime(aStartTime);
        this.setDescription(aDescription);
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
}
