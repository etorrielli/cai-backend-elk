package com.ekeepoit.cai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TopDangerousPointsDTO {

    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("total")
    private Integer total;

    public TopDangerousPointsDTO() {
    }

    public TopDangerousPointsDTO(Double longitude, Double latitude, Integer total) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.total = total;
    }

    public static TopDangerousPointsDTO factory(Double longitude, Double latitude, Integer total) {
        return new TopDangerousPointsDTO(longitude, latitude, total);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "TopDangerousPointsDTO{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", total=" + total +
                '}';
    }
}
