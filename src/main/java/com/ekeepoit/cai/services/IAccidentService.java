package com.ekeepoit.cai.services;

import java.util.Collection;
import java.util.Date;

import com.ekeepoit.cai.dto.AccidentDTO;

public interface IAccidentService {

    public Collection<AccidentDTO> getAccidents();

    public AccidentDTO getOneById(String id);

    public Collection<AccidentDTO> getAccidentsByDates(String dateFrom, String dateTo);

    public void saveAccident(String reason);

}
