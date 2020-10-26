package com.ekeepoit.cai.mapper;

import com.ekeepoit.cai.dto.TopDangerousPointsDTO;
import com.ekeepoit.cai.model.TopDangerousPoints;

public class TopDangerousPointsMapper {

    public static TopDangerousPointsDTO dataCoreMapper(TopDangerousPoints topDangerousPoints) {
        return TopDangerousPointsDTO.factory(topDangerousPoints.getStartLat(), topDangerousPoints.getStartLng(), topDangerousPoints.getTotal());
    }
}
