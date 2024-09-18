package com.appfellas.flightApi.service.flight.service.mapper;

import com.appfellas.flightApi.core.dao.mapper.BaseCreateMapper;
import com.appfellas.flightApi.service.flight.dto.AirCraftTypeInput;
import com.appfellas.flightApi.service.flight.entity.embeddable.AirCraftType;
import org.springframework.stereotype.Component;

@Component
public class AirCraftTypeMapper implements BaseCreateMapper<AirCraftType, AirCraftTypeInput> {

    @Override
    public AirCraftType createEntity(AirCraftTypeInput input) {
        AirCraftType entity = new AirCraftType();
        entity.setIataMain(input.getIataMain());
        entity.setIataSub(input.getIataSub());
        return entity;
    }
}
