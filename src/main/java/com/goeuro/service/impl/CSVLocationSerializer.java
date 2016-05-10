package com.goeuro.service.impl;

import com.goeuro.model.Location;
import com.goeuro.service.LocationDiskSerializer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author tckb
 *         Created on 09/05/16.
 *         <p>
 *         A Concrete Comma-seperated-value implementation of the Disk serializer
 */

@Service
public class CSVLocationSerializer implements LocationDiskSerializer {

    protected Logger log = Logger.getLogger(getClass().getName());

    private CSVLocationSerializer() {
        log.debug("Serializing date to directory: " + LocationDiskSerializer.DIR_TO_SAVE);
    }

    @Override
    public void serializeToDisk(final List<Location> listOfLocations, final String fileName) throws Exception {

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(LocationDiskSerializer.DIR_TO_SAVE + fileName), Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            bufferedWriter.append(serializeToString(listOfLocations));
        }
    }

    @Override
    public String serializeToString(List<Location> listOfLocations) {
        StringBuilder builder = new StringBuilder();
        builder.append(Location.getHeader()).append("\n");
        listOfLocations.forEach(location -> builder.append(location.toString()).append("\n"));
        return builder.toString();
    }
}
