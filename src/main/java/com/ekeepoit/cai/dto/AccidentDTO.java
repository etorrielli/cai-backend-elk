package com.ekeepoit.cai.dto;

import com.ekeepoit.cai.model.Accident;

public class AccidentDTO {

    public String id;
    public String startTime;
    public String description;

    public AccidentDTO(Accident anAccident) {
        this.setId(anAccident.getId());
        this.setStartTime(anAccident.getStartTime());
        this.setDescription(anAccident.getDescription());
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

}
