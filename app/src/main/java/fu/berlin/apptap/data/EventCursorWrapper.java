package fu.berlin.apptap.data;

import android.database.Cursor;
import android.database.CursorWrapper;

import fu.berlin.apptap.model.Event;

import static fu.berlin.apptap.data.AppTabDbSchema.*;

public class EventCursorWrapper extends CursorWrapper {
    public EventCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Event getEvent() {

        int event_index_id = getInt(getColumnIndex(EventTable.Cols.INDEX_ID));
        String appId = getString(getColumnIndex(EventTable.Cols.APPID));
        String eventTime = getString(getColumnIndex(EventTable.Cols.TIME));
        String eventName = getString(getColumnIndex(EventTable.Cols.NAME));
        String eventOrigin = getString(getColumnIndex(EventTable.Cols.ORIGIN));
        String eventParams = getString(getColumnIndex(EventTable.Cols.PARAMS));

        Event event = new Event(event_index_id);
        event.setAppId(appId);
        event.setTime(eventTime);
        event.setName(eventName);
        event.setOrigin(eventOrigin);
        event.setParams(eventParams);

        return event;
    }

}
