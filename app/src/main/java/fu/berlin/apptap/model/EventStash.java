package fu.berlin.apptap.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fu.berlin.apptap.data.AppTapDatabaseHelper;
import fu.berlin.apptap.data.EventCursorWrapper;

import static fu.berlin.apptap.data.AppTabDbSchema.EventTable;

public class EventStash {
    private static EventStash sEventStashInstance;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private EventStash(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new AppTapDatabaseHelper(mContext).getWritableDatabase();
    }

    public void addEvent(Event event) {
        ContentValues values = getContentValues(event);
        mDatabase.insert(EventTable.NAME, null, values);
    }

    public long getEventsCount() {
        long count = DatabaseUtils.queryNumEntries(mDatabase, EventTable.NAME);
        return count;
    }

    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();

        try (EventCursorWrapper cursor = queryEvents(null, null, AppTapDatabaseHelper.ORDER_DESCENDING)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                events.add(cursor.getEvent());
                cursor.moveToNext();
            }
        }

        return events;
    }

    public Event getEvent(int eventId) {

        try (EventCursorWrapper cursor = queryEvents(EventTable.Cols.INDEX_ID + " = ?", new String[]{Integer.toString(eventId)}, null)) {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getEvent();
        }
    }

    //toDo: define correct values
    private static ContentValues getContentValues(Event event) {
        ContentValues values = new ContentValues();
        values.put(EventTable.Cols.NAME, event.getName());
        values.put(EventTable.Cols.APPID, event.getAppId());
        return values;
    }

    private EventCursorWrapper queryEvents(String whereClause, String[] whereArgs, String orderBy) {
        Cursor cursor = mDatabase.query(
                EventTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                orderBy // orderBy
        );

        return new EventCursorWrapper(cursor);
    }

    public static EventStash getInstance(Context context) {
        if (sEventStashInstance == null) {
            sEventStashInstance = new EventStash(context);
        }
        return sEventStashInstance;
    }
}
