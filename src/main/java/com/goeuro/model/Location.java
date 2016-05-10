package com.goeuro.model;

import lombok.Data;

/**
 * @author tckb
 *         Created on 08/05/16.
 *         <p>
 *         Pojo representing the Location data
 */

@Data
public class Location {
    private long _id;
    private String key;
    private String name;
    private String fullName;
    private String iata_airport_code;
    private String type;
    private String country;
    private GeoPosition geo_position;
    private long location_id;
    private boolean inEurope;
    private String countryCode;
    private boolean codeCountry;
    private int distance;

    public static String getHeader() {
        return "Id" + "," +
                "Name" + "," +
                "Type" + "," +
                "Latitude" + "," +
                "Longitude" + ",";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder
                .append(get_id()).append(",")
                .append(getName()).append(",")
                .append(getType()).append(",");

        if (getGeo_position() != null) {
            builder.append(getGeo_position().getLatitude()).append(",")
                    .append(getGeo_position().getLongitude()).append(",");
        } else {
            builder.append(",,");
        }
        return builder.toString();
    }
}
