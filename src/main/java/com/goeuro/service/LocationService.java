package com.goeuro.service;

import com.goeuro.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tckb
 *         Created on 08/05/16.
 *         <p>
 *         Contract for fetching locations from remote
 */
@Service
public interface LocationService {

    List<Location> getMatchedLocations(String text);

}
