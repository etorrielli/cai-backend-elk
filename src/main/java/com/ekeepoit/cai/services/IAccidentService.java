package com.ekeepoit.cai.services;

import java.util.Collection;
import java.util.Date;

import com.ekeepoit.cai.dto.AccidentDTO;
import com.ekeepoit.cai.dto.TopStatesDTO;
import org.bson.Document;

public interface IAccidentService {

    public Collection<AccidentDTO> getAccidents();

    public AccidentDTO getOneById(String id);

    public Collection<AccidentDTO> getAccidentsByDates(String dateFrom, String dateTo);

    public Collection<TopStatesDTO> getTopStates();

    public void saveAccident(String reason);

}
