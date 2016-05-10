package com.goeuro.service;

import com.goeuro.model.Location;

import java.util.List;

/**
 * @author tckb
 *         Created on 09/05/16.
 *         <p>
 *         A Utility class for serializing {@link Location}
 */
public interface LocationDiskSerializer {

    String DIR_TO_SAVE = System.getProperty("user.home") + System.getProperty("file.separator");

    /**
     * @param listOfLocations
     * @param fileName
     * @throws Exception
     */
    void serializeToDisk(List<Location> listOfLocations, String fileName) throws Exception;

    String serializeToString(List<Location> listOfLocations);

}
