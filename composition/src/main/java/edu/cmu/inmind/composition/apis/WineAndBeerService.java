package edu.cmu.inmind.composition.apis;

import edu.cmu.inmind.composition.annotations.ArgDesc;
import edu.cmu.inmind.composition.annotations.Description;
import edu.cmu.inmind.composition.pojos.LocationPOJO;
import edu.cmu.inmind.composition.pojos.WeatherPOJO;

import java.util.Date;

/**
 * Created by oscarr on 8/17/18.
 */
public interface WineAndBeerService extends GenericService{

    @Description( capabilities = {
            "This method allows to search a list of Wine and Spirit shops near to a location for a given time",
            "This method allows to lookup for Wine and spirit shops near to a location",
            "This method searches a list of Wine shops given a location, time"
    })
    @ArgDesc(args = {
            "location : which location are you looking for?",
            "date : which date are you looking for?",
            "time : which time are you looking for?"
    })
    void searchWineAndSpiritsShop();

    @Description( capabilities = {
            "This method allows to search a list of beer shops near to a location",
            "This method allows to lookup for beer shops near to a location",
            "This method searches a list of beer shops near to a location"
    })
    @ArgDesc(args = {
            "location : which location are you looking for?",
            "date : which date are you looking for?",
            "time : which time are you looking for?"
    })
    void searchBeerShop();
}
