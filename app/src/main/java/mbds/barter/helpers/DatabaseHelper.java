package mbds.barter.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Name
    private static final String DATABASE_NAME = "barter.db";
    // Database Version
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        String CREATE_TABLE =
                "CREATE TABLE Object (\n" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  name TEXT NOT NULL,\n" +
                "  categoryId INTEGER NOT NULL,\n" +
                "  description TEXT,\n" +
                "  photos TEXT, -- Storing array as a JSON string\n" +
                "  deletedAt DATETIME,\n" +
                "  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "  updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP\n" +
                ");\n";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old tables if they exist
        db.execSQL("DROP TABLE IF EXISTS barder");
        // Create new tables
        onCreate(db);
    }
}
