package com.ekeepoit.cai.services;

import java.util.Collection;

import com.ekeepoit.cai.dto.AccidentDTO;
import com.ekeepoit.cai.dto.TopDangerousPointsDTO;
import com.ekeepoit.cai.dto.TopStatesDTO;

public interface IAccidentService {

    public Collection<AccidentDTO> getAccidents();

    public AccidentDTO getOneById(String id);

    public Collection<AccidentDTO> getAccidentsByDates(String dateFrom, String dateTo);

    public Collection<TopStatesDTO> getTopStates();

    public Collection<AccidentDTO> getAccidentsByRadius(float lng, float lat, float radiusKm);

    public Collection<TopDangerousPointsDTO> getTopDangerousPoints(float radiusKm);

    public Float getAvgDistance();

    public void saveAccident(String reason);

}
