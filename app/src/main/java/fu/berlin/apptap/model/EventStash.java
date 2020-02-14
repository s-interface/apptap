package fu.berlin.apptap.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fu.berlin.apptap.data.AppTapDatabaseHelper;
import fu.berlin.apptap.data.EventCursorWrapper;

import static fu.berlin.apptap.data.AppTabDbSchema.*;

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

        try (EventCursorWrapper cursor = queryEvents(null, null)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                events.add(cursor.getEvent());
                cursor.moveToNext();
                Log.d("AppTap", "cursor_inloop: " + cursor.getPosition());
            }
        }

        Log.d("AppTap", "events.size(): " + events.size());

        return events;
    }

    public Event getEvent(UUID uuid) {

        return null;
    }

    //toDo: define correct values
    private static ContentValues getContentValues(Event event) {
        ContentValues values = new ContentValues();
        values.put(EventTable.Cols.NAME, event.getName());
//        values.put(CrimeTable.Cols.TITLE, event.getTitle());
//        values.put(CrimeTable.Cols.DATE, event.getDate().getTime());
//        values.put(CrimeTable.Cols.SOLVED, event.isSolved() ? 1 : 0);
        return values;
    }

    private EventCursorWrapper queryEvents(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                EventTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
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
