package com.ekeepoit.cai.repository;

import com.ekeepoit.cai.dto.TopStatesDTO;

import java.util.Collection;

public interface AccidentRepositoryCustom {

    Collection<TopStatesDTO> findTopStates();
}
