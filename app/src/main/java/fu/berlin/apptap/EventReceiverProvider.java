package fu.berlin.apptap;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EventReceiverProvider extends ContentProvider {

    public static final String AUTHORITY = "fu.berlin.apptap.eventreceiverprovider";
    public static final String MIME_VND_TYPE = "vnd.example.item";

    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int ITEM = 1;
    private static final int ITEMS = 2;

    static {
        matcher.addURI(AUTHORITY, "items/#", ITEM);
        matcher.addURI(AUTHORITY, "items", ITEMS);
    }

    @Override
    public Bundle call(String method, String arg, Bundle bundle) {
        if (bundle != null) {
            Log.d("ProviderGotBundle", unpackBundle(bundle).toString());
        }
        return null;
    }

    public Bundle unpackBundle(Bundle bundle) {
        Bundle unpackedBundle = new Bundle(bundle);
        for (String key :
                unpackedBundle.keySet()) {
            Bundle oldBundle = unpackedBundle.getBundle(key);
            if (oldBundle != null) {
                unpackedBundle.putBundle(key, unpackBundle(oldBundle));
            }
        }
        return unpackedBundle;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
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
