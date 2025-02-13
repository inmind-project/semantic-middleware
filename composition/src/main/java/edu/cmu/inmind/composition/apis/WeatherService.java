package edu.cmu.inmind.composition.apis;

import edu.cmu.inmind.composition.annotations.ArgDesc;
import edu.cmu.inmind.composition.annotations.Description;
import edu.cmu.inmind.composition.pojos.LocationPOJO;
import edu.cmu.inmind.composition.pojos.WeatherPOJO;

import java.util.Date;

/**
 * Created by oscarr on 8/8/18.
 */
public interface WeatherService extends GenericService {

    @Description(capabilities = {
            "This method validates the weather conditions of a specific place given a range of dates",
            "This method checks the weather conditions and forecast for a given place and range of dates",
            "This method determines good and bad weather conditions for a given place and range of dates"
    })
    @ArgDesc(args = {
            "place : which location do you want me to check the weather for?",
            "from : from date (yyyy-mm-dd)...?",
            "to : to date (yyyy-mm-dd)...?"
    })
    WeatherPOJO checkWeatherConditions(LocationPOJO place, Date from, Date to);
}
