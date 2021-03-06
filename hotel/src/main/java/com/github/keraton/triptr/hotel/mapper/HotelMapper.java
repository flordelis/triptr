package com.github.keraton.triptr.hotel.mapper;

import com.github.keraton.triptr.hotel.model.Hotel;
import com.github.keraton.triptr.domain.model.HotelTrip;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    public HotelTrip map(Hotel hotel) {
        return new HotelTrip(hotel.getRefId(), hotel.getName(), hotel.getCity());
    }
}
