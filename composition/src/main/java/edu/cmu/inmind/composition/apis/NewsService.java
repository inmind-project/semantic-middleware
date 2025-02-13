package edu.cmu.inmind.composition.apis;

import edu.cmu.inmind.composition.annotations.ArgDesc;
import edu.cmu.inmind.composition.annotations.Description;
import edu.cmu.inmind.composition.pojos.NewsPOJO;

/**
 * Created by oscarr on 8/10/18.
 */
public interface NewsService extends GenericService{
    @Description( capabilities = {
            "It returns the news feeds about a given topic",
            "It retrieves news about people",
            "It retrieves news about places, countries, and cities"
    })
    @ArgDesc(args = {
            "topic : which topic do you want me to use to filter your news feeds?"
    })
    NewsPOJO getNewsFeed(String topic);
}
