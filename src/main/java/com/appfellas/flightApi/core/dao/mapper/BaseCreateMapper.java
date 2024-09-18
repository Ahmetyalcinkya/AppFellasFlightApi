package com.appfellas.flightApi.core.dao.mapper;

public interface BaseCreateMapper<E, I> {

    E createEntity(I input);
}
