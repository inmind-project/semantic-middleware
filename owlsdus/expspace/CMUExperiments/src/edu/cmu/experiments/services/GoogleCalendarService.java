package edu.cmu.experiments.services;

import java.util.Date;

import edu.cmu.experiments.apis.CalendarService;
import edu.cmu.inmind.multiuser.controller.log.Log4J;

/**
 * Created by oscarr on 8/8/18.
 */
public class GoogleCalendarService implements CalendarService {

    @Override
    public Boolean checkAvailability(Date from, Date to){
        Log4J.warn(this, String.format("Executing GoogleCalendarService.checkAvailability for: [from date: %s, " +
                "to date: %s]", from, to));
        return true;
    }

    @Override
    public String createEvent(String eventName, Date from, Date to){
        Log4J.warn(this, String.format("Executing GoogleCalendarService.createEvent for: [eventName: %s. " +
                "from date: %s, to date: %s]", eventName, from, to));
        return null;
    }
}
