package com.appfellas.flightApi.service.flight.service.mapper;

import com.appfellas.flightApi.core.dao.mapper.BaseCreateMapper;
import com.appfellas.flightApi.service.flight.dto.input.FlightRouteInput;
import com.appfellas.flightApi.service.flight.entity.embeddable.FlightRoute;
import org.springframework.stereotype.Component;

@Component
public class FlightRouteMapper implements BaseCreateMapper<FlightRoute, FlightRouteInput> {

    @Override
    public FlightRoute createEntity(FlightRouteInput input) {
        FlightRoute entity = new FlightRoute();
        entity.setArrivalIATACode(input.getArrivalIATACode());
        entity.setDepartureIATACode(input.getDepartureIATACode());
        entity.setEu(input.getEu());
        entity.setVisa(input.getVisa());
        return entity;
    }
}
