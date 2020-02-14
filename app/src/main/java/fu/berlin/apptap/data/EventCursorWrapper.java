package fu.berlin.apptap.data;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import fu.berlin.apptap.model.Event;

import static fu.berlin.apptap.data.AppTabDbSchema.*;

public class EventCursorWrapper extends CursorWrapper {
    public EventCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Event getEvent() {

        String appId = getString(getColumnIndex(EventTable.Cols.APPID));
        String eventTime = getString(getColumnIndex(EventTable.Cols.TIME));
        String eventName = getString(getColumnIndex(EventTable.Cols.NAME));
        String eventOrigin = getString(getColumnIndex(EventTable.Cols.ORIGIN));
        String eventParams = getString(getColumnIndex(EventTable.Cols.PARAMS));

        Event event = new Event();
        event.setAppId(appId);
        event.setTime(eventTime);
        event.setName(eventName);
        event.setOrigin(eventOrigin);
        event.setParams(eventParams);

        return event;
    }

}
