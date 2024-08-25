package mbds.barter.data.datasource;

import java.io.IOException;
import mbds.barter.data.response.ObjectsResponse;
import mbds.barter.service.Api;
import mbds.barter.service.IObjectService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObjectDataSource {
    private IObjectService api;

    public ObjectDataSource() {
        api = Api.getClient().create(IObjectService.class);
    }

    public void getAllObjects(Callback<ObjectsResponse> callback) {
        Call<ObjectsResponse> call = api.getAllObjects();
        call.enqueue(new Callback<ObjectsResponse>() {
            @Override
            public void onResponse(Call<ObjectsResponse> call, Response<ObjectsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    callback.onFailure(call, new IOException("Error fetching data"));
                }
            }

            @Override
            public void onFailure(Call<ObjectsResponse> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
