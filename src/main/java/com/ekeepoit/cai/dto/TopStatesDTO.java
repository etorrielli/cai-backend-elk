package com.ekeepoit.cai.dto;

public class TopStatesDTO {

    public String id;
    public Integer total;

    public TopStatesDTO(String id, Integer total) {
        this.id = id;
        this.total = total;
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
