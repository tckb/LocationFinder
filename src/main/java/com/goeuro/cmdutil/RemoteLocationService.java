package com.goeuro.cmdutil;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.http.GET;
import retrofit.http.Path;

import java.util.List;

/**
 * The REST service endpoint for GoEuro location
 *
 * @author tckb &lt; chandra[dot]tungathurthi[at]rwth-aachen[dot]de &gt;
 */
public final class RemoteLocationService {

    public final static String geServiceEndPoint = "http://api.goeuro.com/api/v2";
    private static GoEuroRemoteIntf remoteGEIntf;
    private static RemoteLocationService INSTANCE = null;

    /**
     * private constructor
     */
    private RemoteLocationService(LogLevel level) {
        // Initial setup for remote service
        remoteGEIntf = new RestAdapter.Builder()
                .setEndpoint(geServiceEndPoint)
                .setLogLevel(level)
                .build()
                .create(GoEuroRemoteIntf.class);
    }

    /**
     * A very simple Singleton pattern (lazy initializer!) implementation <br/>
     * for returning the instance of the class
     *
     * @param level verbose level
     * @return the instance for the class
     */
    public static RemoteLocationService getInstance(LogLevel level) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteLocationService(level);
        }
        return INSTANCE;
    }

    /**
     * Returns the remote service
     *
     * @return
     */
    public GoEuroRemoteIntf getGEServiceImpl() {
        return remoteGEIntf;
    }

    /**
     * Interface modeling the remote service
     */
    public interface GoEuroRemoteIntf {

        /**
         * Returns the list of locations for given city <br/>
         * by sending a REST request to remote and returns the result marshaled
         * into POJOs representing the result JSON.
         *
         * @param city
         * @return List of Locations
         * @see Location
         */
        @GET("/position/suggest/en/{city}")
        List<Location> getRemoteLocation(@Path("city") String city);

    }

}
