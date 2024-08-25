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
import java.util.List;
import java.util.Locale;

import mbds.barter.data.model.QRCPost;

public class QRCPostDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Gson gson = new Gson();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());

    public QRCPostDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertQRCPost(QRCPost qrcPost) {
        ContentValues values = new ContentValues();
        values.put("id", qrcPost.getId());
        values.put("authorId", qrcPost.getAuthorId());
        values.put("description", qrcPost.getDescription());
        values.put("createdAt", dateFormat.format(qrcPost.getCreatedAt()));
        values.put("updatedAt", dateFormat.format(qrcPost.getUpdatedAt()));
        values.put("deletedAt", qrcPost.getDeletedAt() != null ? dateFormat.format(qrcPost.getDeletedAt()) : null);

        // Convert nested lists to JSON strings
        values.put("objects", gson.toJson(qrcPost.getObjects()));
        values.put("suggestions", gson.toJson(qrcPost.getSuggestions()));
        values.put("author", gson.toJson(qrcPost.getAuthor()));

        database.insert("posts", null, values);
    }

    public List<QRCPost> getAllQRCPosts() {
        List<QRCPost> qrcPostList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM posts", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            QRCPost qrcPost = cursorToQRCPost(cursor);
            qrcPostList.add(qrcPost);
            cursor.moveToNext();
        }
        cursor.close();
        return qrcPostList;
    }

    @SuppressLint("Range")
    private QRCPost cursorToQRCPost(Cursor cursor) {
        QRCPost qrcPost = new QRCPost();
        qrcPost.setId(cursor.getInt(cursor.getColumnIndex("id")));
        qrcPost.setAuthorId(cursor.getInt(cursor.getColumnIndex("authorId")));
        qrcPost.setDescription(cursor.getString(cursor.getColumnIndex("description")));

        try {
            qrcPost.setCreatedAt(dateFormat.parse(cursor.getString(cursor.getColumnIndex("createdAt"))));
            qrcPost.setUpdatedAt(dateFormat.parse(cursor.getString(cursor.getColumnIndex("updatedAt"))));
            String deletedAtStr = cursor.getString(cursor.getColumnIndex("deletedAt"));
            if (deletedAtStr != null) {
                qrcPost.setDeletedAt(dateFormat.parse(deletedAtStr));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Convert JSON strings back to nested objects
        String objectsJson = cursor.getString(cursor.getColumnIndex("objects"));
        Type objectsListType = new TypeToken<List<QRCPost.ObjectPost>>() {}.getType();
        List<QRCPost.ObjectPost> objectsList = gson.fromJson(objectsJson, objectsListType);
        qrcPost.setObjects(objectsList);

        String suggestionsJson = cursor.getString(cursor.getColumnIndex("suggestions"));
        Type suggestionsListType = new TypeToken<List<QRCPost.Suggestion>>() {}.getType();
        List<QRCPost.Suggestion> suggestionsList = gson.fromJson(suggestionsJson, suggestionsListType);
        qrcPost.setSuggestions(suggestionsList);

        String authorJson = cursor.getString(cursor.getColumnIndex("author"));
        QRCPost.Author author = gson.fromJson(authorJson, QRCPost.Author.class);
        qrcPost.setAuthor(author);

        return qrcPost;
    }
}
