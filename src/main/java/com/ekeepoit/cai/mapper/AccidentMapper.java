package com.ekeepoit.cai.mapper;

import com.ekeepoit.cai.dto.AccidentDTO;
import com.ekeepoit.cai.dto.TopStatesDTO;
import com.ekeepoit.cai.model.Accident;
import com.ekeepoit.cai.model.TopStates;

public class AccidentMapper {

    public static AccidentDTO dataCoreMapper(Accident accident) {
        return AccidentDTO.factory(accident.getId(), accident.getStartTime(), accident.getDescription(), accident.getStartLat(), accident.getStartLng(), accident.getEndLat(), accident.getEndLng());
    }
}
