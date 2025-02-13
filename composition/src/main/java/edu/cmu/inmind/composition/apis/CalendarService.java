package edu.cmu.inmind.composition.apis;

import edu.cmu.inmind.composition.annotations.ArgDesc;
import edu.cmu.inmind.composition.annotations.Description;
import edu.cmu.inmind.composition.pojos.CalendarPOJO;

import java.util.Date;

/**
 * Created by oscarr on 8/8/18.
 */
public interface CalendarService extends GenericService {

    @Description(capabilities = {
            "This method validates whether there is availability on calendar given a time slot",
            "This method checks whether there is a conflict on the calendar given a range of dates",
            "This method checks calendar availability on a range of dates"
    })
    @ArgDesc(args = {
            "from : from date (yyyy-mm-dd)...?",
            "to : to date (yyyy-mm-dd)...?"
    })
    Boolean checkAvailability(Date from, Date to);



    @Description(capabilities = {
            "This method creates a new calendar event given a time slot and an event name",
            "This method generates a new calendar event given an event name and a range of dates",
    })
    @ArgDesc(args = {
            "eventName : what is the name of your event?",
            "from : from date (yyyy-mm-dd)...?",
            "to : to date (yyyy-mm-dd)...?"
    })
    CalendarPOJO createEvent(String eventName, Date from, Date to);
}
