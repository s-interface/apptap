package fu.berlin.apptap.database;

import android.database.Cursor;
import android.database.CursorWrapper;

public class EventsCursorWrapper extends CursorWrapper {
    public EventsCursorWrapper(Cursor cursor) {
        super(cursor);
    }

}
