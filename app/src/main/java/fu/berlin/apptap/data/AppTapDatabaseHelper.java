package fu.berlin.apptap.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fu.berlin.apptap.data.AppTabDbSchema.EventTable;

public class AppTapDatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "events.db";

    public static final String ORDER_DESCENDING = "_id DESC";

    /**
     * Create a helper object to create, open, and/or manage a database.
     *
     * @param context to use for locating paths to the the database
     */
    public AppTapDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + EventTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
//                EventTable.Cols.UUID + ", " +
                EventTable.Cols.APPID + ", " +
                EventTable.Cols.TIME + ", " +
                EventTable.Cols.NAME + ", " +
                EventTable.Cols.ORIGIN + ", " +
                EventTable.Cols.PARAMS +
                ")"
        );
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
