package mbds.barter.data.datasource;

import mbds.barter.data.model.Objects;
import mbds.barter.data.request.ObjectRequest;
import mbds.barter.data.response.AuthResponse;
import mbds.barter.service.Api;
import mbds.barter.service.IObjectService;
import retrofit2.Call;
import retrofit2.Callback;

public class ObjectDataSource {
    private IObjectService api;

    public void  addObject(ObjectRequest request, Callback<Objects> callback) {
        api = Api.getClient().create(IObjectService.class);

        Call<Objects> call = api.addObject(request);

        call.enqueue(callback);
    }
}
