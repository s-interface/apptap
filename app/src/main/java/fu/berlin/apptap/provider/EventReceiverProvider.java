package fu.berlin.apptap.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.Objects;

import fu.berlin.apptap.data.AppTabDbSchema;
import fu.berlin.apptap.data.AppTapDatabaseHelper;

public class EventReceiverProvider extends ContentProvider {

    public static final String AUTHORITY = "fu.berlin.apptap.eventreceiverprovider";
    public static final String MIME_VND_TYPE = "vnd.example.item";

    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int ITEM = 1;
    private static final int ITEMS = 2;
    public static final String LOG_TAG = "AppTap";
    public static final String BUNDLE_KEY_APP_ID = "app_id";
    public static final String BUNDLE_KEY_EVENT_NAME = "event_name";
    public static final String BUNDLE_KEY_EVENT_ORIGIN = "event_origin";
    public static final String BUNDLE_KEY_EVENT_TIMESTAMP = "event_timestamp";
    public static final String BUNDLE_KEY_BUNDLE_ARG = "bundle_arg";

    //database related
    private Context mContext;
    private SQLiteDatabase mDatabase;

    static {
        matcher.addURI(AUTHORITY, "items/#", ITEM);
        matcher.addURI(AUTHORITY, "items", ITEMS);
    }

    @Override
    public boolean onCreate() {
        mContext = Objects.requireNonNull(getContext(), "EventReceiverProvider.getContext() returned null").getApplicationContext();
        mDatabase = new AppTapDatabaseHelper(mContext).getWritableDatabase();
        return false;
    }

    @Override
    public Bundle call(String method, String arg, Bundle bundle) {
        if (bundle != null) {
            debugLogEvent(bundle);

            ContentValues values = getContentValues(bundle);
            mDatabase.insert(AppTabDbSchema.EventTable.NAME, null, values);
        }
        return null;
    }

    private static ContentValues getContentValues(Bundle bundle) {
        ContentValues values = new ContentValues();
        values.put(AppTabDbSchema.EventTable.Cols.APPID, bundle.getString(BUNDLE_KEY_APP_ID));
        values.put(AppTabDbSchema.EventTable.Cols.TIME, String.valueOf(bundle.getLong(BUNDLE_KEY_EVENT_TIMESTAMP)));
        values.put(AppTabDbSchema.EventTable.Cols.NAME, bundle.getString(BUNDLE_KEY_EVENT_NAME));
        values.put(AppTabDbSchema.EventTable.Cols.ORIGIN, bundle.getString(BUNDLE_KEY_EVENT_ORIGIN));
        values.put(AppTabDbSchema.EventTable.Cols.PARAMS, bundle.getBundle(BUNDLE_KEY_BUNDLE_ARG).toString());
        return values;
    }

    private void debugLogEvent(Bundle bundle) {
        String eventLogString = "Event received: Event{appID='" +
                bundle.getString(BUNDLE_KEY_APP_ID) +
                "', name='" +
                bundle.getString(BUNDLE_KEY_EVENT_NAME) +
                "', origin='" +
                bundle.getString(BUNDLE_KEY_EVENT_ORIGIN) +
                "', time='" +
                bundle.getLong(BUNDLE_KEY_EVENT_TIMESTAMP) +
                "', params=" +
                unpackBundle(bundle.getBundle(BUNDLE_KEY_BUNDLE_ARG)).toString() +
                "}";
        Log.d(LOG_TAG, eventLogString);
    }

    public Bundle unpackBundle(Bundle bundle) {
        Bundle unpackedBundle = new Bundle();
        if (bundle != null) {
            unpackedBundle.putAll(bundle);
            for (String key :
                    unpackedBundle.keySet()) {
                Object value = unpackedBundle.get(key);
                if (value instanceof Bundle) {
                    unpackedBundle.putBundle(key, unpackBundle((Bundle) value));
                }
            }
        }
        return unpackedBundle;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
