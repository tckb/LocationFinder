package com.goeuro.cmdutil;

import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Main entrypoint to the Location finder
 *
 * @author tckb &lt; chandra[dot]tungathurthi[at]rwth-aachen[dot]de &gt;
 */
public class LocationFinder {

    // the default message
    private final static String defaultMessage = "\nGoEuro Location Finder\n"
            + "************************\n\n"
            + "Usage::\n"
            + "\t java -jar <thisJarFile> <city> [ outputFile [-v] ]\n"
            + "Optional args::\n"
            + "\t outputFile: /path/to/Ouputfile.csv (default: /path/to/home/<city>_locations.csv)\n"
            + "\t -v : Verbous output, displays raw json file\n";
    private static RemoteLocationService.GoEuroRemoteIntf remoteService = null;
    private static String outputFile = System.getProperty("user.home") + System.getProperty("file.separator");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String city = null;
        // retrofit log level 
        LogLevel logLevel = LogLevel.NONE;

        // check input parameters
        switch (args.length) {
            case 1:

                city = args[0];
                outputFile += city + "_locations.csv";
                break;
            case 2:

                city = args[0];
                outputFile = args[1];

                break;
            case 3:

                city = args[0];
                outputFile = args[1];

                if ("-v".equalsIgnoreCase(args[2])) {
                    logLevel = LogLevel.FULL;
                    break;
                }

            default:
                System.out.println("<< Invalid args! >>");
                printDefaultMessage();
                // send status error status 1
                System.exit(1);
        }

        // Configure retrofit for GoEuro remote service
        remoteService = RemoteLocationService.getInstance(logLevel).getGEServiceImpl();

        try {

            // Make REST call using the hookup methods in the interface
            List<Location> resultLocList = remoteService.getRemoteLocation(city);

            System.out.println("Locations founds for : " + city);
            System.out.println("********************************");
            if (resultLocList.size() > 0) {
                saveResultLocation(resultLocList, outputFile);
                System.out.println("\nCSV File saved at " + new File(outputFile).getAbsolutePath());

            } else {
                System.out.println("(None)");

            }

        } catch (RetrofitError ex) {

            System.out.println("Error: Can't connect to the remote service, cowardly quitting! ");
            System.out.println("Endppoint: " + RemoteLocationService.geServiceEndPoint);
            System.out.println("Cause: " + ex.getMessage());

        } catch (IOException ex) {
            System.out.println("Error: Writing result to disk! ");
            System.out.println("Cause: " + ex.getMessage());
        }
    }

    /**
     * Displays the default message
     */
    private static void printDefaultMessage() {
        System.out.println(defaultMessage);
        System.out.println("-----");
    }

    /**
     * Marshall the output locations to a file
     *
     * @param resultLoc
     * @param outputFile
     */
    private static void saveResultLocation(List<Location> locationList, String outputFile) throws IOException {

        StringBuilder resStringBuilder = new StringBuilder();

        resStringBuilder.append("ID").append(",").append("Name")
                .append(",").append("Type").append(",")
                .append("Latitude").append(",")
                .append("Longitude").append("\n");
        // save the output locations to file
        for (Location loc : locationList) {
            resStringBuilder.append(loc.get_Id()).append(",").append(loc.getName())
                    .append(",").append(loc.getType()).append(",")
                    .append(loc.getGeo_position().getLatitude()).append(",")
                    .append(loc.getGeo_position().getLongitude()).append("\n");

            // debug
            System.out.println(loc.getFullName() + " [ " + loc.getType() + " ]");
        }

        // save the data to disk
        FileWriter fileWriter = new FileWriter(new File(outputFile));
        fileWriter.write(resStringBuilder.toString());
        // close the resouce
        fileWriter.close();

    }

}
