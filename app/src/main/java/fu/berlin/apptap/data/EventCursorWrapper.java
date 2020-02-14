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

        String name = getString(getColumnIndex(EventTable.Cols.NAME));
        String appId = getString(getColumnIndex(EventTable.Cols.APPID));

        Event event = new Event();
        event.setName(name);
        event.setAppId(appId);

        return event;
    }

}
