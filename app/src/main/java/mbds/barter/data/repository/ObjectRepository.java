package mbds.barter.data.repository;

import android.content.Context;

import java.util.List;

import mbds.barter.data.datasource.ObjectDataSource;
import mbds.barter.data.local.ObjectsDAO;
import mbds.barter.data.model.Objects;
import mbds.barter.data.response.ObjectsResponse;
import mbds.barter.utils.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObjectRepository {
    private static volatile ObjectRepository instance;
    private ObjectDataSource dataSource;
    private ObjectsDAO objectsDAO;
    private Context context;

    private ObjectRepository(Context context, ObjectDataSource dataSource) {
        this.context = context;
        this.dataSource = dataSource;
        this.objectsDAO = new ObjectsDAO(context);
        this.objectsDAO.open();
    }

    public static synchronized ObjectRepository getInstance(Context context, ObjectDataSource dataSource) {
        if (instance == null) {
            instance = new ObjectRepository(context, dataSource);
        }
        return instance;
    }

    public void getAllObjects(Callback<ObjectsResponse> callback) {
        if (NetworkUtils.isNetworkAvailable(context)) {
            // Online: Fetch from API
            dataSource.getAllObjects(new Callback<ObjectsResponse>() {
                @Override
                public void onResponse(Call<ObjectsResponse> call, Response<ObjectsResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Store the fetched objects into the local SQLite database
                        List<Objects> objects = response.body().getData();
                        for (Objects object : objects) {
                            objectsDAO.insertObject(object);
                        }
                        callback.onResponse(call, response);
                    } else {
                        callback.onFailure(call, new Exception("API response unsuccessful"));
                    }
                }

                @Override
                public void onFailure(Call<ObjectsResponse> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        } else {
            // Offline: Fetch from SQLite
            List<Objects> objectsList = objectsDAO.getAllObjects();
            ObjectsResponse objectsResponse = new ObjectsResponse();
            objectsResponse.setData(objectsList);
            callback.onResponse(null, Response.success(objectsResponse));
        }
    }
}
