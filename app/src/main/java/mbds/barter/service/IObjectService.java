package mbds.barter.service;

import mbds.barter.data.model.Objects;
import mbds.barter.data.request.ObjectRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IObjectService {
    @POST("/api/objects")
    Call<Objects> addObject(@Body ObjectRequest request);
}
