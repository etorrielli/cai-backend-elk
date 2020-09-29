package com.ekeepoit.cai.mapper;

import com.ekeepoit.cai.dto.TopStatesDTO;
import com.ekeepoit.cai.model.TopStates;

public class TopStatesMapper {

    public static TopStatesDTO dataCoreMapper(TopStates states) {
        return TopStatesDTO.factory(states.getState(), states.getTotal());
    }
}
