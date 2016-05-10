package com.goeuro.service.impl;

import com.goeuro.model.Location;
import com.goeuro.service.LocationService;
import com.goeuro.service.impl.GoEuroService.GoEuroApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tckb
 *         Created on 08/05/16.
 */

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    GoEuroApiService apiService;


    @Override
    public List<Location> getMatchedLocations(final String text) {
        return apiService.searchMatchedLocations(text);
    }


}
