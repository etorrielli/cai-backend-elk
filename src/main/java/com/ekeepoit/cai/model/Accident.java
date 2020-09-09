package com.ekeepoit.cai.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Accident {

    @Id
    public String id;
    @Field("Start_Time")
    public String startTime;
    @Field("Description")
    public String description;

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

}
