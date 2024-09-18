package com.appfellas.flightApi.core.dao.mapper;

public interface BaseMapper<E, I> {

    E createEntity(I input);

    E updateEntity(E entity, I input);
}
