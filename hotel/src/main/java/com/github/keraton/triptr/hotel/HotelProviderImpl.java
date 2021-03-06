package com.github.keraton.triptr.hotel;

import com.github.keraton.triptr.domain.provider.HotelProvider;
import com.github.keraton.triptr.hotel.client.HotelProviderClient;
import com.github.keraton.triptr.hotel.mapper.HotelMapper;
import com.github.keraton.triptr.hotel.model.Hotel;
import com.github.keraton.triptr.domain.model.HotelTrip;
import com.github.keraton.triptr.note.NoteProvider;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class HotelProviderImpl implements HotelProvider {

    private final HotelProviderClient hotelProviderClient;
    private final NoteProvider noteProvider;
    private final HotelMapper hotelMapper;


    public HotelProviderImpl(HotelProviderClient hotelProviderClient,
                             NoteProvider noteProvider,
                             HotelMapper hotelMapper) {
        this.hotelProviderClient = hotelProviderClient;
        this.noteProvider = noteProvider;
        this.hotelMapper = hotelMapper;
    }

    @Override
    public List<HotelTrip> find(String city) {
        List<Hotel> hotels = hotelProviderClient.find(city);
        return hotels.stream()
                .map(hotelMapper::map)
                .map(hotelTrip -> {
                    hotelTrip.setNote(noteProvider.getNote(hotelTrip.getRefId()));
                    return hotelTrip;
                })
                .collect(toList());
    }
}
