package com.goeuro.view.cl;

import com.goeuro.model.Location;
import com.goeuro.service.LocationDiskSerializer;
import com.goeuro.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tckb
 *         Created on 08/05/16.
 *         <p>
 *         The commands that can be used to interact on the REPL console.
 */

@Component
public class GoEuroCLCommands implements CommandMarker {

    @Autowired
    LocationService myLocationService;

    @Autowired
    LocationDiskSerializer diskSerializer;

    /**
     * A simple greet message
     *
     * @return
     */
    @CliCommand(value = "greet", help = "Print a simple hello world message")
    public String greet() {
        return " Hello stranger!";
    }

    /**
     * Command for search for location
     *
     * @param location search parameter
     * @param diskFile Optional parameter where the result has to be stored
     * @return a "string" version of the location result
     */
    @CliCommand(value = "search", help = "Search for the location using the location service")
    public String searchLocation(
            @CliOption(key = {"location"}, mandatory = true, help = "city  or location name") final String location,
            @CliOption(key = {"save"}, help = "save the result to disk", specifiedDefaultValue = "out.csv") String diskFile) {
        final List<Location> matchedLocations = myLocationService.getMatchedLocations(location);

        if (matchedLocations.isEmpty()) {
            return "(No results found for the location)";
        } else {

            String locations = printLocations(matchedLocations);

            if (diskFile != null && !diskFile.isEmpty()) {
                try {
                    diskSerializer.serializeToDisk(matchedLocations, diskFile);
                    locations += "\n [ Saving result to file: " + diskFile + " ]";

                } catch (Exception e) {

                    locations += "\n [ Saving result to file failed, ]" + e.getMessage();
                }
            }
            return locations;
        }


    }


    private String printLocations(List<Location> listOfLocations) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\nId").append("\t\t").append("Name").append("[").append("Type").append("]\n");
        stringBuilder.append("====================================================").append("\n");

        listOfLocations.forEach(location -> stringBuilder.append(location.get_id()).append("\t\t").append(location.getFullName()).append("[").append(location.getType()).append("]\n"));
        return stringBuilder.toString();
    }

}
