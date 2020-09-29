package com.ekeepoit.cai.dto;

public class TopStatesDTO {

    public String state;
    public Integer total;

    public TopStatesDTO(String state, Integer total) {
        this.state = state;
        this.total = total;
    }

    public static TopStatesDTO factory(String state, Integer total) {
        return new TopStatesDTO(state, total);
    }

    public String getState() {
        return state;
    }

    public Integer getTotal() {
        return total;
    }
}
