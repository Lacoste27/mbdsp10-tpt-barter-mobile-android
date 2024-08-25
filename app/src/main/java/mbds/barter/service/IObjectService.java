package mbds.barter.service;

import java.util.List;

import mbds.barter.data.model.Objects;
import mbds.barter.data.request.ObjectRequest;
import mbds.barter.data.response.ObjectsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IObjectService {
    @POST("/api/objects")
    Call<Objects> addObject(@Body ObjectRequest request);

    @GET("/api/objects")
    Call<ObjectsResponse> getAllObjects();
}
