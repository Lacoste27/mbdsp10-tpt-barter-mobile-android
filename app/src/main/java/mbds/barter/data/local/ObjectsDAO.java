package mbds.barter.data.local;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mbds.barter.data.model.Objects;

public class ObjectsDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Gson gson = new Gson();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());

    public ObjectsDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertObject(Objects object) {
        ContentValues values = new ContentValues();
        values.put("id", object.getId());
        values.put("name", object.getName());
        values.put("categoryId", object.getCategoryId());
        values.put("description", object.getDescription());
        values.put("images", gson.toJson(object.getImage()));
        values.put("createdAt", dateFormat.format(object.getCreatedAt()));
        values.put("updatedAt", dateFormat.format(object.getUpdatedAt()));
        values.put("deletedAt", object.getDeletedAt() != null ? dateFormat.format(object.getDeletedAt()) : null);
        database.insert("objects", null, values);
    }

    public List<Objects> getAllObjects() {
        List<Objects> objectsList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM objects", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Objects object = cursorToObject(cursor);
            objectsList.add(object);
            cursor.moveToNext();
        }
        cursor.close();
        return objectsList;
    }

    @SuppressLint("Range")
    private Objects cursorToObject(Cursor cursor) {
        Objects object = new Objects();
        object.setId(cursor.getInt(cursor.getColumnIndex("id")));
        object.setName(cursor.getString(cursor.getColumnIndex("name")));
        object.setCategoryId(cursor.getInt(cursor.getColumnIndex("categoryId")));
        object.setDescription(cursor.getString(cursor.getColumnIndex("description")));

        // Convert images from JSON string back to List<String>
        String imagesJson = cursor.getString(cursor.getColumnIndex("images"));
        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> imagesList = gson.fromJson(imagesJson, listType);
        object.setImage(imagesList);

        try {
            object.setCreatedAt(dateFormat.parse(cursor.getString(cursor.getColumnIndex("createdAt"))));
            object.setUpdatedAt(dateFormat.parse(cursor.getString(cursor.getColumnIndex("updatedAt"))));
            String deletedAtStr = cursor.getString(cursor.getColumnIndex("deletedAt"));
            if (deletedAtStr != null) {
                object.setDeletedAt(dateFormat.parse(deletedAtStr));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return object;
    }
}
