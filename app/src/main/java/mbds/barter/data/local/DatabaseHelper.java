package mbds.barter.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "objects.db";
    private static final int DATABASE_VERSION = 1;

    // Full SQL query for creating the objects table
    private static final String TABLE_CREATE =
            "CREATE TABLE objects (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT, " +
                    "categoryId INTEGER, " +
                    "description TEXT, " +
                    "images TEXT, " + // Store as JSON string
                    "createdAt TEXT, " +
                    "updatedAt TEXT, " +
                    "deletedAt TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS objects");
        onCreate(db);
    }
}
