package com.ekeepoit.cai.dto;

import com.ekeepoit.cai.model.Accident;

public class AccidentDTO {

    public String id;
    public String startTime;
    public String endTime;
    public String description;
    private Float startLat;
    private Float startLng;
    private Float endLat;
    private Float endLng;


    public AccidentDTO(Accident anAccident) {
        this.setId(anAccident.getId());
        this.setStartTime(anAccident.getStartTime());
        this.setDescription(anAccident.getDescription());
        this.setStartLat(anAccident.getStartLat());
        this.setEndLat(anAccident.getEndLat());
        this.setStartLng(anAccident.getEndLng());
        this.setEndLng(anAccident.getEndLng());
    }

    public AccidentDTO(String id, String startTime, String description, Float startLat, Float startLng, Float endLat, Float endLng) {
        this.id = id;
        this.startTime = startTime;
        this.description = description;
        this.startLat = startLat;
        this.startLng = startLng;
        this.endLat = endLat;
        this.endLng = endLng;
    }

    public static AccidentDTO factory(String id, String startTime, String description, Float startLat, Float startLng, Float endLat, Float endLng) {
        return new AccidentDTO(id, startTime, description, startLat, startLng, endLat, endLng);
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String anId) {
        this.id = anId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String aDescription) {
        this.description = aDescription;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Float getStartLat() {
        return startLat;
    }

    public void setStartLat(Float startLat) {
        this.startLat = startLat;
    }

    public Float getStartLng() {
        return startLng;
    }

    public void setStartLng(Float startLng) {
        this.startLng = startLng;
    }

    public Float getEndLat() {
        return endLat;
    }

    public void setEndLat(Float endLat) {
        this.endLat = endLat;
    }

    public Float getEndLng() {
        return endLng;
    }

    public void setEndLng(Float endLng) {
        this.endLng = endLng;
    }
}
