package com.goeuro.service.impl;
/**
 * @author tckb
 * Created on 09/05/16.
 */

import com.goeuro.model.Location;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

import java.util.List;

/**
 * A component representing Locations service
 */
@Component
@PropertySource("classpath:application.properties")
public class GoEuroService {

    private Logger log = Logger.getLogger(getClass().getName());

    @Value("${goeuro.baseurl}")
    private String apiBaseUrl;

    private GoEuroApiService goEuroApiService;


    /**
     * Returns the remote service
     *
     * @return
     */
    public GoEuroApiService getService() {

        if (goEuroApiService == null) {
            log.debug("Using Api BaseUrl: " + apiBaseUrl);

            goEuroApiService = new RestAdapter.Builder()
                    .setEndpoint(apiBaseUrl)
                    .build()
                    .create(GoEuroApiService.class);
        }
        return goEuroApiService;
    }

    /**
     * Interface modeling the remote service
     */
    public interface GoEuroApiService {

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
        List<Location> searchMatchedLocations(@Path("city") String city);

    }

}
