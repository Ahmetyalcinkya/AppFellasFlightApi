package com.appfellas.flightApi.service.airport.service.mapper;

import com.appfellas.flightApi.core.dao.mapper.BaseCreateMapper;
import com.appfellas.flightApi.service.airport.dto.input.AddressInput;
import com.appfellas.flightApi.service.airport.entity.embeddable.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements BaseCreateMapper<Address, AddressInput> {

    @Override
    public Address createEntity(AddressInput input) {
        Address address = new Address();
        address.setCity(input.getCity());
        address.setCountry(input.getCountry());
        address.setCountryCode(input.getCountryCode());
        return address;
    }
}
