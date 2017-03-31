package ru.evoytenkoapps.rss;

import java.util.List;

/**
 * Created by evv on 24.03.2017.
 */

// http://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a

public interface AsyncResponse {
    void processFinish(List <String> output);
}
