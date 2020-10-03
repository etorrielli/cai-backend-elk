package com.ekeepoit.cai.repository;

import com.ekeepoit.cai.dto.AccidentDTO;
import com.ekeepoit.cai.dto.TopStatesDTO;
import com.ekeepoit.cai.model.Accident;
import com.ekeepoit.cai.model.TopStates;

import java.util.Collection;

public interface AccidentRepositoryCustom {

    Collection<TopStates> findTopStates();

    Collection<Accident> findAccidentsByRadius(float lng, float lat, float radiusKm);
}
